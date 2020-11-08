package com.itheima.exposedatabase;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建openHelper对象
		MyOpenHelper helper = new MyOpenHelper(this);
		database = helper.getReadableDatabase();
	}

	public void insert(View v){
		database.execSQL("insert into info(name,phone) values('王五','13777777')");
		database.execSQL("insert into info(name,phone) values('张三','13888888')");
		database.execSQL("insert into info(name,phone) values('李四','13666666')");
	}
	
	public void query(View v){
		Cursor cursor = database.rawQuery("select * from info", null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			System.out.println("name="+name+"phone="+phone);
		}
		cursor.close();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		database.close();
	}

}
