package lib.imageloaderdroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import app.image.ImageLoader;
import app.image.ImageLoaderResponseHandler;

public class MainActivity extends Activity {

	private ImageView imageView;
	private ProgressBar progressBar;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initComponents();
		String url = "http://www.vogella.de/img/lars/LarsVogelArticle7a.png";
		loadImage(url);
	}

	private void initComponents() {
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setVisibility(View.GONE);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);
		button = (Button) findViewById(R.id.button1);
		button.setVisibility(View.GONE);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://www.vogella.de/img/lars/LarsVogelArticle7.png";
				loadImage(url);
			}
		});
	}

	private void loadImage(String url) {
		button.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		new ImageLoader().get(url, new ImageLoaderResponseHandler() {

			@Override
			public void onSuccess(Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);
				imageView.setVisibility(View.VISIBLE);
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onError(Exception e) {
				progressBar.setVisibility(View.GONE);
				button.setVisibility(View.VISIBLE);
				Toast.makeText(getApplicationContext(),
						"Unable to load image.", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
