package com.lucky4all.geolocation_basic_example;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {

	private TextView lblLat;
	private TextView lblLen;
	private TextView lblAccu;
	private TextView lblNetwork;
	private TextView lblGps;
	private TextView lblPassive;

	private Button btnUpdate;
	private ToggleButton tglNetwork;
	private ToggleButton tglGps;
	private ToggleButton tglPassive;

	private LocationManager mLocationManager;
	private Location loc;
	private LocationListener mLocationListener;
	private String provider = null;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		lblLat = (TextView) findViewById(R.id.lbl_lat);
		lblLen = (TextView) findViewById(R.id.lbl_len);
		lblAccu = (TextView) findViewById(R.id.lbl_Accu);
		lblNetwork = (TextView) findViewById(R.id.lblNetwork);
		lblGps = (TextView) findViewById(R.id.lblGps);
		lblPassive = (TextView) findViewById(R.id.lblPassive);
		btnUpdate = (Button) findViewById(R.id.btnActualizar);
		tglNetwork = (ToggleButton) findViewById(R.id.tglNetwork);
		tglGps = (ToggleButton) findViewById(R.id.tglGps);
		tglPassive = (ToggleButton) findViewById(R.id.tglPassive);

		tglNetwork.setOnClickListener(this);
		tglGps.setOnClickListener(this);
		tglPassive.setOnClickListener(this);

		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		provider = LocationManager.GPS_PROVIDER;
		// loc = mLocationManager.getLastKnownLocation(provider);
		// Log.i("DEBUG", "location loc: " + loc);

		mLocationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				loc = location;
				Toast.makeText(mContext, "Ubication changed", Toast.LENGTH_LONG)
						.show();

			}

			@Override
			public void onProviderDisabled(String prov) {
				Log.i("onProviderDisabled", prov + " DISABLED!");
			}

			@Override
			public void onProviderEnabled(String prov) {
				Log.i("onProviderEnabled", prov + " ENABLED!");
			}

			@Override
			public void onStatusChanged(String prov, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}
		};

		btnUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if ((tglNetwork.isChecked() || tglGps.isChecked() || tglPassive
						.isChecked()) && loc != null) {
					lblAccu.setText(String.valueOf(loc.getAccuracy()));
					lblLat.setText(String.valueOf(loc.getLatitude()));
					lblLen.setText(String.valueOf(loc.getAltitude()));
				} else {
					Toast.makeText(mContext,
							"Provider Not Available or not selected",
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.tglNetwork):
			tglGps.setChecked(false);
			tglPassive.setChecked(false);
			provider = LocationManager.NETWORK_PROVIDER;

			loc = mLocationManager.getLastKnownLocation(provider);
			Log.i("LOCATION", "location " + loc);

			break;
		case (R.id.tglGps):
			tglNetwork.setChecked(false);
			tglPassive.setChecked(false);
			provider = LocationManager.GPS_PROVIDER;

			loc = mLocationManager.getLastKnownLocation(provider);
			Log.i("LOCATION", "location " + loc);

			break;
		case (R.id.tglPassive):
			tglGps.setChecked(false);
			tglNetwork.setChecked(false);
			provider = LocationManager.PASSIVE_PROVIDER;

			loc = mLocationManager.getLastKnownLocation(provider);
			Log.i("LOCATION", "location " + loc);

			break;
		}

	}

	@Override
	protected void onPause() {
		mLocationManager.removeUpdates(mLocationListener);

		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		mLocationManager.requestLocationUpdates(provider, 30000, 0,
				mLocationListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
