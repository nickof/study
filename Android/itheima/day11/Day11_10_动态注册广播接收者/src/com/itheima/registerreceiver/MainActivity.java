package com.itheima.registerreceiver;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.view.Menu;

public class MainActivity extends Activity {

    private BroadcastReceiver receiver;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new ScreenLightReceiver();
        //意图过滤器对象
		IntentFilter filter = new IntentFilter();
		//给意图过滤器添加action 这个action就是要监听的广播对应的action
		filter.addAction("android.intent.action.SCREEN_OFF");
		filter.addAction("android.intent.action.SCREEN_ON");
		//动态注册一个广播接收者
		registerReceiver(receiver, filter);
    }
    

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	//动态注册的广播接收者 在当前activity销毁的时候需要注销掉 unregisterReceiver 注销动态注册的广播接收者
    	unregisterReceiver(receiver);
    }
   
}
