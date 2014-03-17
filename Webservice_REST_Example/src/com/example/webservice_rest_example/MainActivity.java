package com.example.webservice_rest_example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Context mContext;
	Button btnIns, btnUp, btnDel, btnCli, btnList;
	EditText txtId, txtNombre, txtTelefono;
	ListView lvClientes;
	String TAG = "LOGDEBUG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		btnIns = (Button) findViewById(R.id.btnIns);
		btnUp = (Button) findViewById(R.id.btnUp);
		btnDel = (Button) findViewById(R.id.btnDel);
		btnList = (Button) findViewById(R.id.btnList);
		btnCli = (Button) findViewById(R.id.btnCli);
		txtId = (EditText) findViewById(R.id.txtId);
		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtTelefono = (EditText) findViewById(R.id.txtTelefono);
		lvClientes = (ListView) findViewById(R.id.lvClientes);

		btnIns.setOnClickListener(this);
		btnUp.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		btnCli.setOnClickListener(this);
		btnList.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.btnIns):
			TareaWSInsert InsertTask = new TareaWSInsert();
			InsertTask.execute(txtNombre.getText().toString(), txtTelefono
					.getText().toString());
			break;
		case (R.id.btnUp):
			TareaWSUpdate UpdateTask = new TareaWSUpdate();
			UpdateTask.execute(txtId.getText().toString(), txtNombre.getText()
					.toString(), txtTelefono.getText().toString());
			break;
		case (R.id.btnDel):
			TareaWSDelete DeleteTask = new TareaWSDelete();
			DeleteTask.execute(txtId.getText().toString());
			break;
		case (R.id.btnCli):
			TareaWSClient ClientTask = new TareaWSClient();
			ClientTask.execute(txtId.getText().toString());
			break;
		case (R.id.btnList):
			TareaWSConsult ListTask = new TareaWSConsult();
			ListTask.execute();
			break;
		}
	}

	class TareaWSInsert extends AsyncTask<String, Integer, Boolean> {

		String link = "http://10.0.2.2:49498/Api/Clientes/Cliente";

		@Override
		protected Boolean doInBackground(String... params) {
			boolean result = true;

			// crear un nuevo objeto HttpClient, que será el encargado de
			// realizar la comunicación HTTP con el servidor a partir de los
			// datos que nosotros le proporcionemos.
			HttpClient httpClient = new DefaultHttpClient();
			// crearemos la petición POST creando un nuevo objeto HttpPost e
			// indicando la URL de llamada al servicio
			HttpPost post = new HttpPost(link);
			// Modificaremos el atributo http content-type
			// para indicar que el formato de los datos que utilizaremos en la
			// comunicación será JSON (MIME-Type es “application/json“).
			post.setHeader("content-type", "application/json");

			try {
				JSONObject datos = new JSONObject();

				datos.put("Nombre", params[0]);
				datos.put("Telefono", Integer.parseInt(params[1]));

				// asociaremos este objeto JSON a nuestra petición HTTP
				// convirtiéndolo primero al tipo StringEntity e incluyéndolo
				// finalmente en la petición mediante el método setEntity().
				StringEntity entity = new StringEntity(datos.toString());
				post.setEntity(entity);

				HttpResponse resp = httpClient.execute(post);
				String respStr = EntityUtils.toString(resp.getEntity());

				if (respStr.equals("true")) {
					Toast.makeText(mContext, "INSERT OK!", Toast.LENGTH_SHORT)
							.show();
				} else if (!respStr.equals("true")) {
					Toast.makeText(mContext, "INSERT ERROR!",
							Toast.LENGTH_SHORT).show();
					result = false;
				}

			} catch (Exception e) {
				result = false;
			}
			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				Toast.makeText(mContext, "HILO EJECUTADO", Toast.LENGTH_LONG)
						.show();
			}
		}
		// Fin de la Clase TareaWSinsert
	}

	class TareaWSUpdate extends AsyncTask<String, Integer, Boolean> {

		String link = "http://10.0.2.2:49498/Api/Clientes/Cliente";
		Boolean result = true;

		@Override
		protected Boolean doInBackground(String... params) {

			HttpClient httpClient = new DefaultHttpClient();
			HttpPut put = new HttpPut(link);
			put.setHeader("content-type", "application/json");

			try {
				JSONObject datos = new JSONObject();

				datos.put("Id", Integer.parseInt(params[0]));
				datos.put("Nombre", params[1]);
				datos.put("Telefono",
						Integer.parseInt(params[2]));

				StringEntity entity = new StringEntity(datos.toString());
				put.setEntity(entity);

				HttpResponse resp = httpClient.execute(put);
				String respStr = EntityUtils.toString(resp.getEntity());

				if (!respStr.equals("true")) {
					Toast.makeText(mContext, "ERROR!", Toast.LENGTH_SHORT)
							.show();
					result = false;
				}

			} catch (Exception e) {
				Log.i(TAG, "EXCEPTION : " + e);
				result = false;
			}

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				Toast.makeText(mContext, "HILO EJECUTADO", Toast.LENGTH_LONG)
						.show();
			}
		}
		// Fin de la Clase TareaWSUpdate
	}

	class TareaWSDelete extends AsyncTask<String, Integer, Boolean> {

		String link = "http://10.0.2.2:49498/Api/Clientes/Cliente/";
		Boolean result = true;

		@Override
		protected Boolean doInBackground(String... params) {

			String id = params[0];
			HttpClient httpClient = new DefaultHttpClient();
			HttpDelete del = new HttpDelete(link + id);
			del.setHeader("content-type", "application/json");

			try {
				HttpResponse resp = httpClient.execute(del);
				String respStr = EntityUtils.toString(resp.getEntity());

				if (!respStr.equals("true"))
					result = false;

			} catch (Exception e) {
				Log.i(TAG, "ERROR Exception: " + e);
				result = false;
			}

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				Toast.makeText(mContext, "HILO EJECUTADO", Toast.LENGTH_LONG)
						.show();
			}
		}
		// Fin de la Clase TareaWSDelete
	}

	class TareaWSConsult extends AsyncTask<String, Integer, Boolean> {

		String link = "http://10.0.2.2:49498/Api/Clientes";
		JSONArray respJSON;
		String[] clientes;

		@Override
		protected Boolean doInBackground(String... params) {
			Boolean result = true;

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet get = new HttpGet(link);
			get.setHeader("content-type", "application/json");

			try {
				HttpResponse resp = httpClient.execute(get);
				String respStr = EntityUtils.toString(resp.getEntity());

				respJSON = new JSONArray(respStr);
				clientes = new String[respJSON.length()];

				for (int i = 0; i < respJSON.length(); i++) {

					JSONObject obj = respJSON.getJSONObject(i);

					clientes[i] = obj.getString("Id") + " - "
							+ obj.getString("Nombre") + " - "
							+ obj.getString("Telefono");
				}
			} catch (Exception e) {
				Log.i(TAG, "ERROR Exception: " + e);
				result = false;
			}

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						MainActivity.this, android.R.layout.simple_list_item_1,
						clientes);
				lvClientes.setAdapter(adapter);

				Toast.makeText(mContext, "HILO EJECUTADO", Toast.LENGTH_LONG)
						.show();
			}
		}
		// Fin de la Clase TareaWSConsult
	}

	class TareaWSClient extends AsyncTask<String, Integer, Boolean> {

		String link = "http://10.0.2.2:49498/Api/Clientes/Cliente/";
		String client_id;

		@Override
		protected Boolean doInBackground(String... params) {
			Boolean result = true;
			String id = params[0];

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet put = new HttpGet(link + id);
			put.setHeader("content-type", "application/json");

			try {
				HttpResponse resp = httpClient.execute(put);
				String respStr = EntityUtils.toString(resp.getEntity());

				JSONObject obj = new JSONObject(respStr);

				client_id = obj.getString("Id") + " - "
						+ obj.getString("Nombre") + " - "
						+ obj.getString("Telefono");

			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result){
				Toast.makeText(mContext, client_id, Toast.LENGTH_LONG).show();
			}
		}

	}

}
