package com.itheima.serviceregisterreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action)){
			System.out.println("屏幕关闭开始上传");
		}else if(Intent.ACTION_SCREEN_ON.equals(action)){
			System.out.println("屏幕点亮停止上传");
		}

	}

}
