package com.itheima.serviceregisterreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(Intent.ACTION_SCREEN_OFF.equals(action)){
			System.out.println("��Ļ�رտ�ʼ�ϴ�");
		}else if(Intent.ACTION_SCREEN_ON.equals(action)){
			System.out.println("��Ļ����ֹͣ�ϴ�");
		}

	}

}
