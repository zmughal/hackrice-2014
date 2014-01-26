package edu.uh.carvis.fuelmap;

import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by zaki on 1/26/14.
 */
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.reflect.TypeToken;

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
            return gson.fromJson(in, new TypeToken<List<FuelInfo>>(){}.getType());
        } catch( IOException e ) {
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
