package adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class Lista_adaptador extends BaseAdapter {

	private ArrayList<?> entradas;
	private int R_layout_IdView;
	private Context context;

	public Lista_adaptador(Context context, int r_layout_IdView,
			ArrayList<?> entradas) {
		super();
		this.context = context;
		R_layout_IdView = r_layout_IdView;
		this.entradas = entradas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entradas.size();
	}

	@Override
	public Object getItem(int posicion) {
		// TODO Auto-generated method stub
		return entradas.get(posicion);
	}

	@Override
	public long getItemId(int posicion) {
		// TODO Auto-generated method stub
		return posicion;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R_layout_IdView, null);
		}
		onEntrada(entradas.get(position), view);
		return view;
	}

	public abstract void onEntrada(Object entrada, View view);
}
