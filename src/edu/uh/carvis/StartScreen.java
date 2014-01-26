package edu.uh.carvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.content.Intent;
=======
import edu.uh.carvis.emergency.EmergencyServicesActivity;
>>>>>>> Emergency Services module

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