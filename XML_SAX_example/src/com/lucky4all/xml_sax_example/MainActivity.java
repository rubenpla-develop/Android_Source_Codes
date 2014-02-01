package com.lucky4all.xml_sax_example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.lucky4all.xml_sax_example.AsyncTask.XmlAsyncTask;
import com.lucky4all.xml_sax_example.adapters.lvAdapter;

public class MainActivity extends Activity implements OnClickListener {

	Button btnCargar;
	ListView lvFeeds;
	lvAdapter rss_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnCargar = (Button) findViewById(R.id.btn1);
		lvFeeds = (ListView) findViewById(R.id.lvFeeds);
		rss_adapter = new lvAdapter(this);
		lvFeeds.setAdapter(rss_adapter);
		btnCargar.setOnClickListener(this);
		lvFeeds.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int pos,
					long id) {
				try {

					Log.i("DEBUG", "LINK : "
							+ rss_adapter.getItem(pos).getLink().toString());
					String link = rss_adapter.getItem(pos).getLink().toString();
					// String link = "http://www.google.es";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(link));
					startActivity(i);
				} catch (Exception e) {
					Log.i("debug","error : " +  e);

				}
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		// switch (v.getId()) {
		// case (R.id.btn1):
		Log.i("debug", "EJECUTANDO HILO SECUNDARIO");
		XmlAsyncTask cargar_rss = new XmlAsyncTask(rss_adapter);
		cargar_rss.execute("http://www.europapress.es/rss/rss.aspx");
		Log.i("debug", " HILO EJECUTADO");
		// break;
		// default:
		// Log.i("debug", "NADA");

		// }

	}

}
