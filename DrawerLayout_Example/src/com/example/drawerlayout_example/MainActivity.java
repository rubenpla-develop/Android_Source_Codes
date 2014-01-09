package com.example.drawerlayout_example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		AdapterView.OnItemClickListener {

	private DrawerLayout mDrawer;
	private ListView mDrawerOptions;
	private static final String[] values = { "Drawer 1", "Drawer 2", "Drawer 3" };

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerOptions = (ListView) findViewById(R.id.left_drawer);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerOptions
				.setAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1,
						android.R.id.text1, values));
		mDrawerOptions.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Toast.makeText(this, "Pulsado " + values[i], Toast.LENGTH_SHORT).show();
		mDrawer.closeDrawers();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawer.isDrawerOpen(mDrawerOptions)) {
				mDrawer.closeDrawers();
			} else {
				mDrawer.openDrawer(mDrawerOptions);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
