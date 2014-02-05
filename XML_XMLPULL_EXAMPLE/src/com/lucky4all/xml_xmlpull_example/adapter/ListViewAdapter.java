package com.lucky4all.xml_xmlpull_example.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lucky4all.xml_xmlpull_example.R;
import com.lucky4all.xml_xmlpull_example.model.Noticia;

public class ListViewAdapter extends ArrayAdapter<Noticia> {

	private Context mContext;

	public ListViewAdapter(Context context) {
		super(context, R.layout.rss_noticia, new ArrayList<Noticia>());
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		lvHolder holder = new lvHolder();
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.rss_noticia, null);
			holder.txtTitulo = (TextView) convertView
					.findViewById(R.id.txtTitulo);
			holder.txtDesc = (TextView) convertView.findViewById(R.id.txtDesc);
			holder.txtfecha = (TextView) convertView
					.findViewById(R.id.txtFecha);
			convertView.setTag(holder);
		}
		holder = (lvHolder) convertView.getTag();
		holder.txtTitulo.setText(getItem(position).getTitulo());
		holder.txtDesc.setText(getItem(position).getDescripcion());
		holder.txtfecha.setText(getItem(position).getFecha());
		// return super.getView(position, convertView, parent);
		return convertView;
	}

}
