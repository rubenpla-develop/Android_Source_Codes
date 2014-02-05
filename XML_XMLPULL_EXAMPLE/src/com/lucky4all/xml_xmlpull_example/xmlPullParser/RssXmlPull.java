package com.lucky4all.xml_xmlpull_example.xmlPullParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.lucky4all.xml_xmlpull_example.model.Noticia;

public class RssXmlPull {


	private URL rssUrl;

	public RssXmlPull(String url) {
		try {
			rssUrl = new URL(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Noticia> parse() {
		List<Noticia> noticias = null;
		XmlPullParser parser = Xml.newPullParser();

		try {
			parser.setInput(getInputStream(), null);
			int evento = parser.getEventType();
			Noticia noticiaActual = null;
			
			while (evento != XmlPullParser.END_DOCUMENT){
				
				String etiqueta = null;
				
				switch (evento){
				case XmlPullParser.START_DOCUMENT:
					noticias = new ArrayList<Noticia>();
					break;
			
				case XmlPullParser.START_TAG:
					etiqueta = parser.getName();
					if (etiqueta.equals("title")){
						
						noticiaActual = new Noticia();
					}else if (noticiaActual != null){
						
						if (etiqueta.equals("title")){
							noticiaActual.setTitulo(parser.nextText());
						}else if (etiqueta.equals("description")){
							noticiaActual.setDescripcion(parser.nextText());
						}else if (etiqueta.equals("link")){
							noticiaActual.setLink(parser.nextText());
						}else if (etiqueta.equals("guid")){
							noticiaActual.setGuid(parser.nextText());
						}else if (etiqueta.equals("pubDate")){
							noticiaActual.setFecha(parser.nextText());
						}
					}
					break;
					
				case XmlPullParser.END_TAG:
					etiqueta = parser.getName();
					if (etiqueta.equals("item") && noticiaActual != null){
						noticias.add(noticiaActual);
					}
					break;
				}
				evento = parser.next();
			}
			
		} catch (Exception f) {
			throw new RuntimeException(f);
		}

		return noticias;
	}

	private InputStream getInputStream() {
		try {
			return rssUrl.openConnection().getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
