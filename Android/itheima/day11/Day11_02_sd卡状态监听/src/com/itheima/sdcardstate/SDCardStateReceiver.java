package com.itheima.sdcardstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SDCardStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		if("android.intent.action.MEDIA_UNMOUNTED".equals(action)){
			System.out.println("sdcardж����");
		}else if("android.intent.action.MEDIA_MOUNTED".equals(action)){
			System.out.println("sdCard��װ����");
		}
	}
	
}
