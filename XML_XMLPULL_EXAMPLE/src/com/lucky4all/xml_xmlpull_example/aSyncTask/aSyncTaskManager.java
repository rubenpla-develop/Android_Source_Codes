package com.lucky4all.xml_xmlpull_example.aSyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.lucky4all.xml_xmlpull_example.adapter.ListViewAdapter;
import com.lucky4all.xml_xmlpull_example.model.Noticia;
import com.lucky4all.xml_xmlpull_example.xmlPullParser.RssXmlPull;

public class aSyncTaskManager extends AsyncTask<String, Integer, Boolean> {

	private ArrayAdapter<Noticia> adapter;
	private List<Noticia> noticias;

	public aSyncTaskManager(ListViewAdapter lvAdapter) {
		// TODO Auto-generated constructor stub
		adapter = lvAdapter;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		Log.i("debug","paso 1");
		RssXmlPull parser = new RssXmlPull(params[0]);
		Log.i("debug","paso 2");
		noticias = parser.parse();
		Log.i("debug","paso 3");
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		Log.i("debug","paso 4");
		for (Noticia tmp : noticias) {
			adapter.add(tmp);
		}
		Log.i("debug","paso 5");
		adapter.notifyDataSetChanged();
		Log.i("debug","paso 6");
		super.onPostExecute(result);
		Log.i("debug","paso 7");
	}

}
