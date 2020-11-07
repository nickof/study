package com.itheima.orderedbroadcast;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v){
    	Intent intent = new Intent();
    	intent.setAction("com.itheima.sendrice");
    	//收到广播时需要的权限
		String receiverPermission = null;
		//作为最终的广播接收者
		BroadcastReceiver resultReceiver = new FinalReceiver();
		//处理最终的广播接收者用到的handler 如果传Null 会在主线程处理
		Handler scheduler = null;
		//初始化的数据
		String initialData = "皇帝发放赈灾粮,每人100斤";
		sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, Activity.RESULT_OK, initialData, null);
    }
    
}
