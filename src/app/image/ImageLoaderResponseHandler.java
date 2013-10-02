package app.image;

import android.graphics.Bitmap;

public interface ImageLoaderResponseHandler {
	
	public void onSuccess(Bitmap bitmap);
	
	public void onError(Exception e);

}
