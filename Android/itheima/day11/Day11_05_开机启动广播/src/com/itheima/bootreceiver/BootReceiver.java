package com.itheima.bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("����������");
		Intent intent2 = new Intent(context,MainActivity.class);
		//�������ڹ㲥�������д���һ��activity ��ǰӦ��û���κ�activity������ ���Բ�����һ������ջ
		//��Ҫͨ��ָ��һ��flag �ڴ���activity��ͬʱ����һ������ջ
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}

}
