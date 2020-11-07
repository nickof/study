package com.itheima.ipdialer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DailReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取用户输入的ip前缀
		SharedPreferences sp = context.getSharedPreferences("info", Context.MODE_PRIVATE);
		String prefix = sp.getString("prefix", "17951");
		//获取打电话的号码
		String number = getResultData();
		System.out.println("打电话了"+number);
		setResultData("110");
	}
}
