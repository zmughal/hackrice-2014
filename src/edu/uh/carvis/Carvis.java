package edu.uh.carvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Carvis extends Activity {
	public static final int REQUEST_OCR_IMAGE = "ocr".hashCode();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		findViewById(R.id.button).setOnClickListener(new ButtonOnClickListener());
	}

	class ButtonOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Carvis.this, CaptureActivity.class);
			startActivityForResult(intent, REQUEST_OCR_IMAGE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_OCR_IMAGE) {
			System.out.println("|||||" + data.getStringExtra(CaptureActivity.EXTRA_OCR_RESULT));
		}
	}
}