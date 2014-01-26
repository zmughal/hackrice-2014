package edu.uh.carvis.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.uh.carvis.R;
import edu.uh.carvis.resource.ResourceLoader;

public class CarvisDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "carvis";
	private static final int DATABASE_VERSION = 1;

	private String createScript;

	public CarvisDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		createScript = ResourceLoader.getResourceAsText(context, R.raw.create_table);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createScript);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
