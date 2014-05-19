package com.lucky4all.fragment_masterdetail_2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lucky4all.fragment_masterdetail_2.fragments.FragmentDetalle;

public class ActivityDetalle extends FragmentActivity {

	@Override
	protected void onCreate(Bundle state) {
		// TODO Auto-generated method stub
		super.onCreate(state);
		setContentView(R.layout.frg_detalle);

		// Comprobamos que no hayamos entrado en esta actividad
		if (state == null) {
			Bundle arguments = new Bundle();
			arguments.putString(
					FragmentDetalle.ARG_ID_ENTRADA_SELECCIONADA,
					getIntent().getStringExtra(
							FragmentDetalle.ARG_ID_ENTRADA_SELECCIONADA));
			FragmentDetalle fragment = new FragmentDetalle();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.frame_container_detalle, fragment).commit();

		}
	}

}
