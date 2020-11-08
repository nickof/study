package com.itheima.contentobserver;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取内容解析者
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.itheima.provider");
        MyObserver observer = new MyObserver(new Handler());
        //第二个参数 如果传入true 只要uri前面的部分匹配上了 就可以收到通知
        //如果是false 只有整个URI都匹配上 才能收到通知
		resolver.registerContentObserver(uri, false, observer);
    }


   private class MyObserver extends ContentObserver{

	public MyObserver(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}
	   @Override
	public void onChange(boolean selfChange, Uri uri) {
		   System.out.println(uri);
	}
   }
    
}
