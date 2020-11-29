package com.itheima.fragmentcommunication;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //第三个参数 给fragment添加一个标签
        transaction.replace(R.id.ll_left, new LeftFragment(),"left");
        transaction.replace(R.id.ll_right, new RightFragment(),"right");
        transaction.commit();
    }

//    public void change(View v){
//		System.out.println("hello");
//	}
}
