package com.itheima.receiveorderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String resultData = getResultData();
		Toast.makeText(context, resultData+"===="+1, Toast.LENGTH_SHORT).show();
		setResultData("�ʵ۷���������,ÿ��60��");
		//�жϹ㲥 ����㲥�����ж�
		//abortBroadcast();
	}

}
