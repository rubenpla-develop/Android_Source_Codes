package com.lucky4all.xml_sax_example.AsyncTask;

import java.util.List;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.lucky4all.xml_sax_example.models.Noticia;
import com.lucky4all.xml_sax_example.sax.RssParserSax;

public class XmlAsyncTask extends AsyncTask<String, Integer, Boolean> {

	private List<Noticia> noticias;
	private ArrayAdapter<Noticia> adapter;
	

	public XmlAsyncTask(ArrayAdapter<Noticia> adapter) {
		//super();
		this.adapter = adapter;
		
	}

	@Override
	protected Boolean doInBackground(String... params) {

		RssParserSax saxparser = new RssParserSax(params[0]);

		noticias = saxparser.parse();
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		for (Noticia tmp : noticias) {
			//adapter.add(tmp);
			adapter.add(tmp);
		}
		adapter.notifyDataSetChanged();
		super.onPostExecute(result);

	}

}
