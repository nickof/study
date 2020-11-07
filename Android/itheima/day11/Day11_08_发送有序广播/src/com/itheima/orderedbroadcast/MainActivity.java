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
    	//�յ��㲥ʱ��Ҫ��Ȩ��
		String receiverPermission = null;
		//��Ϊ���յĹ㲥������
		BroadcastReceiver resultReceiver = new FinalReceiver();
		//�������յĹ㲥�������õ���handler �����Null �������̴߳���
		Handler scheduler = null;
		//��ʼ��������
		String initialData = "�ʵ۷���������,ÿ��100��";
		sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, Activity.RESULT_OK, initialData, null);
    }
    
}
