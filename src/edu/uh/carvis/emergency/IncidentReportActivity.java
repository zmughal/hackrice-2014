package edu.uh.carvis.emergency;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import edu.uh.carvis.R;
import edu.uh.carvis.database.CarvisDatabaseHelper;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Eric
 * Date: 1/25/14
 */
public class IncidentReportActivity extends Activity {

	public static final String EXTRA_ACTIVITY_TYPE = "activity_type";

	public static final int ACTIVITY_TYPE_NEW = 1;
	public static final int ACTIVITY_TYPE_EXISTING = 2;
	public static final int ACTIVITY_TYPE_EDIT_ME = 3;
	public static final int ACTIVITY_TYPE_VIEW_ME = 3;

	private CarvisDatabaseHelper database;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new CarvisDatabaseHelper(this);
		int activityType = getIntent().getIntExtra(EXTRA_ACTIVITY_TYPE, ACTIVITY_TYPE_NEW);

		if (activityType == ACTIVITY_TYPE_NEW) {
			setContentView(R.layout.new_incident);
		}
		else if (activityType == ACTIVITY_TYPE_EXISTING) {

		}
		else if (activityType == ACTIVITY_TYPE_EDIT_ME) {

		}
		else if (activityType == ACTIVITY_TYPE_VIEW_ME) {

		}
	}

	public void saveIncidentReport(View v) {
		List<TextView> list = new ArrayList<TextView>();
		list.add((TextView)findViewById(R.id.full_name));
		list.add((TextView)findViewById(R.id.address_1));
		list.add((TextView)findViewById(R.id.address_2));
		list.add((TextView)findViewById(R.id.city));
		list.add((TextView)findViewById(R.id.state));
		list.add((TextView)findViewById(R.id.zipcode));
		list.add((TextView)findViewById(R.id.phone_number));
		list.add((TextView)findViewById(R.id.email));
		list.add((TextView)findViewById(R.id.vehicle_make));
		list.add((TextView)findViewById(R.id.vehicle_model));
		list.add((TextView)findViewById(R.id.vehicle_year));
		list.add((TextView)findViewById(R.id.license_plate));
		list.add((TextView)findViewById(R.id.insurance_carrier));
		list.add((TextView)findViewById(R.id.policy_number));
		list.add((TextView)findViewById(R.id.insurance_phone_number));

		database.runStatement(R.raw.insert_incident_report, list);
	}
}