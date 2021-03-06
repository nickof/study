package com.itheima.accessothersdatabase;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


   public void query(View v){
//	   String path = "/data/data/com.itheima.exposedatabase/databases/itheima.db";
//	SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
	   //获取内容解析者
	   ContentResolver contentResolver = getContentResolver();
	   Uri uri =Uri.parse("content://com.itheima.provider/query");
	Cursor cursor = contentResolver.query(uri, null, null, null, null);
//	Cursor cursor = database.rawQuery("select * from info", null);
	while(cursor.moveToNext()){
		String name = cursor.getString(cursor.getColumnIndex("name"));
		String phone = cursor.getString(cursor.getColumnIndex("phone"));
		System.out.println("name="+name+"phone"+phone);
	}
   }
   
   public void insert(View v){
	   //①获取内容解析者
	   ContentResolver resolver = getContentResolver();
	   Uri url = Uri.parse("content://com.itheima.provider/insert");
	ContentValues values = new ContentValues();
	values.put("name", "赵四");
	values.put("phone", "12345678");
	Uri insert = resolver.insert(url, values);
	System.out.println(insert);
   }
   
public void update(View v){
	ContentResolver resolver = getContentResolver();
	Uri uri = Uri.parse("content://com.itheima.provider/update");
	ContentValues values = new ContentValues();
	values.put("phone", "110");
	String where="name = ?";
	String[] selectionArgs = {"张三"};
	int update = resolver.update(uri, values, where, selectionArgs);
	   Toast.makeText(getApplicationContext(), "更新了"+update+"条数据", Toast.LENGTH_SHORT).show();
   }

public void delete(View v){
	 ContentResolver resolver = getContentResolver();
	 Uri url = Uri.parse("content://com.itheima.provider/delete");
	String where = "name = ?";
	String[] selectionArgs = {"王五"};
	int delete = resolver.delete(url, where, selectionArgs);
	 Toast.makeText(getApplicationContext(), "删除了"+delete+"条数据", Toast.LENGTH_SHORT).show();
}
    
}
