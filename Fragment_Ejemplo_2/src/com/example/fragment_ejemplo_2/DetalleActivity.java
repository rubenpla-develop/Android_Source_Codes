package com.example.fragment_ejemplo_2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetalleActivity extends FragmentActivity {

	public static final String EXTRA_TEXTO = "com.example.Fragment_Ejemplo_2.EXTRA_TEXTO";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);

		DetalleFragment detalle = (DetalleFragment) getSupportFragmentManager()
				.findFragmentById(R.id.FrgDetalle);

		detalle.mostrar_fragment_detalle(this, getIntent().getStringExtra("imagen"),
				getIntent().getStringExtra(EXTRA_TEXTO));
	}
}