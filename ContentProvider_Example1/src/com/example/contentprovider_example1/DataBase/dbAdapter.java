package com.example.contentprovider_example1.DataBase;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbAdapter {

	Context mContext;
	private static final int Version = 1;
	private static final String NombreDB = "BDClientes";

	ClientesSQLite dbHelper;
	SQLiteDatabase db;

	public dbAdapter(Context context) {
		mContext = context;
		dbHelper = new ClientesSQLite(mContext,NombreDB,null,1);
	}

	public dbAdapter Open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void Close() {
		db.close();
	}

	public Cursor getSelectRegistros() {
		TablaCliente tCliente = new TablaCliente(db);
		return tCliente.getRegistros();
	}

}
