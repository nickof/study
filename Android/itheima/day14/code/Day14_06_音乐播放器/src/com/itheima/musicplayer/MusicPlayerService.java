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
		 * ������ͣ
		 */
		public void playPause(){
			//player.isPlaying() ͨ����������ж� ���ŵ�״̬ �������true ˵�����ڲ���
			if(player.isPlaying()){
				//������ڲ���
				player.pause();
			}else{
				player.start();
			}
		}
		
		/**
		 * ���ص�ǰ�������Ĳ���״̬
		 * @return
		 */
		public boolean isPlaying(){
			return player.isPlaying();
		}
		
		/**
		 * ��ȡ��ǰ���ֵ���ʱ��
		 * @return ����ʱ���ĺ���ֵ
		 */
		public int getDuration(){
			return player.getDuration();
		}
		
		/**
		 * ��ȡ��ǰ���ŵĽ���
		 * @return ��ǰ���Ž��ȵĺ���ֵ
		 */
		public int getCurrentPosition(){
			return player.getCurrentPosition();
		}
		
		/**
		 * ����һ�����ŵĺ���ֵ ����ֱ����ת�����ʱ�俪ʼ����
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
