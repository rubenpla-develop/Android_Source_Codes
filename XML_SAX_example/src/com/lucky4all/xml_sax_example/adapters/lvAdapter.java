package com.lucky4all.xml_sax_example.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lucky4all.xml_sax_example.R;
import com.lucky4all.xml_sax_example.lvHolder;
import com.lucky4all.xml_sax_example.models.Noticia;

public class lvAdapter extends ArrayAdapter<Noticia> {

	Context mContext;

	public lvAdapter(Context context) {

		super(context, R.layout.rss_noticia, new ArrayList<Noticia>());
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		lvHolder holder;
		if (convertView == null) {
			holder = new lvHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.rss_noticia, null);
			holder.txtTitulo = (TextView) convertView
					.findViewById(R.id.txtTitulo);
			holder.txtDescripcion = (TextView) convertView
					.findViewById(R.id.txtDesc);
			holder.txtFecha = (TextView) convertView
					.findViewById(R.id.txtFecha);
			convertView.setTag(holder);
		}

		holder = (lvHolder) convertView.getTag();
		holder.txtTitulo.setText(getItem(position).getTitulo());
		holder.txtDescripcion.setText(getItem(position).getDescripcion());
		holder.txtFecha.setText(getItem(position).getFecha());

		return convertView;
	}

}
