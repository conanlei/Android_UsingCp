package cn.eoe.usingsqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class UsersCP extends ContentProvider {

	
	private UsersDb db;
	private SQLiteDatabase dbRead,dbWrite;
	
	public static final String TABLE_NAME = "user";
	public static final Uri URI = Uri.parse("content://cn.eoe.usingsqlite.usercp");
	
	@Override
	public boolean onCreate() {
		db = new UsersDb(getContext());
		dbRead = db.getReadableDatabase();
		dbWrite = db.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return dbRead.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		dbWrite.insert(TABLE_NAME, null, values);
		
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return dbWrite.delete(TABLE_NAME, selection, selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return dbWrite.update(TABLE_NAME, values, selection, selectionArgs);
	}

}
