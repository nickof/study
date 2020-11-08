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
        //��ȡ���ݽ�����
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.itheima.provider");
        MyObserver observer = new MyObserver(new Handler());
        //�ڶ������� �������true ֻҪuriǰ��Ĳ���ƥ������ �Ϳ����յ�֪ͨ
        //�����false ֻ������URI��ƥ���� �����յ�֪ͨ
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
