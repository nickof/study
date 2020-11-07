package com.itheima.sendbroadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void sendbroadcast(View v){
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.broadcast");
    	intent.putExtra("key", "hello");
		sendBroadcast(intent);
    }
}
