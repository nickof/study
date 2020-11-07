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
		setResultData("皇恩浩荡,今年受灾,免除个人所交的粮食");

	}

}
