package edu.uh.carvis.fuelmap;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;
import edu.uh.carvis.R;

import java.net.*;
import java.io.*;

/**
 * Created by zaki on 1/25/14.
 */
public class FuelMap extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng sydney = new LatLng(-33.867, 151.206);

        try {
            URL oracle = new URL("http://www.oracle.com/");

            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.print("|||||-");
                System.out.println(inputLine);
            }
            in.close();
        } catch( IOException e ) {
            e.printStackTrace();
        }


        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }
}