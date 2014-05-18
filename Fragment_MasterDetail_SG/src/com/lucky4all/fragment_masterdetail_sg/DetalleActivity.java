package com.lucky4all.fragment_masterdetail_sg;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import fragments.FragmentDetalle;

public class DetalleActivity extends FragmentActivity {

	public static final String EXTRA_TEXTO = "com.lucky4all.fragment_masterdetail_sg";

	@Override
	protected void onCreate(Bundle state) {
		// TODO Auto-generated method stub
		super.onCreate(state);
		setContentView(R.layout.detalle_activity);

		FragmentDetalle detalle = (FragmentDetalle) getSupportFragmentManager()
				.findFragmentById(R.id.FrgDetalle);

		detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
	}
}
