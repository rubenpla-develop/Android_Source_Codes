package com.example.fragment_ejemplo_2;

public class lista_imagenes {
	String titulo;
	String pie;
	String imagen;

	public lista_imagenes(String titulo, String pie, String imagen) {
		//super();
		this.titulo = titulo;
		this.pie = pie;
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPie() {
		return pie;
	}

	public String getImagen() {
		return imagen;
	}

	public void setPie(String pie) {
		this.pie = pie;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
