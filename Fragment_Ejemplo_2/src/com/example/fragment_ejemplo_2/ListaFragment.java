package com.example.fragment_ejemplo_2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaFragment extends Fragment {

	lista_imagenes[] milista = new lista_imagenes[] {
			new lista_imagenes("Imagen 1", "Fragment 1", "drawable/img2"),
			new lista_imagenes("Imagen 2", "Fragment 2", "drawable/img3"),
			new lista_imagenes("Imagen 3", "Fragment 3", "drawable/img4"),
			new lista_imagenes("Imagen 4", "Fragment 4", "drawable/img5"),
			new lista_imagenes("Imagen 5", "Fragment 5", "drawable/img6") };

	ImagenesListener listener;

	ListView lista;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.lista_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		lista = (ListView) getView().findViewById(R.id.listView1);
		lista.setAdapter(new AdaptadorImagenes(this));

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos,
					long id) {
				if (listener != null) {
					listener.onImagen((lista_imagenes) lista.getAdapter()
							.getItem(pos));
				}
			}
		});
	}

	public interface ImagenesListener {
		void onImagen(lista_imagenes l_imagen);
	}

	public void setImagenesListener(ImagenesListener listener) {
		this.listener = listener;
	}

	class AdaptadorImagenes extends ArrayAdapter<lista_imagenes> {

		Activity context;

		AdaptadorImagenes(Fragment context) {
			super(context.getActivity(), R.layout.listitem_titulo, milista);
			this.context = context.getActivity();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.listitem_titulo, null);
			
			TextView lblDe = (TextView) item.findViewById(R.id.listItem_titulo);
			lblDe.setText(milista[position].getTitulo());
			return (item);
		}
	}
}
