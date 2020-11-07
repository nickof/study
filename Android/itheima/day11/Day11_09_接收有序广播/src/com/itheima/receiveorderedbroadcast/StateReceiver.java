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
		setResultData("皇帝发放赈灾粮,每人60斤");
		//中断广播 有序广播可以中断
		//abortBroadcast();
	}

}
