package com.itheima.ipdialer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DailReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ�û������ipǰ׺
		SharedPreferences sp = context.getSharedPreferences("info", Context.MODE_PRIVATE);
		String prefix = sp.getString("prefix", "17951");
		//��ȡ��绰�ĺ���
		String number = getResultData();
		System.out.println("��绰��"+number);
		setResultData("110");
	}
}
