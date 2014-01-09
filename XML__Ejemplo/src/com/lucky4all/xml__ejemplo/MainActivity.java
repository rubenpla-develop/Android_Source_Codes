package com.lucky4all.xml__ejemplo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity implements OnItemClickListener {
	private ListView lvPublicaciones;
	private AdapterLVMain lvAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lvAdapter = new AdapterLVMain(this);

		lvPublicaciones = (ListView) findViewById(R.id.lvPublicaciones);
		lvPublicaciones.setAdapter(lvAdapter);
		lvPublicaciones.setOnItemClickListener(this);

		((XML_EjemploApplication) getApplication())
				.updatePublicaciones(lvAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		// Recuperamos el link de la publicación seleccionada.
		String link = lvAdapter.getItem(position).getLink();
		
		// Creamos un intent implicito para que el sistema escoja la aplicación
		// que debe utilizar.
		Intent i = new Intent(Intent.ACTION_VIEW);
		
		// Le añadimos la url que debe mostrar.
		i.setData(Uri.parse(link));
		
		// Lanzamos la nueva Activity.
		startActivity(i);
	}
}
