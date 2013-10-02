package app.image;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class ImageLoader {

	public ImageLoader() {

	}

	public void get(final String url, final ImageLoaderResponseHandler handler) {

		new AsyncTask<Void, Void, Void>() {

			private Bitmap bitmap = null;
			private IOException ioe = null;

			@Override
			protected Void doInBackground(Void... params) {
				try {
					bitmap = downloadBitmap(url);
				} catch (IOException e) {
					ioe = e;
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				if (bitmap != null) {
					handler.onSuccess(bitmap);
				} else {
					handler.onError(ioe);
				}
			}

		}.execute();

	}

	private Bitmap downloadBitmap(String url) throws IOException {
		HttpUriRequest request = new HttpGet(url.toString());
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(entity);

			Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,
					bytes.length);
			return bitmap;
		} else {
			throw new IOException("Download failed, HTTP response code "
					+ statusCode + " - " + statusLine.getReasonPhrase());
		}
	}

}
