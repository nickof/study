package com.itheima.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	private MyOpenHelper openHelper;
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int QUERY_SUCESS = 0;
	private static final int INSERT_MATCHED = 1;
	private static final int UPDATE_MATCHED = 2;
	private static final int DELETE_MATCHED = 3;

	static
    {
		//����ǰ��URIƥ�������һ��ƥ�����
        sURIMatcher.addURI("com.itheima.provider", "query", QUERY_SUCESS);
        sURIMatcher.addURI("com.itheima.provider", "insert", INSERT_MATCHED);
        sURIMatcher.addURI("com.itheima.provider", "update", UPDATE_MATCHED);
        sURIMatcher.addURI("com.itheima.provider", "delete", DELETE_MATCHED);
        //sURIMatcher.addURI("com.itheima.provider", "student", 5);
    }

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int result = sURIMatcher.match(uri);
		if(result==DELETE_MATCHED){
			SQLiteDatabase db = openHelper.getReadableDatabase();
			int delete = db.delete("info", selection, selectionArgs);
			return delete;
		}else{
			return -1;
		}
		
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int result = sURIMatcher.match(uri);
		if(result==INSERT_MATCHED){
			SQLiteDatabase db = openHelper.getReadableDatabase();
			long insert = db.insert("info", null, values);
			//����ͨ�����ݽ����߷���һ�����ݱ仯��֪ͨ
			//��һ������ uri notifyChange ֮�� ͨ�����uri���ж� �ĵ����ĸ����ݽ�۲���
			//�ڶ�������  ���ݹ۲��߶��� �����null ע�������uri�����й۲��߶����յ�֪ͨ
			getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(String.valueOf(insert));
		}else{
			return null;
		}
		
	}

	@Override
	public boolean onCreate() {
		openHelper = new MyOpenHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		//ʹ��URIƥ���� ����URIƥ����ж� ���ƥ������ ������ȷ�Ľ�� ���û��ƥ�����׳��쳣
		int result = sURIMatcher.match(uri);
		if(result == QUERY_SUCESS){
			SQLiteDatabase db = openHelper.getReadableDatabase();
			Cursor cursor = db.query("info", projection, selection, selectionArgs, null, null, sortOrder);
			return cursor;
		}else{
			throw new IllegalStateException("�������");
		}
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int result = sURIMatcher.match(uri);
		if(result==UPDATE_MATCHED){
			SQLiteDatabase db = openHelper.getReadableDatabase();
			int update = db.update("info", values, selection, selectionArgs);
			db.close();
			return update;
		}else{
			return -1;
		}
		
	}

}
