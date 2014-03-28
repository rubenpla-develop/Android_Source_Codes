package com.lucky4all.geolocation_example_1;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
	LocationManager mi_loc;
	LocationProvider provider;
	List<String> list_geo_providers;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		mi_loc = (LocationManager) getSystemService(LOCATION_SERVICE);
		// list_geo_providers = mi_loc.getAllProviders();
		//
		// provider = mi_loc.getProvider(list_geo_providers.get(0));
		// int precision = provider.getAccuracy();
		// boolean latitud = provider.supportsAltitude();
		// int recursos = provider.getPowerRequirement();
		//
		// Criteria req = new Criteria();
		// req.setAccuracy(Criteria.ACCURACY_FINE);
		// req.setAltitudeRequired(true);
		//
		// String best_prov_criteria = mi_loc.getBestProvider(req, false);
		//
		// List<String> list_prov_criteria = mi_loc.getProviders(req, false);
		//
		// if (mi_loc.isProviderEnabled(LocationManager.GPS_PROVIDER) == false)
		// Toast.makeText(mContext, "GPS Desactivado", Toast.LENGTH_LONG)
		// .show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
