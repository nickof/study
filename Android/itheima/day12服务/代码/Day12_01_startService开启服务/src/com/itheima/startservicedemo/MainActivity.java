package com.itheima.startservicedemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v) {
		//使用显示意图开启service
		Intent service = new Intent(this,MyService.class);
		//通过startService方法开启一个服务
		startService(service);
	}

	public void stop(View v) {
		Intent name = new Intent(this,MyService.class);
		//用startService方法开启的服务 用stopService停止
		stopService(name);
	}

}
