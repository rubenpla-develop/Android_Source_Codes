package com.lucky4all.json_example;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class AsyncConector extends AsyncTask<Void, Void, Void> {
	private ArrayList<String> data;
	private ArrayAdapter<String> adapter;
	private String URL;
	private ProgressDialog pd;
	private Context context;

	public AsyncConector(Context context, ArrayAdapter<String> adapter, String URL) {
		this.adapter = adapter;
		this.URL = URL;
		this.context = context;
		pd = new ProgressDialog(context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		pd.setIndeterminate(true);
		pd.setMessage("Loading Data");
		pd.setTitle("Proceso AsyncTask");
		pd.show();
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		ConectorHttpJSON conector = new ConectorHttpJSON(URL);

		try {
			JSONObject jsObject = conector.execute();
			data = new JSONtoStringCollection(jsObject).getArrayList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		for (String tmp : data)
			adapter.add(tmp);
		super.onPostExecute(result);

		adapter.notifyDataSetChanged();

		pd.dismiss();
	}

}
