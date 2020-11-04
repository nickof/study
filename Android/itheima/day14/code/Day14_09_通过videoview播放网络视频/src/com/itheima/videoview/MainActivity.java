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
		//设置视频的路径 setVideoPath方法中 创建了一个mediaPlayer对象  调用了setDataSource
		//调用了异步准备的方法
		vv_video.setVideoPath(path);
		//设置监听
		vv_video.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				vv_video.start();
			}
		});
		//设置默认的进度条 
		vv_video.setMediaController(new MediaController(this));
	}
  
    public void start(View v){
    	
    }
}
