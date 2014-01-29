package com.example.contentprovider_example1.DataBase;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TablaCliente {
	
	private SQLiteDatabase db;
	
	public TablaCliente(SQLiteDatabase dbase) {
		db = dbase;
	}
	
	public boolean insert(String nombre, String email){
		ContentValues values = new ContentValues();
		values.put(ColumnasTabla.NOMBRE, nombre);
		values.put(ColumnasTabla.EMAIL, email);
		
		return db.insert(ColumnasTabla.NOMBRETABLA, null, values) >0;
	}
	
	public Cursor getRegistros(){
		String[] columnas = {ColumnasTabla.id,ColumnasTabla.NOMBRE, ColumnasTabla.EMAIL};
		return db.query(ColumnasTabla.NOMBRETABLA, columnas, null, null, null, null, null);
		
	}
	
}
