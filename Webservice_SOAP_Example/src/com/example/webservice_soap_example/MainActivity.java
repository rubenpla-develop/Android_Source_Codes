package com.example.webservice_soap_example;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webservice_soap_example.model.Cliente;

public class MainActivity extends Activity implements OnClickListener {

	EditText txtNombre;
	EditText txtTelefono;
	Button btnEnviar;
	Button btnObjeto;
	Button btnConsulta;
	public Context mContext;
	private ProgressDialog pd;
	ListView lvClientes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtTelefono = (EditText) findViewById(R.id.txtTelefono);
		btnEnviar = (Button) findViewById(R.id.btnEnviar);
		btnObjeto = (Button) findViewById(R.id.btnObjeto);
		btnConsulta = (Button) findViewById(R.id.btnConsulta);
		lvClientes = (ListView) findViewById(R.id.listView1);

		btnEnviar.setOnClickListener(this);
		btnObjeto.setOnClickListener(this);
		btnConsulta.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case (R.id.btnEnviar):
			AsyncTask_NuevoClienteSimple aSync_nClienteSimple = new AsyncTask_NuevoClienteSimple();
			aSync_nClienteSimple.execute();
			pd = ProgressDialog.show(mContext, "Por favor espere",
					"Guardando Datos", true, false);

			break;
		case (R.id.btnConsulta):
			AsyncTask_Consulta aSync_Consulta = new AsyncTask_Consulta();
			aSync_Consulta.execute();
			break;

		case (R.id.btnObjeto):
			AsyncTask_NuevoClienteObjeto aSync_nClienteObjeto = new AsyncTask_NuevoClienteObjeto();
			aSync_nClienteObjeto.execute();
			pd = ProgressDialog.show(mContext, "Por favor espere",
					"Guardando Datos", true, false);

