package com.lucky4all.xml_example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	Context mContext;
	private ListView lvNoticias;
	private AdapterListView lvAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		lvNoticias = (ListView) findViewById(R.id.lvPublicaciones);
		lvAdapter = new AdapterListView(this);
		lvNoticias.setAdapter(lvAdapter);
		lvNoticias.setOnItemClickListener(this);

		((XML_EjemploApplication) getApplication()).updateNoticias(lvAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		// Recuperamos el link de la publicación seleccionada.
		String link = lvAdapter.getItem(pos).getLink();
		
		Log.i("Error", "link: " + link + " pos: " + pos);

		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(link));
		startActivity(i);
	}
}
