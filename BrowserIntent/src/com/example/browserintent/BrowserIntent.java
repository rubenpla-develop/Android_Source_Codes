package com.example.browserintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class BrowserIntent extends Activity {
	private EditText txtUri;
	private Button btnIr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Manejadores de Elementos
		txtUri = (EditText) findViewById(R.id.url_field);
		btnIr = (Button) findViewById(R.id.go_button);

		// Manejadores de Eventos

		btnIr.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				abrirBrowser();
			}
		});

		txtUri.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				boolean isEnter = (keyCode == KeyEvent.KEYCODE_ENTER);
				// if (keyCode = KeyEvent.KEYCODE_ENTER) {
				if (isEnter) {
					abrirBrowser();
					return true;
				}
				return false;
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browser_intent, menu);
		return true;
	}

	private void abrirBrowser() {
//		Toast.makeText(this, "CORRECTO!!!", Toast.LENGTH_LONG).show();
//		txtUri=EditText.
		Uri web = Uri.parse(txtUri.getText().toString());
		Intent intent = new Intent(Intent.ACTION_VIEW, web);
		startActivity(intent);

	}

}
