package com.lucky4all.xml__ejemplo;

import android.app.Application;
import android.widget.ArrayAdapter;

public class XML_EjemploApplication extends Application {
	private final static String URL = "http://proyectosimio.wordpress.com/feed/";

	public void updatePublicaciones(ArrayAdapter<Publicacion> lvAdapter) {

		AsyncConector conector = new AsyncConector(lvAdapter, URL);
		conector.execute();
	}

}