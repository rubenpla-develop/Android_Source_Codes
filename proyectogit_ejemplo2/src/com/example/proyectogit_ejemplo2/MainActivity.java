package com.example.proyectogit_ejemplo2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Context mContext;
	Button btn,btn2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		btn = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mContext, "Mensaje Toast", Toast.LENGTH_LONG)
						.show();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
