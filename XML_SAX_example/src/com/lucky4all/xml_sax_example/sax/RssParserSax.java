package com.lucky4all.xml_sax_example.sax;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.lucky4all.xml_sax_example.models.Noticia;

public class RssParserSax {

	private URL rssUrl;

	public RssParserSax(String rssUrl)  {

		try {
			this.rssUrl = new URL(rssUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Noticia> parse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser parser = factory.newSAXParser();
			Rsshandler handler = new Rsshandler();
			parser.parse(getInputStream(), handler);
			return handler.getNoticias();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private InputStream getInputStream() {

		try {
			return rssUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
