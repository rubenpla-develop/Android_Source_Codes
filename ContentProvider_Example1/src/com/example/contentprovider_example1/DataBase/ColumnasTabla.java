package com.example.contentprovider_example1.DataBase;

import android.provider.BaseColumns;

public class ColumnasTabla implements BaseColumns {

	public ColumnasTabla() {
		
	}
	public static String NOMBRETABLA = "Clientes";
	public static String NOMBRE = "nombre";
	public static String EMAIL = "email";
	public static String id = "_id";
	public static String NOMBREDB = "BDClientes";
	public static int BD_VERSION = 1;

}
