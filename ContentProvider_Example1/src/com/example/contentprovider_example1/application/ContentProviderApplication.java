package com.example.contentprovider_example1.application;

import java.util.ArrayList;

import com.example.contentprovider_example1.DataBase.TablaCliente;
import com.example.contentprovider_example1.DataBase.dbAdapter;
import com.example.contentprovider_example1.models.Cliente;
import com.example.contentprovider_example1.utils.CursorCollection;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;


public class ContentProviderApplication extends Application {
	dbAdapter DBadapter;
	Context mContext;
	TablaCliente tCliente;

	@Override
	public void onCreate() {
		mContext = this;
		DBadapter = new dbAdapter(mContext);
		DBadapter.Open();

		super.onCreate();
	}

	@Override
	public void onTerminate() {
		DBadapter.Close();
		super.onTerminate();
	}

	public ArrayList<Cliente> getResultados() {
		Cursor c = DBadapter.getSelectRegistros();
		return CursorCollection.cursorArrayNombres(c);
	}
}
