package com.example.contentprovider_example1;

import com.example.contentprovider_example1.Adapters.LVadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	private Context mContext;
	private ListView lvClientes;
	private LVadapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		//registros = ((ContentProviderApplication) getApplication())
		//		.getResultados();

		lvClientes = (ListView) findViewById(R.id.lvClientes);

		LVadapter adapter = new LVadapter(this);
		lvClientes.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
