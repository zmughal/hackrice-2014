package edu.uh.carvis.fuel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import edu.uh.carvis.CaptureActivity;
import edu.uh.carvis.R;

/**
 * Created by Tanmay_Local on 1/26/14.
 */
public class FuelCalculatorActivity extends Activity {

    public static final int FUEL_OCR=1;
    public static final int ODOMETER_OCR=2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_reading);

    }

    public void fuel_ocr()
    {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, CaptureActivity.RESULT_ID+FUEL_OCR);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==CaptureActivity.RESULT_ID+FUEL_OCR )
        {
            ((EditText)findViewById(R.id.fuel_text)).setText(data.getStringExtra(CaptureActivity.EXTRA_OCR_RESULT));
        }
        else if (requestCode==CaptureActivity.RESULT_ID+ODOMETER_OCR)
        {
            ((EditText)findViewById(R.id.odometer_text)).setText(data.getStringExtra(CaptureActivity.EXTRA_OCR_RESULT));
        }
    }

    public void odometer_ocr()
    {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, CaptureActivity.RESULT_ID+ODOMETER_OCR);
    }
}