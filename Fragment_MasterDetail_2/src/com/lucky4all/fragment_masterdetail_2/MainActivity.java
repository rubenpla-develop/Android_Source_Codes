package com.lucky4all.fragment_masterdetail_2;

import interfaces.Callbacks;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lucky4all.fragment_masterdetail_2.fragments.FragmentDetalle;

public class MainActivity extends FragmentActivity implements Callbacks {

	boolean hayDetalle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frg_listado);

		if (findViewById(R.id.frame_container_detalle) != null) {
			hayDetalle = true;
		}
		/*
		 * if (savedInstanceState == null) {
		 * getFragmentManager().beginTransaction() .add(R.id.container, new
		 * PlaceholderFragment()).commit(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onEntradaSeleccionada(String id) {
		if (hayDetalle) {
			Bundle arguments = new Bundle();
			arguments
					.putString(FragmentDetalle.ARG_ID_ENTRADA_SELECCIONADA, id);
			FragmentDetalle fragment = new FragmentDetalle();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.frame_container_detalle, fragment).commit();
		} else {
			Intent detailIntent = new Intent(this, ActivityDetalle.class);
			detailIntent.putExtra(FragmentDetalle.ARG_ID_ENTRADA_SELECCIONADA,
					id);
			startActivity(detailIntent);
		}

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_main, container, false); return
	 * rootView; } }
	 */

}
