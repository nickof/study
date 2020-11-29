package com.itheima.notificationdemo;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

@SuppressLint("NewApi") public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v){
    	//ͨ��Notification.Builder ����һ��notification
    	Notification.Builder builder = new Builder(this);
    	//֪ͨ��һ���յ���ʱ�� ����״̬������ʾ���� ������־���ͨ��setTicker���õ�
    	builder.setTicker("�˻��춯֪ͨ:�����˻� ����ϴǮ����,�Ѿ����췽����,�ⶳ����������������ϵ,�绰����13987654321");
    	//���õ�ǰ��֪ͨ �û����֮�����ʧ��
    	builder.setAutoCancel(true);
    	//������֪ͨ������ʾ�Ĵ����
    	builder.setContentTitle("�˻��춯֪ͨ");
    	//������֪ͨ������ʾ��С����������
    	builder.setContentText("�����˻� ����ϴǮ����,�Ѿ����췽����,�ⶳ����������������ϵ,�绰����13987654321");
    	//������״̬����ʾ��Сͼ��
    	builder.setSmallIcon(R.drawable.ic_launcher);
    	//builder.setOngoing(true);
    	Intent intent2 = new Intent(this,MainActivity.class);
    	//pendingIntent �ӳ�ִ�е���ͼ ��֪ͨ�������ʱ�� �ͻ�ִ��intent
    	//��������activity getActivity
    	//��������service getService
    	//�ڶ������� ������  �������ֲ�ͬ��pendingIntent
    	//���������� ��ͼ 
    	//���ĸ�����  ��FLAG_UPDATE_CURRENT ����pendingintent
		PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(intent);
    	Notification notification = builder.build();
    	//��ȡnotificationManager
    	NotificationManager mananger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	//����������� ֪ͨ�ᷢ�͵�֪ͨ����
    	//��һ������ ֪ͨ��id
    	mananger.notify(1, notification);
    	mananger.cancel(1);
    }
    
}
