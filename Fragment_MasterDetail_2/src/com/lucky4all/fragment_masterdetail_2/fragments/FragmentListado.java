package com.lucky4all.fragment_masterdetail_2.fragments;

import interfaces.Callbacks;
import modelo.Lista_Contenido;
import modelo.Lista_entrada;
import adapter.Lista_adaptador;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lucky4all.fragment_masterdetail_2.R;

public class FragmentListado extends ListFragment {

	private Callbacks mCallbacks = CallbacksVacios;

	private static Callbacks CallbacksVacios = new Callbacks() {
		@Override
		public void onEntradaSeleccionada(String id) {

		}

	};

	public FragmentListado() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setListAdapter(new Lista_adaptador(getActivity(), R.layout.list_item,
				Lista_Contenido.ENTRADAS_LISTA) {

			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {

					TextView texto_titulo = (TextView) view
							.findViewById(R.id.txtTitulo_Listado);
					if (texto_titulo != null) {
						texto_titulo
								.setText(((Lista_entrada) entrada).txtTituloDetalle);
					}

					ImageView imagen_detalle = (ImageView) view
							.findViewById(R.id.imgImagen_listado);
					if (imagen_detalle != null) {
						imagen_detalle
								.setImageResource(((Lista_entrada) entrada).idImagen);
					}
				}
			}

		});

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Error: La actividad debe implementar el callback del fragmento");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {

		super.onDetach();
		mCallbacks = CallbacksVacios;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {

		super.onListItemClick(listView, view, position, id);

		mCallbacks.onEntradaSeleccionada(Lista_Contenido.ENTRADAS_LISTA
				.get(position).id);
	}

}
