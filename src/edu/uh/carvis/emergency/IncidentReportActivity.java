package edu.uh.carvis.emergency;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import edu.uh.carvis.R;
import edu.uh.carvis.database.CarvisDatabaseHelper;

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
		
	}
}