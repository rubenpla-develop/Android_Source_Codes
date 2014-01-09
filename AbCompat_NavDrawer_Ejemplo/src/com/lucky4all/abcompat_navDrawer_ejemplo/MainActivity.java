package com.lucky4all.abcompat_navDrawer_ejemplo;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lucky4all.abcompat_nav_Drawer_ejemplo.R;

@SuppressLint("InlinedApi")
public class MainActivity extends ActionBarActivity {
	private String[] opcionesMenu;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	String tituloSeccion;
	ActionBarDrawerToggle drawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		opcionesMenu = new String[] { "Opción 1", "Opción 2", "Opción 3" };
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		drawerList
				.setAdapter(new ArrayAdapter<String>(
						getSupportActionBar().getThemedContext(),
						(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ? android.R.layout.simple_list_item_activated_1
								: android.R.layout.simple_list_item_checked,
						opcionesMenu));

		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {

				Fragment fragment = null;

				switch (position) {
				case 0:
					fragment = new Fragment1();
					break;
				case 1:
					fragment = new Fragment2();
					break;
				case 2:
					fragment = new Fragment3();
					break;
				}

				FragmentManager fragmentManager = getSupportFragmentManager();

				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();

				drawerList.setItemChecked(position, true);

				tituloSeccion = opcionesMenu[position];
				getSupportActionBar().setTitle(tituloSeccion);

				drawerLayout.closeDrawer(drawerList);
			}
		});

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(tituloSeccion);
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(getTitle());
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.opcion1:
			Toast.makeText(getApplicationContext(), "Has pulasado la Opcion 1",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.opcion2:
			Toast.makeText(getApplicationContext(), "Has pulasado la Opcion 2",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(),
					"Has pulasado la opcion Ajustes", Toast.LENGTH_SHORT)
					.show();
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

}
