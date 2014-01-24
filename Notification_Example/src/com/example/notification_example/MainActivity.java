package com.example.notification_example;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button btn;
	Context mContext;
	private static final int NOTIF_ALERTA_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		NotificationCompat.Builder notif = new NotificationCompat.Builder(
				mContext)
				.setSmallIcon(android.R.drawable.stat_notify_missed_call)
				.setLargeIcon(
						(((BitmapDrawable) getResources().getDrawable(
								R.drawable.ic_launcher)).getBitmap()))
				.setContentTitle("Notificacion de Prueba")
				.setContentText("Texto de Notifcacion").setContentInfo("4")
				.setTicker("Alerta!");

		Intent notIntent = new Intent(mContext, Notification.class);

		PendingIntent contIntent = PendingIntent.getActivity(mContext, 0,
				notIntent, 0);

		notif.setContentIntent(contIntent);

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(NOTIF_ALERTA_ID, notif.build());

	}
}
