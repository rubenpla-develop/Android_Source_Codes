package com.example.contentprovider_example1.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.contentprovider_example1.R;
import com.example.contentprovider_example1.application.ContentProviderApplication;
import com.example.contentprovider_example1.models.Cliente;


public class LVadapter extends ArrayAdapter<Cliente> {

	private Context mContext;
	private ArrayList<Cliente> datos;

	public LVadapter(Context context) {
		super(context, R.layout.cliente, ((ContentProviderApplication) context
				.getApplicationContext()).getResultados());
		mContext = context;
		datos = ((ContentProviderApplication) mContext.getApplicationContext())
				.getResultados();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.cliente, null);

			holder = new HolderView();
			holder.txtNombre = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.txtEmail = (TextView) convertView
					.findViewById(R.id.textView2);

			convertView.setTag(holder);
		}

		holder = (HolderView) convertView.getTag();
		holder.txtNombre.setText(datos.get(position).getcNombre());
		holder.txtEmail.setText(datos.get(position).getcEmail());

		return convertView;
	}

	private class HolderView {
		TextView txtNombre, txtEmail;
	}
}
