package com.lucky4all.widget_example_1;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class WidgetConfig extends Activity implements OnClickListener {

	private int widgetId = 0;

	private EditText txtMensaje;
	private Button btnAceptar;
	private Button btnCancelar;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_config_screen);
		mContext = this;

		txtMensaje = (EditText) findViewById(R.id.TxtMensaje);
		btnAceptar = (Button) findViewById(R.id.BtnAceptar);
		btnCancelar = (Button) findViewById(R.id.BtnCancelar);

		btnAceptar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);

		// Obtenemos el intent del cual proviene esta activity y cogemos sus
		// parametros extras.
		Intent intentOrigen = getIntent();
		Bundle params = intentOrigen.getExtras();

		// obtendremos el valor del ID del widget accediendo a la clave
		// AppWidgetManager.EXTRA_APPWIDGET_ID.
		widgetId = params.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		// Establecemos el resultado por defecto (si se pulsa el botón 'Atrás'
		// del teléfono será éste el resultado devuelto).
		setResult(RESULT_CANCELED);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case (R.id.BtnAceptar):
			SharedPreferences prefs = getSharedPreferences("WidgetPrefs",
					Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("msg_" + widgetId, txtMensaje.getText().toString());
			editor.commit();
			
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
			appWidgetManager.getAppWidgetInfo(widgetId);
			miwidget.actualizarWidget(WidgetConfig.this, appWidgetManager, widgetId);
			
			//Devolvemos como resultado: ACEPTAR (RESULT_OK)
			Intent Resultado = new Intent();
			Resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			setResult(RESULT_OK, Resultado);
			finish();
			
		case (R.id.BtnCancelar):
			finish();
		}

	}
}
