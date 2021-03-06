package com.itheima.musicplayerframework;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayerService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		//③onCreate之后执行onBind
		return new MyBinder();
	}
	public class MyBinder extends Binder implements IService{
		//播放下一首
		public void callNext(){
			next();
		}
		//播放上一首
		public void callPre() {
			pre();
		}
		//播放音乐
		public void callplay() {
			play();
		}
		//暂停
		public void callpause() {
			pause();
		}
		
		public void playHiFiMusic(){
			
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// MediaPlayer
		//②bindService之后执行 onCreate()
		System.out.println("准备一个音乐播放器");
	}

	public void next() {
		System.out.println("播放下一首... 小苹果");
	}

	public void pre() {
		System.out.println("播放上一首... 小苹果");
	}

	public void play() {
		System.out.println("播放上一首... 江南皮革厂 倒闭了");
	}

	public void pause() {
		System.out.println("暂停");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestory");
	}
	
}
