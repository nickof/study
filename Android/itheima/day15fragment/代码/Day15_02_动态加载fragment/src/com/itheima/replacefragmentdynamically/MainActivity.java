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
        //①获取fragmentmanager
        FragmentManager manager = getFragmentManager();
        //②开启fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        
        if(width>height){
        	//横屏
        	//③把fragment对象 替换到 viewgroup节点下
        	//第一个参数 用来放置fragment的viewgroup的id
        	//第二个参数 要显示的fragment对象
        	transaction.replace(R.id.fragment_container, new SecondFragment());
        }else{
        	//竖屏
        	transaction.replace(R.id.fragment_container, new FirstFragment());
        }
        //④设置完对应的fragment一定要调用commit提交事务
        transaction.commit();
    }
}
