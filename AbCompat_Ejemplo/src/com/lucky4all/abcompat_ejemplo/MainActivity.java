package com.lucky4all.abcompat_ejemplo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

}
