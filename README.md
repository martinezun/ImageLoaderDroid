ImageLoaderDroid
================

Android Library to load image from URL  

It uses **HttpClient** to load image from remote server and **AsyncTask** to do it asynchronously.

Example usage:

	``java
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
	``