package com.lucky4all.xml_dom_example.aSyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.lucky4all.xml_dom_example.adapter.ListViewAdapter;
import com.lucky4all.xml_dom_example.domParser.DomParser;
import com.lucky4all.xml_dom_example.model.Noticia;

public class aSyncTaskManager extends AsyncTask<String, Integer, Boolean> {

	private ArrayAdapter<Noticia> adapter;
	private List<Noticia> noticias;

	public aSyncTaskManager(ListViewAdapter lvAdapter) {
		// TODO Auto-generated constructor stub
		adapter = lvAdapter;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		DomParser parser = new DomParser(params[0]);
		noticias = parser.parse();
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		for (Noticia tmp : noticias) {
			adapter.add(tmp);
		}
		adapter.notifyDataSetChanged();
		super.onPostExecute(result);
	}

}
