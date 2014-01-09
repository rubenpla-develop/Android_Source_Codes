package com.example.fragment_ejemplo_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.example.fragment_ejemplo_2.ListaFragment.ImagenesListener;

public class MainActivity extends FragmentActivity implements ImagenesListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListaFragment frgListado = (ListaFragment) getSupportFragmentManager()
				.findFragmentById(R.id.FrgListado);

		frgListado.setImagenesListener(this);
	}

	public void onImagen(lista_imagenes limagenes) {
		boolean hayDetalle = (getSupportFragmentManager().findFragmentById(
				R.id.FrgDetalle) != null);

		if (hayDetalle) {
			((DetalleFragment) getSupportFragmentManager().findFragmentById(
					R.id.FrgDetalle)).mostrar_fragment_detalle(this, limagenes.getImagen(),limagenes
					.getPie());
		} else {
			Intent i = new Intent(this, DetalleActivity.class);
			i.putExtra(DetalleActivity.EXTRA_TEXTO, limagenes.getPie());
			i.putExtra("imagen", limagenes.getImagen());
			startActivity(i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
