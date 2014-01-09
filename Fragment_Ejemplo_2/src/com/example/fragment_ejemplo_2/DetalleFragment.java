package com.example.fragment_ejemplo_2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.detalle_fragment, container, false);
	}

	public void mostrar_fragment_detalle(Context c, String drawable, String pie) {
        
		ImageView imagen_fragment = (ImageView) getView().findViewById(
				R.id.imagen);
		 int imageResource = c.getResources().getIdentifier(drawable, null, c.getPackageName());
		    imagen_fragment.setImageDrawable(c.getResources().getDrawable(imageResource));
		TextView txtPie = (TextView) getView().findViewById(R.id.pie_imagen);

		txtPie.setText(pie);
	}
}
