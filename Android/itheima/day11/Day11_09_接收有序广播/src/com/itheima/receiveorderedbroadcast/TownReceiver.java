package com.itheima.receiveorderedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TownReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String resultData = getResultData();
		Toast.makeText(context, resultData+"===="+3, Toast.LENGTH_SHORT).show();
		setResultData("�ʶ��Ƶ�,��������,���������������ʳ");

	}

}
