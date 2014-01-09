package com.lucky4all.threads_example_1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button btnSinHilos, btnHilo;
	ProgressBar pbarProgreso;
	Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		btnSinHilos = (Button) findViewById(R.id.button1);
		btnHilo = (Button) findViewById(R.id.btnHilo);
		pbarProgreso = (ProgressBar) findViewById(R.id.progressBar1);

		btnSinHilos.setOnClickListener(this);
		btnHilo.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void tareaLarga() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			pbarProgreso.setMax(100);
			pbarProgreso.setProgress(0);

			for (int i = 1; i <= 10; i++) {
				tareaLarga();
				pbarProgreso.incrementProgressBy(10);
			}

			Toast.makeText(mContext, "Tarea finalizada!", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.btnHilo:
			new Thread(new Runnable() {
				public void run() {
					pbarProgreso.post(new Runnable() {
						public void run() {
							pbarProgreso.setProgress(0);
						}
					});

					for (int i = 1; i <= 10; i++) {
						tareaLarga();
						pbarProgreso.post(new Runnable() {
							public void run() {
								pbarProgreso.incrementProgressBy(10);
							}
						});
					}

					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(mContext, "Tarea finalizada!",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			}).start();
			break;
		}

	}

}
