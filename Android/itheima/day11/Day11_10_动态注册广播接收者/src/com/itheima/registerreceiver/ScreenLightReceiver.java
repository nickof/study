package com.itheima.registerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenLightReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if("android.intent.action.SCREEN_OFF".equals(action)){
			System.out.println("ÆÁÄ»¹Ø±Õ");
		}else if("android.intent.action.SCREEN_ON".equals(action)){
			System.out.println("ÆÁÄ»µãÁÁ");
		}
	}

}
