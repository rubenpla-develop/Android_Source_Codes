package com.lucky4all.json_example;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

public class JSONApplication extends Application {
	private final String URL = "http://api.flickr.com/services/feeds/photos_public.gne?format=json";

	public void getData(Context context, ArrayAdapter<String> adapter) {
		new AsyncConector(context, adapter, URL).execute();
	}
}
