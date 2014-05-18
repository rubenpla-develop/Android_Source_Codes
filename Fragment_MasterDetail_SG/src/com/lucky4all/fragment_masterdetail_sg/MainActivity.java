package com.lucky4all.fragment_masterdetail_sg;

import fragments.FragmentDetalle;
import fragments.FragmentListado;
import interfaces.CorreosListener;
import modelo.Correo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements CorreosListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		FragmentListado frgListado = (FragmentListado) getSupportFragmentManager()
				.findFragmentById(R.id.FrgListado);

		frgListado.setCorreosListener(this);

		/*
		 * if (savedInstanceState == null) {
		 * getSupportFragmentManager().beginTransaction() .add(R.id.container,
		 * new PlaceholderFragment()).commit(); }
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

	/*
	*//**
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

	@Override
	public void onCorreoSeleccionado(Correo c) {
		boolean hayDetalle = (getSupportFragmentManager().findFragmentById(
				R.id.FrgDetalle) != null);

		if (hayDetalle) {
			((FragmentDetalle) getSupportFragmentManager().findFragmentById(
					R.id.FrgDetalle)).mostrarDetalle(c.getMensaje());
		} else {
			Intent i = new Intent(this, DetalleActivity.class);
			i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getMensaje());
			startActivity(i);
		}

	}
}
