package com.itheima.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("��������");
		//pdu protocal data unit
		Object[] object = (Object[]) intent.getExtras().get("pdus");
		for(Object obj:object){
			//�������ŵ���Ϣ����
			SmsMessage message = SmsMessage.createFromPdu((byte[])obj);
			//��ȡ���ŵķ�����
			String from = message.getOriginatingAddress();
			//��ȡ��Ϣ����
			String messageBody = message.getMessageBody();
			System.out.println("from"+from+"body"+messageBody);
			//�ж� from �����ǲ������Լ�����
			if("12345".equals(from)){
				//֪ͨ���� �Ѷ��ŵ��������õ�edittext��
				Intent data = new Intent();
				data.setAction("com.itheima.getcode");
				data.putExtra("code", messageBody);
				context.sendBroadcast(data);
			}
		}
	}

}
