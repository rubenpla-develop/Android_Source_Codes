package com.lucky4all.xml_dom_example.model;

public class Noticia {
	private String Titulo;
	private String Descripcion;
	private String Link;
	private String Guid;
	private String Fecha;

	public String getTitulo() {
		return Titulo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public String getLink() {
		return Link;
	}

	public String getGuid() {
		return Guid;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public void setLink(String link) {
		Link = link;
	}

	public void setGuid(String guid) {
		Guid = guid;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

}