			break;
		}

	}

	class AsyncTask_NuevoClienteSimple extends
			AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			final String NAMESPACE = "http://www.WebServiceLucky.net/";
			final String URL = "http://10.0.2.2:49647/ServicioClientes.asmx";
			final String METHOD_NAME = "NuevosClientesSimple";
			final String SOAP_ACTION = "http://www.WebServiceLucky.net/NuevosClientesSimple";
			final String TAG = "DEBUG";
			Boolean result = true;

			// En primer lugar crearemos la petición (request) a nuestro método
			// NuevoCliente.
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			// tendremos que asociar los parámetros de entrada mediante el
			// método addProperty() al que pasaremos los nombres y valores de
			// los parámetros
			request.addProperty("nombre", txtNombre.getText().toString());
			request.addProperty("telefono", txtTelefono.getText().toString());
			Log.i("TAG", "txtNombre: " + txtNombre.getText().toString()
					+ "\ntxtTelefono: " + txtTelefono.getText().toString());

			// El segundo paso será crear el contenedor SOAP (envelope) y
			// asociarle nuestra petición
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			// Indicaremos además que se trata de un servicio web .NET activando
			// su propiedad dotNet
			envelope.dotNet = true;
			// asociaremos la petición antes creada a nuestro contenedor
			// llamando al método setOutputSoapObject()
			envelope.setOutputSoapObject(request);

			// crearemos el objeto que se encargará de realizar la comunicación
			// HTTP con el servidor
			HttpTransportSE transporte = new HttpTransportSE(URL, 60000);

			// completaremos el proceso realizando la llamada al servicio web
			// mediante el método call()
			try {
				Log.i(TAG, "PASO 1");
				transporte.call(SOAP_ACTION, envelope);

				// obtener el resultado devuelto por el método web llamado. Esto
				// lo
				// conseguimos mediante el método getResponse()
				Log.i(TAG, "PASO 1b");
				SoapPrimitive resultado_xml;
				Log.i(TAG, "PASO 2");
				resultado_xml = (SoapPrimitive) envelope.getResponse();
				Log.i(TAG, "PASO 3");
				String res = resultado_xml.toString();
				Log.i(TAG, "PASO 4");
				if (!res.equals("1")) {
					result = false;
				} else {
					Toast.makeText(mContext, "OK GUARDADO", Toast.LENGTH_LONG)
							.show();
					Log.i(TAG, "PROCESO COMPLETADO");
				}

			} catch (Exception e) {
				Log.i(TAG, "EXCEPTION " + e);
				result = false;
				pd.dismiss();
			}
			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {

			pd.dismiss();
			// super.onPostExecute(result);
		}
		// final AsyncTask_NuevoClienteSimple
	}

	class AsyncTask_Consulta extends AsyncTask<String, Integer, Boolean> {

		private Cliente[] listaClientes;
		final String TAG = "DEBUG";

		@Override
		protected Boolean doInBackground(String... params) {

			final String NAMESPACE = "http://www.WebServiceLucky.net/";
			final String URL = "http://10.0.2.2:49647/ServicioClientes.asmx";
			final String METHOD_NAME = "ListadoClientes";
			final String SOAP_ACTION = "http://www.WebServiceLucky.net/ListadoClientes";
			boolean result = true;

			Log.i(TAG, "PASO 1");
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			HttpTransportSE transporte = new HttpTransportSE(URL);
			Log.i(TAG, "PASO 2");
			try {
				transporte.call(SOAP_ACTION, envelope);

				SoapObject resSoap = (SoapObject) envelope.getResponse();
				listaClientes = new Cliente[resSoap.getPropertyCount()];

				for (int i = 0; i < listaClientes.length; i++) {
					SoapObject ic = (SoapObject) resSoap.getProperty(i);

					Cliente cli = new Cliente();
					cli.set_id(Integer.parseInt(ic.getProperty(0).toString()));
					cli.setNombre(ic.getProperty(1).toString());
					cli.setTelefono(Integer.parseInt(ic.getProperty(2)
							.toString()));

					listaClientes[i] = cli;
				}
			} catch (Exception e) {
				result = false;
			}
			Log.i(TAG, "PASO 3");
			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			Log.i(TAG, "PASO 4");
			if (result) {
				Log.i(TAG, "PASO 5");
				// Rellenamos la lista con los nombres de los clientes
				final String[] datos = new String[listaClientes.length];
				for (int i = 0; i < listaClientes.length; i++) {
					datos[i] = listaClientes[i].nombre;

					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							mContext, android.R.layout.simple_list_item_1,
							datos);
					lvClientes.setAdapter(adapter);

				}
			} else {
				Toast.makeText(mContext, "ERROR!", Toast.LENGTH_LONG).show();
			}
			Log.i(TAG, "PASO 6");
		}
		// final AsyncTask_Consulta
	}

	public class AsyncTask_NuevoClienteObjeto extends
			AsyncTask<String, Integer, Boolean> {

		//private Cliente[] listaClientes;
		Boolean result = true;

		@Override
		protected Boolean doInBackground(String... params) {
			final String NAMESPACE = "http://www.WebServiceLucky.net/";
			final String URL = "http://10.0.2.2:49647/ServicioClientes.asmx";
			final String METHOD_NAME = "NuevoClienteObjeto";
			final String SOAP_ACTION = "http://www.WebServiceLucky.net/NuevoClienteObjeto";
			//final String TAG = "DEBUG";

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

			Cliente cli = new Cliente();

			cli.setNombre(txtNombre.getText().toString());
			cli.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));

			PropertyInfo property_info = new PropertyInfo();
			property_info.setName("cliente");
			property_info.setValue(cli);
			property_info.setType(cli.getClass());

			request.addProperty(property_info);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;

			envelope.setOutputSoapObject(request);

			envelope.addMapping(NAMESPACE, "Cliente", cli.getClass());

			HttpTransportSE transporte = new HttpTransportSE(URL);

			try {
				transporte.call(SOAP_ACTION, envelope);

				SoapPrimitive result_xml = (SoapPrimitive) envelope
						.getResponse();
				String res = result_xml.toString();

				if (!res.equals("1")) {
					result = false;
				}

			} catch (Exception e) {
				result = false;
			}

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			pd.dismiss();
		}

		// final AsyncTask_Consulta
	}

}
