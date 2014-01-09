package com.lucky4all.xml__ejemplo;

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
		this.url = url;
	}

	/**
	 * Analiza el documento XML a partir de la URL indicada en el constructor.
	 * 
	 * @return ArrayList<Publicacion> con todas las publicaciones que hay en el
	 *         XML
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public ArrayList<Publicacion> execute() throws XmlPullParserException,
			IOException {
		ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();
		// Variable para controlar si nos encontramos dentro de la etiqueta
		// item, que es la que identifica las publicaciones.
		boolean insideItem = false;
		// Variables para t�tulo y link de las publicaciones.
		String headline = "";
		String link = "";

		// Variable que permitir� instanciar el XmlPullParser.
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser xpp = factory.newPullParser();

		// Le indicamos al parser la fuente que debe analizar. El m�todo
		// getInputStream() est� definido m�s abajo.
		xpp.setInput(getInputStream(new URL(url)), "UTF-8");

		// Recogemos el tipo de evento en el que nos encontramos, esto indicar�
		// si estamos en una etiqueta de inicio, de fin, o si se trata de un
		// texto.
		int event = xpp.getEventType();

		// Hacemos que se analice el documento hasta el final del mismo.
		while (event != XmlPullParser.END_DOCUMENT) {

			// Si nos encontramos en una etiqueta de inicio analizamos en qu�
			// etiqueta nos encontramos.
			if (event == XmlPullParser.START_TAG) {

				// Si es una etiqueta item cambiamos el valor de insideItem a
				// true, para posteriormente saber que estamos dentro de esta
				// etiqueta.
				if (xpp.getName().equalsIgnoreCase("item")) {
					insideItem = true;

					// Si es la etiqueta title, y previamente hab�amos entrado
					// en un item recuperamos el t�tulo de la publicaci�n.
				} else if (xpp.getName().equalsIgnoreCase("title")
						&& insideItem) {
					headline = xpp.nextText();

					// Si es la etiqueta link, y previamente hab�amos entrado
					// en un item recuperamos el link de la publicaci�n.
				} else if (xpp.getName().equalsIgnoreCase("link") && insideItem) {
					link = xpp.nextText();
				}

				// Si es etiqueta de fin de item a�adimos una nueva publicaci�n
				// a la colecci�n e indicamos que salimos del item cambiando el
				// valor de insideItem a false.
			} else if (event == XmlPullParser.END_TAG
					&& xpp.getName().equalsIgnoreCase("item")) {
				publicaciones.add(new Publicacion(headline, link));
				insideItem = false;
			}

			// Recogemos el siguiente evento.
			event = xpp.next();
		}

		// Una vez analizado el documento completo, devolvemos la colecci�n de
		// publicaciones.
		return publicaciones;
	}

	/**
	 * Conecta a la url indicada para recoger su contenido.
	 * 
	 * @param url
	 * @return InputStream con el contenido de la url
	 */
	public InputStream getInputStream(URL url) {
		try {
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			return null;
		}
	}
}
