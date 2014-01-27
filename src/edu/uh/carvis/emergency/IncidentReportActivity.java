package edu.uh.carvis.emergency;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import edu.uh.carvis.R;
import edu.uh.carvis.database.CarvisDatabaseHelper;

import java.util.*;

/**
 * User: Eric
 * Date: 1/25/14
 */
public class IncidentReportActivity extends Activity {

	public static final String EXTRA_ACTIVITY_TYPE = "activity_type";

	public static final int ACTIVITY_TYPE_NEW = 1;
	public static final int ACTIVITY_TYPE_EXISTING = 2;
	public static final int ACTIVITY_TYPE_EDIT_ME = 3;
	public static final int ACTIVITY_TYPE_VIEW_ME = 4;

	private CarvisDatabaseHelper database;

	private Map<String, TextView> columnToTextView = new HashMap<String, TextView>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		database = new CarvisDatabaseHelper(this);
		int activityType = getIntent().getIntExtra(EXTRA_ACTIVITY_TYPE, ACTIVITY_TYPE_NEW);

		if (activityType == ACTIVITY_TYPE_NEW) {
			setContentView(R.layout.new_incident);
		} else if (activityType == ACTIVITY_TYPE_EXISTING) {

		} else if (activityType == ACTIVITY_TYPE_EDIT_ME) {
			setContentView(R.layout.edit_me);
			initMapping();
			populateMyInfo();
		} else if (activityType == ACTIVITY_TYPE_VIEW_ME) {
			setContentView(R.layout.view_me);
			initMapping();
			populateMyInfo();
		}
	}

	private void initMapping() {
		columnToTextView.put("full_name", ((TextView) findViewById(R.id.full_name)));
		columnToTextView.put("address_1", ((TextView) findViewById(R.id.address_1)));
		columnToTextView.put("address_2", ((TextView) findViewById(R.id.address_2)));
		columnToTextView.put("city", ((TextView) findViewById(R.id.city)));
		columnToTextView.put("state", ((TextView) findViewById(R.id.state)));
		columnToTextView.put("zipcode", ((TextView) findViewById(R.id.zipcode)));
		columnToTextView.put("phone", ((TextView) findViewById(R.id.phone_number)));
		columnToTextView.put("email", ((TextView) findViewById(R.id.email)));
		columnToTextView.put("vehicle_make", ((TextView) findViewById(R.id.vehicle_make)));
		columnToTextView.put("vehicle_model", ((TextView) findViewById(R.id.vehicle_model)));
		columnToTextView.put("vehicle_year", ((TextView) findViewById(R.id.vehicle_year)));
		columnToTextView.put("license_plate", ((TextView) findViewById(R.id.license_plate)));
		columnToTextView.put("insurance_carrier", ((TextView) findViewById(R.id.insurance_carrier)));
		columnToTextView.put("policy_number", ((TextView) findViewById(R.id.policy_number)));
		columnToTextView.put("insurance_phone_number", ((TextView) findViewById(R.id.insurance_phone_number)));
	}

	public void saveIncidentReport(View v) {
		List<Object> list = new ArrayList<Object>();
		list.add(findViewById(R.id.full_name));
		list.add(findViewById(R.id.address_1));
		list.add(findViewById(R.id.address_2));
		list.add(findViewById(R.id.city));
		list.add(findViewById(R.id.state));
		list.add(findViewById(R.id.zipcode));
		list.add(findViewById(R.id.phone_number));
		list.add(findViewById(R.id.email));
		list.add(findViewById(R.id.vehicle_make));
		list.add(findViewById(R.id.vehicle_model));
		list.add(findViewById(R.id.vehicle_year));
		list.add(findViewById(R.id.license_plate));
		list.add(findViewById(R.id.insurance_carrier));
		list.add(findViewById(R.id.policy_number));
		list.add(findViewById(R.id.insurance_phone_number));

		database.runStatement(R.raw.insert_incident_report, list);

		finish();
	}

	public void saveMyInfo(View v) {
		List<Object> list = new ArrayList<Object>();
		list.add(0);
		list.add(findViewById(R.id.full_name));
		list.add(findViewById(R.id.address_1));
		list.add(findViewById(R.id.address_2));
		list.add(findViewById(R.id.city));
		list.add(findViewById(R.id.state));
		list.add(findViewById(R.id.zipcode));
		list.add(findViewById(R.id.phone_number));
		list.add(findViewById(R.id.email));
		list.add(findViewById(R.id.vehicle_make));
		list.add(findViewById(R.id.vehicle_model));
		list.add(findViewById(R.id.vehicle_year));
		list.add(findViewById(R.id.license_plate));
		list.add(findViewById(R.id.insurance_carrier));
		list.add(findViewById(R.id.policy_number));
		list.add(findViewById(R.id.insurance_phone_number));

		database.runStatement(R.raw.create_or_update_incident_report, list);

		finish();
	}

	public void populateMyInfo() {

		Cursor cursor = database.runQuery("incident_report", R.raw.query_by_id, Arrays.asList("0"));

		if (cursor.moveToNext()) {
			for (Map.Entry<String, TextView> entry : columnToTextView.entrySet()) {
				System.out.println("|||||" + entry.getKey() + " " + cursor.getColumnIndex(entry.getKey()));
				entry.getValue().setText(cursor.getString(cursor.getColumnIndex(entry.getKey())));
			}
		}

		cursor.close();
	}

	public ContactInfo createMyContactCard() {
		ContactInfo info = new ContactInfo();

		Cursor cursor = database.runQuery("incident_report", R.raw.query_by_id, Arrays.asList("0"));

		if (cursor.moveToNext()) {
			info.fullName = cursor.getString(cursor.getColumnIndex("full_name"));
			info.address1 = cursor.getString(cursor.getColumnIndex("address_1"));
			info.address2 = cursor.getString(cursor.getColumnIndex("address_2"));
			info.fullName = cursor.getString(cursor.getColumnIndex("full_name"));
			info.fullName = cursor.getString(cursor.getColumnIndex("full_name"));
		}

		return info;
	}

	public void end(View v) {
		finish();
	}
}