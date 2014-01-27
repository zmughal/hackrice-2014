package edu.uh.carvis.fuelmap;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by zaki on 1/26/14.
 */

public class RetrieverFuelInfo extends AsyncTask<URL, Void, List<FuelInfo>> {
	FuelInfoCompleted listener;

	RetrieverFuelInfo(FuelInfoCompleted listener) {
		System.out.println(listener);
		this.listener = listener;
	}

	@Override
	protected List<FuelInfo> doInBackground(URL... urls) {
		URL targetURL = urls[0];
		try {
			URLConnection yc = targetURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			Gson gson = new Gson();
			return gson.fromJson(in, new TypeToken<List<FuelInfo>>() {
			}.getType());
		} catch (IOException e) {
	        /* TODO */
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<FuelInfo> f) {
		listener.onTaskCompleted(f);
	}
}
