package com.lucky4all.xml_example;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class AsyncConector extends AsyncTask<Void, Void, Void> {
	
	private ArrayList<Publicacion> publicaciones;
	private ArrayAdapter<Publicacion> adapter;
	private String url;
	
	public AsyncConector(ArrayAdapter<Publicacion> adapter, String url) {
		this.adapter = adapter;
		this.url = url;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		ConectorHttpXML conector = new ConectorHttpXML(url);
		
		try{
				publicaciones = conector.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		for (Publicacion tmp : publicaciones){
			adapter.add(tmp);
		}
				
		adapter.notifyDataSetChanged();
		super.onPostExecute(result);
	}
}
