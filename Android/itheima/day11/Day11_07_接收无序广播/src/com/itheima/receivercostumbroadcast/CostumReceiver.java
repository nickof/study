package com.itheima.receivercostumbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CostumReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
			System.out.println("�յ����Զ��Ĺ㲥"+intent.getStringExtra("key"));
			abortBroadcast();
	}

}
