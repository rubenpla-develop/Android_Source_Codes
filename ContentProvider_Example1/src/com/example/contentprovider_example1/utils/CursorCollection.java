package com.example.contentprovider_example1.utils;

import java.util.ArrayList;

import com.example.contentprovider_example1.DataBase.ColumnasTabla;
import com.example.contentprovider_example1.models.Cliente;

import android.database.Cursor;

public class CursorCollection {

	public static ArrayList<Cliente> cursorArrayNombres(Cursor c) {
		ArrayList<Cliente> registros = new ArrayList<Cliente>();
		int id;
		String nombre, email;

	/*	if (c.moveToFirst()) {
			do {
				registros.add(c.getString(0));
			} while (c.moveToNext());
		}*/
		if (c.moveToFirst()){
			do{
				id = c.getInt(c.getColumnIndex(ColumnasTabla.id));
				nombre = c.getString(c.getColumnIndex(ColumnasTabla.NOMBRE));
				email = c.getString(c.getColumnIndex(ColumnasTabla.EMAIL));
				
				registros.add(new Cliente(id, nombre, email));
			}while (c.moveToNext());
		}
		return registros;
	}

}
