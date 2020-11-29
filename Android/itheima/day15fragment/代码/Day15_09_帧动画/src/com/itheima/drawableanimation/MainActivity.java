package com.itheima.drawableanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_image = (ImageView) findViewById(R.id.iv_image);
        
        //找到图片背景对应的Drawable对象 强制转换为AnimationDrawable
        AnimationDrawable animation = (AnimationDrawable) iv_image.getBackground();
        //调用start方法开始动画
        animation.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
