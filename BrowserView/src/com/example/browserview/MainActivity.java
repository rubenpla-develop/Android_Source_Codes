package com.example.browserview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

	private EditText txtUri;
	private Button btnIr;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Manejadores de Elementos
		txtUri = (EditText) findViewById(R.id.txt_Uri);
		btnIr = (Button) findViewById(R.id.go_button);
		webView = (WebView) findViewById(R.id.web_view);
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
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	private void abrirBrowser() {
		// Toast.makeText(this, "CORRECTO!!!", Toast.LENGTH_LONG).show();
		// txtUri=EditText.
		// Uri web = Uri.parse(txtUri.getText().toString());
		// Intent intent = new Intent(Intent.ACTION_VIEW, web);
		// startActivity(intent);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(txtUri.getText().toString());
	}

}
