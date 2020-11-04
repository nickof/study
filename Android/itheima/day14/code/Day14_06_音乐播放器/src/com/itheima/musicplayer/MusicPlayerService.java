package com.itheima.musicplayer;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayerService extends Service {
	private String path = "mnt/sdcard/xpg.mp3";
	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		return new MyBinder();
	}

	public class MyBinder extends Binder{
		/**
		 * 播放暂停
		 */
		public void playPause(){
			//player.isPlaying() 通过这个方法判断 播放的状态 如果返回true 说明正在播放
			if(player.isPlaying()){
				//如果正在播放
				player.pause();
			}else{
				player.start();
			}
		}
		
		/**
		 * 返回当前播放器的播放状态
		 * @return
		 */
		public boolean isPlaying(){
			return player.isPlaying();
		}
		
		/**
		 * 获取当前音乐的总时长
		 * @return 音乐时长的毫秒值
		 */
		public int getDuration(){
			return player.getDuration();
		}
		
		/**
		 * 获取当前播放的进度
		 * @return 当前播放进度的毫秒值
		 */
		public int getCurrentPosition(){
			return player.getCurrentPosition();
		}
		
		/**
		 * 传入一个播放的毫秒值 可以直接跳转到这个时间开始播放
		 * @param msec
		 */
		public void seekTo(int msec){
			player.seekTo(msec);
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();
		try {
			player.setDataSource(path);
			player.prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
