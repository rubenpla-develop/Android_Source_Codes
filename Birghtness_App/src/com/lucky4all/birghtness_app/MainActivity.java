package com.lucky4all.birghtness_app;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private SeekBar brightBar;
	private TextView lblPercent;
	private int bright;
	private ContentResolver c_resolver;
	private Window window;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lblPercent = (TextView) findViewById(R.id.bright_percent);
		brightBar = (SeekBar) findViewById(R.id.seek_brightness);
		c_resolver = getContentResolver();
		window = getWindow();

		brightBar.setMax(255);
		brightBar.setKeyProgressIncrement(1);

		try {
			System.putInt(c_resolver, System.SCREEN_BRIGHTNESS_MODE,
					System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			bright = System.getInt(c_resolver, System.SCREEN_BRIGHTNESS);
			Log.i("onStopTracking", "Brillo: " + bright + "\nOTRO BRILLO: "
					+ (bright / 2.55f));
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		brightBar.setProgress(bright);
		lblPercent.setText(String.valueOf((Math.round(bright / 2.55f))) + "%");
		brightBar.setOnSeekBarChangeListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromuser) {
		if (bright < 10) {
			bright = 10;
		} else {

			bright = progress;
			/*
			 * lblPercent.setText(String.valueOf((Math.round(bright / 2.55f))) +
			 * "%");
			 */
			float perc = (bright / (float) 255) * 100;
			lblPercent.setText((int) perc + "%");
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Log.i("onStopTracking", "onStopTracking: " + bright + "\nOTRO BRILLO: "
				+ (bright / 2.55f));

		System.putInt(c_resolver, System.SCREEN_BRIGHTNESS, bright);

		LayoutParams params = window.getAttributes();
		params.screenBrightness = bright / (float) 255;
		window.setAttributes(params);

	}
}
