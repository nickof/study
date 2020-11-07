package com.itheima.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("来短信了");
		//pdu protocal data unit
		Object[] object = (Object[]) intent.getExtras().get("pdus");
		for(Object obj:object){
			//创建短信的消息对象
			SmsMessage message = SmsMessage.createFromPdu((byte[])obj);
			//获取短信的发送者
			String from = message.getOriginatingAddress();
			//获取消息内容
			String messageBody = message.getMessageBody();
			System.out.println("from"+from+"body"+messageBody);
			//判断 from 究竟是不是我自己发的
			if("12345".equals(from)){
				//通知界面 把短信的内容设置到edittext里
				Intent data = new Intent();
				data.setAction("com.itheima.getcode");
				data.putExtra("code", messageBody);
				context.sendBroadcast(data);
			}
		}
	}

}
