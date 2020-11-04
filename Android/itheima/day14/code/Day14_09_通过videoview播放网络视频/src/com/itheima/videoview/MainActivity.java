package com.itheima.videoview;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    private VideoView vv_video;
    private String path = "http://10.0.2.2:8080/rmvb.rmvb";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
			return;
		}
		
        setContentView(R.layout.activity_main);
        vv_video = (VideoView) findViewById(R.id.vv_video);
    }

	public void prepare(View v){
		//������Ƶ��·�� setVideoPath������ ������һ��mediaPlayer����  ������setDataSource
		//�������첽׼���ķ���
		vv_video.setVideoPath(path);
		//���ü���
		vv_video.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				vv_video.start();
			}
		});
		//����Ĭ�ϵĽ����� 
		vv_video.setMediaController(new MediaController(this));
	}
  
    public void start(View v){
    	
    }
}
