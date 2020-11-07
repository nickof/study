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
        //��ͼ����������
		IntentFilter filter = new IntentFilter();
		//����ͼ���������action ���action����Ҫ�����Ĺ㲥��Ӧ��action
		filter.addAction("android.intent.action.SCREEN_OFF");
		filter.addAction("android.intent.action.SCREEN_ON");
		//��̬ע��һ���㲥������
		registerReceiver(receiver, filter);
    }
    

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	//��̬ע��Ĺ㲥������ �ڵ�ǰactivity���ٵ�ʱ����Ҫע���� unregisterReceiver ע����̬ע��Ĺ㲥������
    	unregisterReceiver(receiver);
    }
   
}
