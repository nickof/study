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
		// ͨ��bind��ʽ�������ֲ��ŵķ���
		Intent service = new Intent(this, MusicPlayerService.class);
		conn = new MyConnection();
		//�ٵ���bindService
		bindService(service, conn, BIND_AUTO_CREATE);
		//��Ϸ�ʽ��������
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
			//��serviceִ����onBind֮�� ����ֵ��Ϊ�� �ͻ����������  IBinder������� onBind�ķ���ֵ  ��ȡ��������� �Ϳ��Ե�������public����
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
