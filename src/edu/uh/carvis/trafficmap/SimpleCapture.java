package edu.uh.carvis.trafficmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import edu.uh.carvis.fuelmap.FuelInfo;
import edu.uh.carvis.fuelmap.FuelInfoCompleted;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * User: Eric
 * Date: 1/26/14
 */
public class SimpleCapture extends Activity implements ZakiListener {

	private URL enetDown;

	static final int REQUEST_IMAGE_CAPTURE = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			ZakiAsync zaki = new ZakiAsync(this);
			zaki.execute(data.getExtras());
		}
	}

	@Override
	public void onTaskCompleted(String s) {
		Intent intent = new Intent();
		intent.putExtra("RESULT", s);

		setResult(REQUEST_IMAGE_CAPTURE, intent);
		finish();
	}

	class ZakiAsync extends AsyncTask<Bundle, Void, String> {

		ZakiListener listener;

		ZakiAsync(ZakiListener listener) {
			this.listener = listener;
		}

		@Override
		protected String doInBackground(Bundle... params) {
			HttpURLConnection httpUrlConnection = null;
			InputStream responseStream = null;

			try {
				Bitmap bitmap = (Bitmap) params[0].get("data");

				System.out.println("|||||" + bitmap);

				String attachmentName = "bitmap";
				String attachmentFileName = "bitmap.bmp";
				String crlf = "\r\n";
				String twoHyphens = "--";
				String boundary = "*****";

				httpUrlConnection = (HttpURLConnection) new URL("http://enetdown.org:3000/api/image").openConnection();
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setDoOutput(true);

				httpUrlConnection.setRequestMethod("POST");
				httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
				httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
				httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

				DataOutputStream request = new DataOutputStream(httpUrlConnection.getOutputStream());

				request.writeBytes(twoHyphens + boundary + crlf);
				request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
				request.writeBytes(crlf);
				System.out.println("||||| HEADER WRITTEN");
//				I want to send only 8 bit black & white bitmaps
				byte[] pixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
				for (int i = 0; i < bitmap.getWidth(); ++i) {
					for (int j = 0; j < bitmap.getHeight(); ++j) {
//						we're interested only in the MSB of the first byte,
//						since the other 3 bytes are identical for B&W images
						pixels[i + j] = (byte) ((bitmap.getPixel(i, j) & 0x80) >> 7);
					}
				}

				request.write(pixels);
				System.out.println("||||| PIXELS WRITTEN");
				request.writeBytes(crlf);
				request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

				request.flush();
				request.close();

				System.out.println("||||| FLUSHED");

				responseStream = new BufferedInputStream(httpUrlConnection.getInputStream());

				Gson gson = new Gson();

				return gson.fromJson(new JsonReader(new InputStreamReader(responseStream)), String.class);

			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					httpUrlConnection.disconnect();
					responseStream.close();
				}
				catch(Exception e) {}
			}
			return "";
		}

		@Override
		protected void onPostExecute(String f) {
			listener.onTaskCompleted(f);
		}
	}
}

interface ZakiListener {
	public void onTaskCompleted(String s);
}