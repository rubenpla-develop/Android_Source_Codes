package com.lucky4all.xml_example;

public class Publicacion {

	private String titulo, link;

	public Publicacion(String titulo, String link) {
		super();
		this.titulo = titulo;
		this.link = link;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getLink() {
		return link;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
