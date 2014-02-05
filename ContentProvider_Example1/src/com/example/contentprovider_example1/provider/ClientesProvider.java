package com.example.contentprovider_example1.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.contentprovider_example1.DataBase.ClientesSQLite;
import com.example.contentprovider_example1.DataBase.ColumnasTabla;

public class ClientesProvider extends ContentProvider {

	private static final String uri = "content://com.example.contentprovider_example1/clientes";
	public static final Uri CONTENT_URI = Uri.parse(uri);
	private ClientesSQLite dbHelper;
	private static final UriMatcher uriMatcher;
	private static final int CLIENTES = 1;
	private static final int CLIENTES_ID = 2;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.example.contentprovider_example1", "clientes",
				CLIENTES);
		uriMatcher.addURI("com.example.contentprovider_example1", "clientes/#",
				CLIENTES_ID);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new ClientesSQLite(getContext(), ColumnasTabla.NOMBREDB,
				null, ColumnasTabla.BD_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] columnas, String selection,
			String[] selectionValues, String orderBy) {
		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor c = db.query(ColumnasTabla.NOMBRETABLA, columnas, where,
				selectionValues, null, null, orderBy);
		return c;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionValues) {
		int cont;

		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "id=" + uri.getLastPathSegment();
		}

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		cont = db.delete(ColumnasTabla.NOMBRETABLA, where, selectionValues);

		return cont;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionValues) {
		int cont;

		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "id=" + uri.getLastPathSegment();
		}

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		cont = db.update(ColumnasTabla.NOMBRETABLA, values, where,
				selectionValues);

		return cont;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long regId = 1;

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		regId = db.insert(ColumnasTabla.NOMBRETABLA, null, values);

		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

		return newUri;
	}

	@Override
	public String getType(Uri uri) {
		int match = uriMatcher.match(uri);

		switch (match) {
		case CLIENTES:
			return "vnd.android.cursor.dir/vnd.example.cliente";
		case CLIENTES_ID:
			return "vnd.android.cursor.item/vnd.example.cliente";
		default:
			return null;
		}
	}

}
