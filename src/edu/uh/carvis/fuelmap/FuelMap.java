package edu.uh.carvis.fuelmap;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;
import edu.uh.carvis.R;

import java.net.*;
import java.io.*;
import java.util.List;

/**
 * Created by zaki on 1/25/14.
 */
public class FuelMap extends Activity implements FuelInfoCompleted {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng sydney = new LatLng(-33.867, 151.206);



        //map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));

        try {
            RetrieverFuelInfo r = new RetrieverFuelInfo(this);
            r.execute(new URL("http://enetdown.org:3000/api/fuel/0/0"));
        } catch( MalformedURLException e ) {
            /* TODO */
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(List<FuelInfo> f) {
        for( FuelInfo i : f ) {
            System.out.println("|||||"+ i );
        }
    }
}