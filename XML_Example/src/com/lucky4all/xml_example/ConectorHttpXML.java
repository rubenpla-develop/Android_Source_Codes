package com.lucky4all.xml_example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ConectorHttpXML {

	private String url;

	public ConectorHttpXML(String url) {
		super();
		this.url = url;
	}

	public ArrayList<Publicacion> execute() throws XmlPullParserException,
			IOException {

		ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();

		// con 'insideitem' comprobamos si nos encontramos dentro de la etiqueta
		// '<item>
		// del documento XML.
		boolean insideItem = false;

		String headline = "";
		String link = "";

		// Instanciamos el objeto XmlPullParser
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser xpp = factory.newPullParser();

		// La fuente que tiene que analizar el parser
		xpp.setInput(getInputStream(new URL(url)), "UTF-8");

		// determinamos en que etuiqueta nos encontramos
		int event = xpp.getEventType();

		while (event != XmlPullParser.END_DOCUMENT) {
			if (event == XmlPullParser.START_TAG) {
				if (xpp.getName().equalsIgnoreCase("item")) {
					insideItem = true;
				} else if (xpp.getName().equalsIgnoreCase("title")
						&& insideItem) {
					headline = xpp.nextText();
				}
			} else if (event == XmlPullParser.END_TAG
					&& xpp.getName().equalsIgnoreCase("item")) {
				publicaciones.add(new Publicacion(headline, link));
				insideItem = false;
			}
			event = xpp.next();
		}
		return publicaciones;
	}

	public InputStream getInputStream(URL url) {
		try {
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			return null;
		}
	}

}
