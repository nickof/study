package com.itheima.bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("机器开启了");
		Intent intent2 = new Intent(context,MainActivity.class);
		//现在是在广播接收者中创建一个activity 当前应用没有任何activity在运行 所以不存在一个任务栈
		//需要通过指定一个flag 在创建activity的同时创建一个任务栈
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}

}
