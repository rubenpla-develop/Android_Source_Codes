package com.lucky4all.xml_sax_example.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lucky4all.xml_sax_example.models.Noticia;

public class Rsshandler extends DefaultHandler {
	private List<Noticia> noticias;
	private Noticia noticiaActual;
	private StringBuilder sbTexto;

	public List<Noticia> getNoticias() {
		return noticias;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if (this.noticiaActual != null)
			sbTexto.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);

		if (this.noticiaActual != null) {

			if (localName.equals("title")) {
				noticiaActual.setTitulo(sbTexto.toString());
			} else if (localName.equals("link")) {
				noticiaActual.setLink(sbTexto.toString());
			} else if (localName.equals("description")) {
				noticiaActual.setDescripcion(sbTexto.toString());
			} else if (localName.equals("guid")) {
				noticiaActual.setGuid(sbTexto.toString());
			} else if (localName.equals("pubDate")) {
				noticiaActual.setFecha(sbTexto.toString());
			} else if (localName.equals("item")) {
				noticias.add(noticiaActual);
			}	
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-ge nerated method stub
		super.startDocument();

		noticias = new ArrayList<Noticia>();
		sbTexto = new StringBuilder();
		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);

		if (localName.equals("item")) {
			noticiaActual = new Noticia();
		}
		sbTexto.setLength(0);

	}

}
