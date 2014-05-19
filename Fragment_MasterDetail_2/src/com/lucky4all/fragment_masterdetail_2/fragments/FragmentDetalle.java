package com.lucky4all.fragment_masterdetail_2.fragments;

import modelo.Lista_Contenido;
import modelo.Lista_entrada;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucky4all.fragment_masterdetail_2.R;

public class FragmentDetalle extends Fragment {

	public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id";
	private Lista_entrada mItem;

	public FragmentDetalle() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)) {
			// Cargamos contenido entrada con el ID correspondiente.
			// Recomendable uso de Loader
			mItem = Lista_Contenido.ENTRADAS_LISTA_HASHMAP.get(getArguments()
					.getString(ARG_ID_ENTRADA_SELECCIONADA));
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frame_detalle, container,
				false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.txtTitulo_Detalle))
					.setText(mItem.txtTituloDetalle);
			((TextView) rootView.findViewById(R.id.txtDesc_Detalle))
					.setText(mItem.txtDescDetalle);
			((ImageView) rootView.findViewById(R.id.imgImagen_Detalle))
					.setImageResource(mItem.idImagen);
		}
		return rootView;
	};

}
