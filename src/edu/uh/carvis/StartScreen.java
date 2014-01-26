package edu.uh.carvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import edu.uh.carvis.emergency.EmergencyServicesActivity;
import edu.uh.carvis.fuelmap.FuelMap;

/**
 * Created by Tanmay_Local on 1/25/14.
 */
public class StartScreen extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    setContentView(R.layout.startscreen_table);
    }

    public void launchFuelMap(View v) {
        Intent intent = new Intent(this, FuelMap.class);
        startActivity(intent);
    }

	public void launchEmergencyServices(View v) {
		Intent intent = new Intent(this, EmergencyServicesActivity.class);
		startActivity(intent);
	}
}