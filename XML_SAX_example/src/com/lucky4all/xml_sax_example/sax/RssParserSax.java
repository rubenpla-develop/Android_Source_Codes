package com.lucky4all.xml_sax_example.sax;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

import com.lucky4all.xml_sax_example.models.Noticia;

public class RssParserSax {

	private URL rssUrl;
	private Noticia noticiaActual;

	public RssParserSax(String rssUrl) {

		try {
			this.rssUrl = new URL(rssUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Noticia> parse() {
		/*
		 * SAXParserFactory factory = SAXParserFactory.newInstance();
		 * 
		 * try { SAXParser parser = factory.newSAXParser(); Rsshandler handler =
		 * new Rsshandler(); parser.parse(getInputStream(), handler); return
		 * handler.getNoticias(); } catch (Exception e) { throw new
		 * RuntimeException(e);
		 */
		
		//Este es un metodo simplificado para recorrer el XML sin necesidad de una clase
		//externa mas laboriosa.
		final List<Noticia> noticias = new ArrayList<Noticia>();

		RootElement root = new RootElement("rss");
		Element channel = root.getChild("channel");
		Element item = channel.getChild("item");

		item.setStartElementListener(new StartElementListener() {

			@Override
			public void start(Attributes attrs) {
				// TODO Auto-generated method stub
				noticiaActual = new Noticia();
			}
		});

		item.setEndElementListener(new EndElementListener() {

			@Override
			public void end() {
				// TODO Auto-generated method stub
				noticias.add(noticiaActual);
			}
		});

		item.getChild("title").setEndTextElementListener(
				new EndTextElementListener() {

					@Override
					public void end(String body) {
						// TODO Auto-generated method stub
						noticiaActual.setTitulo(body);
					}
				});

		item.getChild("link").setEndTextElementListener(
				new EndTextElementListener() {

					@Override
					public void end(String body) {
						// TODO Auto-generated method stub
						noticiaActual.setLink(body);
					}
				});

		item.getChild("description").setEndTextElementListener(
				new EndTextElementListener() {

					@Override
					public void end(String body) {
						// TODO Auto-generated method stub
						noticiaActual.setDescripcion(body);
					}
				});

		item.getChild("guid").setEndTextElementListener(
				new EndTextElementListener() {

					@Override
					public void end(String body) {
						// TODO Auto-generated method stub
						noticiaActual.setGuid(body);
					}
				});

		item.getChild("pubDate").setEndTextElementListener(
				new EndTextElementListener() {

					@Override
					public void end(String body) {
						// TODO Auto-generated method stub
						noticiaActual.setFecha(body);
					}
				});

		try {
			Xml.parse(getInputStream(), Xml.Encoding.UTF_8,
					root.getContentHandler());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return noticias;

	}

	private InputStream getInputStream() {

		try {
			return rssUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
