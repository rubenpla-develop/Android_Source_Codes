package com.example.contentprovider_example1.models;

public class Cliente {
	int _id;
	String cNombre;
	String cEmail;
	
	public Cliente(int _id, String cNombre, String cEmail) {
		this._id = _id;
		this.cNombre = cNombre;
		this.cEmail = cEmail;
	}
	
	public int get_id() {
		return _id;
	}
	public String getcNombre() {
		return cNombre;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	
	
}
