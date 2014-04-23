package com.lucky4all.intents_types;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button Button1;
	private Button Button2;
	private Button Button3;
	private Button Button4;
	private Button Button5;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button1 = (Button) findViewById(R.id.button1);
		Button2 = (Button) findViewById(R.id.button2);
		Button3 = (Button) findViewById(R.id.button3);
		Button4 = (Button) findViewById(R.id.button4);
		Button5 = (Button) findViewById(R.id.button5);

		Button1.setOnClickListener(this);
		Button2.setOnClickListener(this);
		Button3.setOnClickListener(this);
		Button4.setOnClickListener(this);
		Button5.setOnClickListener(this);
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
		case (R.id.button1):
			intent = new Intent(
					Intent.ACTION_WEB_SEARCH,
					Uri.parse("https://github.com/rubenpla-develop?tab=repositories"));
			startActivity(intent);
			break;
		case (R.id.button2):
			intent = new Intent(
					Intent.ACTION_DIAL,
					Uri.parse("tel:964310606"));
			startActivity(intent);
			break;
		case (R.id.button3):
			intent = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("geo:41.656313,-0.877351"));
			startActivity(intent);
			break;
		case (R.id.button4):
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivity(intent);
			break;
		case (R.id.button5):
			intent = new Intent(
					Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_SUBJECT, "CORREO PRUEBA");
			intent.putExtra(Intent.EXTRA_TEXT, "Mail enviado desde com.lucky4all.intents_iipes");
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rubenpla.develop@gmail.com", "luckybcn2@gmail.com"});
			startActivity(intent);
			
			break;
		}

	}

}
