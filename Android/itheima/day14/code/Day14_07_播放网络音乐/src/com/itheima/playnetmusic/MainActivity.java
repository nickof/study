package com.itheima.playnetmusic;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    private MediaPlayer player;
    private String path = "http://10.0.2.2:8080/xpg.mp3";


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


   public void play(View v){
	   if(player == null){
		   player = new MediaPlayer();
		   try {
			   player.setDataSource(path);
//		�첽׼��
			   player.prepareAsync();
			   //���׼���õļ���
			   player.setOnPreparedListener(new OnPreparedListener() {
				   
				   @Override
				   public void onPrepared(MediaPlayer mp) {
					   // �����ǰý���ļ��Ѿ�׼������ �ͻ���onPrepared
					   mp.start();
				   }
			   });
		   } catch (Exception e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
	   }else{
		   if(player.isPlaying()){
			   player.pause();
		   }else{
			   player.start();
		   }
	   }
   }
    
}
