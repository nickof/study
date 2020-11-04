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
		//���ý��������ȱ仯�ļ���
		sb_progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//ֹͣ����seekbar
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				//ֹͣ����seekbar
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				//������״̬�仯��ʱ�������������
				if(fromUser){//���fromUser Ϊtrue˵�����û����϶�������
					//�޸ĵ�ǰ���ֲ������Ĳ��Ž���
					musicController.seekTo(progress);
				}
			}
		});
		
		//��Ϸ�ʽ�������ֲ��ŵķ���
		Intent service = new Intent(this, MusicPlayerService.class);
		startService(service);
		conn = new MyConnection();
		bindService(service, conn, BIND_AUTO_CREATE);
		System.out.println("onCreate");
	}

	public void play(View v) {
		//�������ֵĲ�����ͣ���� 
		musicController.playPause();
		//���ݵ�ǰ���ŵ�״̬����ͼ��
		updatePlayIcon();
	}

	/**
	 * ���ݲ���״̬���²���ͼ��
	 */
	private void updatePlayIcon() {
		if(musicController.isPlaying()){
			ib_play.setImageResource(R.drawable.btn_audio_pause);
			//������Ϣ ��ʼ����seekbar
			handler.sendEmptyMessage(UPDATE_PROGRESS);
		}else{
			ib_play.setImageResource(R.drawable.btn_audio_play);
			//��ͣ��ʱ�� û�б�Ҫ��һֱ����״̬ ���԰Ѹ���״̬����Ϣ�Ƴ�
			handler.removeMessages(UPDATE_PROGRESS);
		}
	}
	
	/**
	 * ���ݵ�ǰ���ֲ��ŵ�״̬�����½������Ľ���
	 */
	private void updateProgress(){
		System.out.println("updateProgress");
		//��ȡ��ǰ���ŵĽ���
		int currentPosition = musicController.getCurrentPosition();
		//���õ�seekbarչʾ��ǰ���ŵ�״̬
		sb_progress.setProgress(currentPosition);
		//����һ���ӳ�500����ִ�е���Ϣ ͨ��handler 500ms���ٴθ���seekbar
		handler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 500);
	}

	class MyConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("onServiceConnected");
			//����������� һ�����ȡ��musicController ���MyBinder����
			// ��Ϊ���п������ֵķ�������Ҫʹ��MyBinder������  ���� ��ʼ������ķ�����д������
			musicController = (MyBinder) service;
			//���²���ͼ��
			updatePlayIcon();
			//���ý���������ʱ��
			sb_progress.setMax(musicController.getDuration());
			//���²��Ž���
			sb_progress.setProgress(musicController.getCurrentPosition());
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}
	@Override
	protected void onStop() {
		super.onStop();
		//ֹͣ���½������Ľ���
		handler.removeCallbacksAndMessages(null);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//��service�����
		unbindService(conn);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(musicController!=null){
			//��ʼ���½������Ľ���
			handler.sendEmptyMessage(UPDATE_PROGRESS);
		}
		System.out.println("onResume");
	}
}
