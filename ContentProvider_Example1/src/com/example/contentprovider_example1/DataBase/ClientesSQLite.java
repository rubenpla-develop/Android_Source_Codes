package com.example.contentprovider_example1.DataBase;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contentprovider_example1.R;

public class ClientesSQLite extends SQLiteOpenHelper {

	private final String sql = "CREATE TABLE Clientes"
			+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " nombre TEXT, " + "email TEXT)";
	Context mContext;

	public ClientesSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mContext=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
		
		ContentValues _Values = new ContentValues();
		// Get xml resource file
		Resources res = mContext.getResources();

		// Open xml file
		XmlResourceParser _xml = res.getXml(R.xml.clientes);
		try {
			// Check for end of document
			int eventType = _xml.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Search for record tags
				if ((eventType == XmlPullParser.START_TAG)
						&& (_xml.getName().equals("record"))) {
					// Record tag found, now get values and insert record
					String _Nombre = _xml.getAttributeValue(null,
							ColumnasTabla.NOMBRE);
					String _Email= _xml
							.getAttributeValue(null, ColumnasTabla.EMAIL);
					_Values.put(ColumnasTabla.NOMBRE, _Nombre);
					_Values.put(ColumnasTabla.EMAIL, _Email);
					db.insert(ColumnasTabla.NOMBRETABLA, null, _Values);
				}
				eventType = _xml.next();
			}
		}
		// Catch errors
		catch (XmlPullParserException e) {
			Log.e("TAG", e.getMessage(), e);
		} catch (IOException e) {
			Log.e("TAG", e.getMessage(), e);

		} finally {
			// Close the xml file
			_xml.close();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE  IF EXISTS CLIENTES");
		db.execSQL(sql);
	}

}
