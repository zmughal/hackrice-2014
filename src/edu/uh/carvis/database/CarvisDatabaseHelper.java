package edu.uh.carvis.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import edu.uh.carvis.R;
import edu.uh.carvis.resource.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarvisDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "carvis";
	private static final int DATABASE_VERSION = 1;

	private String createScript;
	private Context context;

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

	public void runStatement(int resourceId, List<TextView> params) {
		String sqlScript = ResourceLoader.getResourceAsText(context, resourceId);

		List<String> paramList = new ArrayList<String>();
		for (TextView view : params) {
			paramList.add(view.getText().toString());
		}

		getWritableDatabase().execSQL(sqlScript, paramList.toArray());
	}
}
