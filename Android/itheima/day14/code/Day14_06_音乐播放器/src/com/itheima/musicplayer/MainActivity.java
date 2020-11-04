package com.itheima.musicplayer;

import java.io.IOException;

import com.itheima.musicplayer.MusicPlayerService.MyBinder;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private static final int UPDATE_PROGRESS = 0;
	public String path = "mnt/sdcard/xpg.mp3";
	private MyConnection conn;
	private MyBinder musicController;
	private ImageButton ib_play;
	private SeekBar sb_progress;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE_PROGRESS:
				updateProgress();
				break;

			default:
				break;
			}
			
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ib_play = (ImageButton) findViewById(R.id.ib_play);
		sb_progress = (SeekBar) findViewById(R.id.sb_progress);
		//设置进度条进度变化的监听
		sb_progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//停止触摸seekbar
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				//停止操作seekbar
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				//当播放状态变化的时候会调用这个方法
				if(fromUser){//如果fromUser 为true说明是用户在拖动进度条
					//修改当前音乐播放器的播放进度
					musicController.seekTo(progress);
				}
			}
		});
		
		//混合方式开启音乐播放的服务
		Intent service = new Intent(this, MusicPlayerService.class);
		startService(service);
		conn = new MyConnection();
		bindService(service, conn, BIND_AUTO_CREATE);
		System.out.println("onCreate");
	}

	public void play(View v) {
		//调用音乐的播放暂停方法 
		musicController.playPause();
		//根据当前播放的状态更新图标
		updatePlayIcon();
	}

	/**
	 * 根据播放状态更新播放图标
	 */
	private void updatePlayIcon() {
		if(musicController.isPlaying()){
			ib_play.setImageResource(R.drawable.btn_audio_pause);
			//发空消息 开始更新seekbar
			handler.sendEmptyMessage(UPDATE_PROGRESS);
		}else{
			ib_play.setImageResource(R.drawable.btn_audio_play);
			//暂停的时候 没有必要再一直更新状态 可以把跟新状态的消息移除
			handler.removeMessages(UPDATE_PROGRESS);
		}
	}
	
	/**
	 * 根据当前音乐播放的状态来更新进度条的进度
	 */
	private void updateProgress(){
		System.out.println("updateProgress");
		//获取当前播放的进度
		int currentPosition = musicController.getCurrentPosition();
		//设置到seekbar展示当前播放的状态
		sb_progress.setProgress(currentPosition);
		//发送一个延迟500毫秒执行的消息 通过handler 500ms后再次跟新seekbar
		handler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 500);
	}

	class MyConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("onServiceConnected");
			//在这个方法中 一定会获取到musicController 这个MyBinder对象
			// 因为所有控制音乐的方法都需要使用MyBinder来调用  所以 初始化界面的方法都写在这里
			musicController = (MyBinder) service;
			//更新播放图标
			updatePlayIcon();
			//设置进度条的总时长
			sb_progress.setMax(musicController.getDuration());
			//更新播放进度
			sb_progress.setProgress(musicController.getCurrentPosition());
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}
	@Override
	protected void onStop() {
		super.onStop();
		//停止更新进度条的进度
		handler.removeCallbacksAndMessages(null);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//跟service解除绑定
		unbindService(conn);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(musicController!=null){
			//开始更新进度条的进度
			handler.sendEmptyMessage(UPDATE_PROGRESS);
		}
		System.out.println("onResume");
	}
}
