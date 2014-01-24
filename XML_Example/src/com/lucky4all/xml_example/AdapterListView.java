package com.lucky4all.xml_example;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterListView extends ArrayAdapter<Publicacion> {

	Context mContext;

	public AdapterListView(Context context) {
		super(context, R.layout.item_noticia, new ArrayList<Publicacion>());
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.getView(position, convertView, parent);
		TextView holder;
		if (convertView == null) {

			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_noticia, null);
			holder = (TextView) convertView.findViewById(R.id.txtNoticia);

			convertView.setTag(holder);
		}

		holder = (TextView) convertView.getTag();
		holder.setText(getItem(position).getTitulo());

		return convertView;
	}

}
