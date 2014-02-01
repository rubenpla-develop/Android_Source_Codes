package com.lucky4all.xml_sax_example.models;

public class Noticia {
	private String titulo, link, descripcion, guid, fecha;

	/*public Noticia(String titulo, String link, String descripcion, String guid,
			String fecha) {
		this.titulo = titulo;
		this.link = link;
		this.descripcion = descripcion;
		this.guid = guid;
		this.fecha = fecha;
	}*/

	public String getTitulo() {
		return titulo;
	}

	public String getLink() {
		return link;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getGuid() {
		return guid;
	}

	public String getFecha() {
		return fecha;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
