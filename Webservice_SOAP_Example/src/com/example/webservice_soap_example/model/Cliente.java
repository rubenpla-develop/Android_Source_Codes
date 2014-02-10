package com.example.webservice_soap_example.model;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Cliente implements KvmSerializable {
	
	 public Integer _id;
	 public String nombre;
	 public Integer telefono;
	
	public Cliente() {
		_id=0;
		nombre="";
		telefono=0;
	}
	
	public Cliente(Integer _id, String nombre, Integer telefono) {
		super();
		this._id = _id;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	@Override
	public Object getProperty(int arg0) {
		
		switch (arg0){
		case 0:
			return _id;
		case 1:
			return nombre;
		case 2:
			return telefono;
		}
		
		return null;
	}

	@Override
	public int getPropertyCount() {
		
		return 3;
	}

	@Override
	public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
		
		switch (ind){
		case 0:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name="Id";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name="Nombre";
			break;
		case 2:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name="Telefono";
			break;
		default: break;
		}
		
	}

	@Override
	public void setProperty(int ind, Object val) {
		switch (ind){
		case 0:
			_id = Integer.parseInt(val.toString());
			break;
		case 1:
			nombre = val.toString();
			break;
		case 2:
			telefono = Integer.parseInt(val.toString());
			break;
		default:
			break;
		}		
	}
		
}
