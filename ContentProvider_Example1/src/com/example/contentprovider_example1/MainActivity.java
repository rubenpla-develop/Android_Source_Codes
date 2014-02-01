package com.example.contentprovider_example1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.contentprovider_example1.Adapters.LVadapter;
import com.example.contentprovider_example1.DataBase.ColumnasTabla;
import com.example.contentprovider_example1.dialogs.Custom_Dialog2;
import com.example.contentprovider_example1.provider.ClientesProvider;

public class MainActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private Context mContext;
	private ListView lvClientes;
	@SuppressWarnings("unused")
	private LVadapter adapter;
	private Button btnQuery, btnInsert, btnDelete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		// registros = ((ContentProviderApplication) getApplication())
		// .getResultados();

		lvClientes = (ListView) findViewById(R.id.lvClientes);
		btnQuery = (Button) findViewById(R.id.btnAceptar);
		btnInsert = (Button) findViewById(R.id.button2);
		btnDelete = (Button) findViewById(R.id.button3);

		btnQuery.setOnClickListener(this);
		btnInsert.setOnClickListener(this);
		btnDelete.setOnClickListener(this);

		LVadapter adapter = new LVadapter(this);
		lvClientes.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		String[] projection = new String[] { ColumnasTabla._ID,
				ColumnasTabla.NOMBRE, ColumnasTabla.EMAIL };
		Uri clientesUri = ClientesProvider.CONTENT_URI;
		ContentResolver content_resolver = getContentResolver();

		switch (v.getId()) {
		case (R.id.btnAceptar):

			final Custom_Dialog2 miDialog = new Custom_Dialog2(this);
			Cursor c = content_resolver.query(clientesUri, projection, null,
					null, null);
			String nombre,
			email,
			resultados = "";
			TextView txtTitulo,
			txtResult;
			Button btnAceptar;
			txtTitulo = (TextView) miDialog.findViewById(R.id.txtOpcion);
			txtResult = (TextView) miDialog.findViewById(R.id.txtContent);
			btnAceptar = (Button) miDialog.findViewById(R.id.btnAceptar);
			btnAceptar.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					miDialog.cancel();
				}
			});

			if (c.moveToFirst()) {
				do {
					nombre = c
							.getString(c.getColumnIndex(ColumnasTabla.NOMBRE));
					email = c.getString(c.getColumnIndex(ColumnasTabla.EMAIL));
					resultados = resultados + (nombre + " - " + email + "\n");
				} while (c.moveToNext());
			}

			txtTitulo.setText("Lista ContentProvider");
			txtResult.setText(resultados);
			Log.i("debug", resultados);
			miDialog.show();

		case (R.id.button2):
			ContentValues values = new ContentValues();

			values.put(ColumnasTabla.NOMBRE, "ClienteN");
			values.put(ColumnasTabla.EMAIL, "nuevo@email.com");

			ContentResolver cr = getContentResolver();

			cr.insert(ClientesProvider.CONTENT_URI, values);

		case (R.id.button3):

			content_resolver.delete(ClientesProvider.CONTENT_URI,
					ColumnasTabla.NOMBRE + " = 'ClienteN'", null);

		}

	}

}
