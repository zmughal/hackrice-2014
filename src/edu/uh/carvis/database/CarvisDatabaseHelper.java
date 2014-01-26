package edu.uh.carvis.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import edu.uh.carvis.R;
import edu.uh.carvis.resource.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

public class CarvisDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "carvis";
	private static final int DATABASE_VERSION = 1;

	private String createScript;
	private Context context;

	public static final String[] INCIDENT_REPORT_COLUMNS = new String[]{
			"_id",
			"full_name",
			"address_1",
			"address_2",
			"city",
			"state",
			"zipcode",
			"phone",
			"email",
			"vehicle_make",
			"vehicle_model",
			"vehicle_year",
			"license_plate",
			"insurance_carrier",
			"policy_number",
			"insurance_phone_number",
			"date_created"
	};

	public CarvisDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		createScript = ResourceLoader.getResourceAsText(context, R.raw.create_table);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createScript);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void runStatement(int resourceId, List<Object> params) {
		String sqlScript = ResourceLoader.getResourceAsText(context, resourceId);

		List<String> paramList = new ArrayList<String>();
		for (Object obj : params) {
			if (obj instanceof TextView) {
				paramList.add(((TextView) obj).getText().toString());
			} else {
				paramList.add(obj.toString());
			}
		}

		getWritableDatabase().execSQL(sqlScript, paramList.toArray());
	}

	public Cursor runQuery(String tableName, int resourceId, List<?> params) {
		List<String> strings = new ArrayList<String>();
		for (Object obj : params) {
			if (obj instanceof TextView) {
				strings.add(((TextView) obj).getText().toString());
			} else {
				strings.add(obj.toString());
			}
		}

		return getReadableDatabase().query(tableName,
				INCIDENT_REPORT_COLUMNS,
				ResourceLoader.getResourceAsText(context, resourceId),
				(String[]) params.toArray(),
				null, null, null);
	}
}
