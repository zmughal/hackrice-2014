package edu.uh.carvis.resource;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class ResourceLoader {

	public static String getResourceAsText(Context context, int resourceId) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));
			String line;
			StringBuilder builder = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				builder.append(line).append('\n');
			}
			return builder.toString();
		} catch (Exception ioe) {

		}

		return null;
	}
}
