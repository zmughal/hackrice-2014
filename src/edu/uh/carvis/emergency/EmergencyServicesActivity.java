package edu.uh.carvis.emergency;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import edu.uh.carvis.R;

/**
 * User: Eric
 * Date: 1/25/14
 */
public class EmergencyServicesActivity extends Activity {

	private SeekBar seekBar;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.emergency_services);

		seekBar = (SeekBar)findViewById(R.id.dial911);
		seekBar.setOnSeekBarChangeListener(new Dial911Listener());
	}

	public void newIncident(View v) {
		Intent intent = new Intent(this, IncidentReportActivity.class);
		intent.putExtra(IncidentReportActivity.EXTRA_ACTIVITY_TYPE, IncidentReportActivity.ACTIVITY_TYPE_NEW);

		startActivity(intent);
	}

	public void editMyInfo(View v) {
		Intent intent = new Intent(this, IncidentReportActivity.class);
		intent.putExtra(IncidentReportActivity.EXTRA_ACTIVITY_TYPE, IncidentReportActivity.ACTIVITY_TYPE_EDIT_ME);

		startActivity(intent);
	}

	public void viewMyInfo(View v) {
		Intent intent = new Intent(this, IncidentReportActivity.class);
		intent.putExtra(IncidentReportActivity.EXTRA_ACTIVITY_TYPE, IncidentReportActivity.ACTIVITY_TYPE_VIEW_ME);

		startActivity(intent);
	}

	public void viewExistingIncidents(View v) {
		Intent intent = new Intent(this, IncidentReportActivity.class);
		intent.putExtra(IncidentReportActivity.EXTRA_ACTIVITY_TYPE, IncidentReportActivity.ACTIVITY_TYPE_EXISTING);

		startActivity(intent);
	}


	class Dial911Listener implements SeekBar.OnSeekBarChangeListener {

		private static final double percentage = 0.85;

		private double getPercentage(SeekBar seekBar, int progress) {
			return ((double)progress) / seekBar.getMax();
		}

		private void dial911() {
			Uri uri = Uri.parse("tel://2817815206");
			Intent intent = new Intent(Intent.ACTION_CALL, uri);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			startActivity(intent);
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if (getPercentage(seekBar, progress) > percentage) {
				dial911();
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			seekBar.setProgress(0);
		}
	}
}