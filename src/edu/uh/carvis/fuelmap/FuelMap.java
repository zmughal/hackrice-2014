package edu.uh.carvis.fuelmap;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import edu.uh.carvis.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by zaki on 1/25/14.
 */
public class FuelMap extends Activity implements FuelInfoCompleted {
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);

		// Get a handle to the Map Fragment
		this.map = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		try {
			RetrieverFuelInfo r = new RetrieverFuelInfo(this);
			r.execute(new URL("http://enetdown.org:3000/api/fuel/0/0"));
		} catch (MalformedURLException e) {
	        /* TODO */
			e.printStackTrace();
		}
	}

	@Override
	public void onTaskCompleted(List<FuelInfo> f) {
		LatLngBounds.Builder bndsBuild = LatLngBounds.builder();
		for (FuelInfo i : f) {
			System.out.println("|||||" + i);
			LatLng pos = i.getLatLng();
			bndsBuild.include(pos);
			map.addMarker(new MarkerOptions()
					.anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
					.title(i.getName() + " - " + i.getPrice())
					.position(pos));
			map.moveCamera(CameraUpdateFactory.newLatLngBounds(bndsBuild.build(), 200));
		}
	}
}