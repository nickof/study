package com.itheima.replacefragmentdynamically;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        //�ٻ�ȡfragmentmanager
        FragmentManager manager = getFragmentManager();
        //�ڿ���fragment����
        FragmentTransaction transaction = manager.beginTransaction();
        
        if(width>height){
        	//����
        	//�۰�fragment���� �滻�� viewgroup�ڵ���
        	//��һ������ ��������fragment��viewgroup��id
        	//�ڶ������� Ҫ��ʾ��fragment����
        	transaction.replace(R.id.fragment_container, new SecondFragment());
        }else{
        	//����
        	transaction.replace(R.id.fragment_container, new FirstFragment());
        }
        //���������Ӧ��fragmentһ��Ҫ����commit�ύ����
        transaction.commit();
    }
}
