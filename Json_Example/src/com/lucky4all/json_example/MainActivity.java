package com.lucky4all.json_example;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	ListView listFotos;
	ArrayAdapter<String> lvAdapter;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		listFotos = (ListView) findViewById(R.id.lvFotos);

		lvAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_list_item_1, new ArrayList<String>());

		listFotos.setAdapter(lvAdapter);

		((JSONApplication) getApplication()).getData(this, lvAdapter);

		listFotos.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// Recuperamos el link de la publicación seleccionada.
		String link = (lvAdapter.getItem(position)).toString();

		// Creamos un intent implicito para que el sistema escoja la aplicación
		// que debe utilizar.
		Intent i = new Intent(Intent.ACTION_VIEW);

		// Le añadimos la url que debe mostrar.
		i.setData(Uri.parse(link));

		// Lanzamos la nueva Activity.
		startActivity(i);
	}

}
