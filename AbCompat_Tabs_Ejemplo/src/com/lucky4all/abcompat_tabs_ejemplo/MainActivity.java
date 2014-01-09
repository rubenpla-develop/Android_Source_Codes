package com.lucky4all.abcompat_tabs_ejemplo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Obtenemos una referencia a la actionbar
		android.support.v7.app.ActionBar abar = getSupportActionBar();

		// Establecemos el modo de navegaci�n por pesta�as
		abar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);

		// Ocultamos si queremos el t�tulo de la actividad
		// abar.setDisplayShowTitleEnabled(false);

		// Creamos las pesta�as
		ActionBar.Tab tab1 = abar.newTab().setText("Tab 1");

		ActionBar.Tab tab2 = abar.newTab().setText("Tab 2");

		// Creamos los fragments de cada pesta�a
		Fragment1 tab1frag = new Fragment1();
		fragment2 tab2frag = new fragment2();

		// Asociamos los listener a las pesta�as
		tab1.setTabListener(new MiTabListener(tab1frag));
		tab2.setTabListener(new MiTabListener(tab2frag));

		// A�adimos las pesta�as a la action bar
		abar.addTab(tab1);
		abar.addTab(tab2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
