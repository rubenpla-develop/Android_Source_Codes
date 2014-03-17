package com.lucky4all.widget_example_1;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class miwidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			int widgetId = appWidgetIds[i];

			actualizarWidget(context, appWidgetManager, widgetId);
		}
	}

	public static void actualizarWidget(Context context,
			AppWidgetManager appWidgetManager, int widgetId) {
		SharedPreferences prefs = context.getSharedPreferences("WidgetPrefs",
				context.MODE_PRIVATE);
		String mensaje = prefs.getString("msg_" + widgetId, "Hora actual");

		RemoteViews controles = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
	
	

		Intent intent = new Intent(
				"com.lucky4all.widget_example_1.ACTUALIZAR_WIDGET");
		intent.putExtra(appWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

		PendingIntent pIntent = PendingIntent.getBroadcast(context, widgetId,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		controles.setOnClickPendingIntent(R.id.BtnActualizar, pIntent);
		
		controles.setTextViewText(R.id.LblMensaje, mensaje);

		// Obtenemos la hora actual
		Calendar calendario = new GregorianCalendar();
		@SuppressWarnings("deprecation")
		String Hora = calendario.getTime().toLocaleString();

		// Actualizamos la hora en el control del widget
		controles.setTextViewText(R.id.LblHora, Hora);
		
		// Notificamos al manager de la actualización del widget actual
		appWidgetManager.updateAppWidget(widgetId, controles);

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(
				"com.lucky4all.widget_Example_1.ACTUALIZAR_WIDGET")) {
			//Obtenemos el ID del widget a actualizar
			int widgetId = intent.getIntExtra(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
			
			//Obtenemos el widget manager de nuestro contexto
			AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);


			if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID){
				actualizarWidget(context, widgetManager, widgetId);
			}
		}

	}
}
