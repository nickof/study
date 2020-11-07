package com.itheima.musicplayerframework;

import com.itheima.musicplayerframework.MusicPlayerService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private MyConnection conn;
	private MyBinder musicControl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 通过bind方式开启音乐播放的服务
		Intent service = new Intent(this, MusicPlayerService.class);
		conn = new MyConnection();
		//①调用bindService
		bindService(service, conn, BIND_AUTO_CREATE);
		//混合方式开启服务
		startService(service);
	}

	public void pre(View v) {
		musicControl.callPre();
	}

	public void play(View v) {
		musicControl.callplay();
	}

	public void pause(View v) {
		musicControl.callpause();
	}

	public void next(View v) {
		musicControl.callNext();
	}

	private class MyConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//④service执行完onBind之后 返回值不为空 就会走这个方法  IBinder对象就是 onBind的返回值  获取到这个对象 就可以调用它的public方法
			musicControl = (MyBinder) service;
			musicControl.playHiFiMusic();
			IService iservice = (IService) service;
			iservice.callpause();
			iservice.callpause();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
	}

}
