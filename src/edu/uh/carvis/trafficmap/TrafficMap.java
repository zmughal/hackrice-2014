package edu.uh.carvis.trafficmap;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import edu.uh.carvis.R;

/**
 * Created by zaki on 1/26/14.
 */
public class TrafficMap extends Activity {
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);

		// Get a handle to the Map Fragment
		this.map = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		android.location.LocationListener locationListener = new android.location.LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location provider.
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(location.getLatitude(),
								location.getLongitude()), 13));

			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// Register the listener with the Location Manager to receive location updates
		// LocationManager.GPS_PROVIDER
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 100.0F, locationListener);
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
				new LatLng(location.getLatitude(),
						location.getLongitude()), 13));

		map.setTrafficEnabled(true);
	}
}
