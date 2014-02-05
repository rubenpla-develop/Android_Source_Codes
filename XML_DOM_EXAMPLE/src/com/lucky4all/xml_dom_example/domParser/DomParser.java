package com.lucky4all.xml_dom_example.domParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lucky4all.xml_dom_example.model.Noticia;

public class DomParser {

	private URL rssUrl;

	public DomParser(String url) {
		try {
			rssUrl = new URL(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Noticia> parse() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Noticia> noticias = new ArrayList<Noticia>();

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName("item");

			for (int i = 0; i < items.getLength(); i++) {
				Noticia noticia = new Noticia();
				Node item = items.item(i);
				NodeList datosNoticia = item.getChildNodes();

				for (int j = 0; j < datosNoticia.getLength(); j++) {
					Node dato = datosNoticia.item(j);
					String etiqueta = dato.getNodeName();

					// Solo recorro las etiquetas que quiero utilizar para mi
					// view
					// faltara la etiqueta 'guid'
					if (etiqueta.equals("title")) {
						String texto = obtenerTexto(dato);
						noticia.setTitulo(texto);
					}

					if (etiqueta.equals("description")) {
						String texto = obtenerTexto(dato);
						noticia.setDescripcion(texto);
					}

					if (etiqueta.equals("link")) {
						noticia.setLink(dato.getFirstChild().getNodeValue());
					}

					if (etiqueta.equals("pubDate")) {
						noticia.setFecha(dato.getFirstChild().getNodeValue());
					}
				}
				noticias.add(noticia);
			}

		} catch (Exception f) {
			throw new RuntimeException(f);
		}

		return noticias;
	}

	private String obtenerTexto(Node dato) {

		StringBuilder texto = new StringBuilder();
		NodeList fragmentos = dato.getChildNodes();

		for (int k = 0; k < fragmentos.getLength(); k++) {
			texto.append(fragmentos.item(k).getNodeValue());
		}

		return texto.toString();
	}

	private InputStream getInputStream() {
		try {
			return rssUrl.openConnection().getInputStream();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
