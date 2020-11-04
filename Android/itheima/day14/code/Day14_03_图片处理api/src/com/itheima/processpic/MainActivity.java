package com.itheima.processpic;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_image2 = (ImageView) findViewById(R.id.iv_image2);
        
        //①加载图片资源
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
        //②创建图片的副本
        //2.1 根据原图的大小和配置信息创建一个一样大的空的图片
       Bitmap copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //2.2 准备一个画布 把空的图片放到画布上
        Canvas canvas = new Canvas(copybm);
        //2.3 通过画布调用 drawBitmap方法 把原图画到空的图片上
        Matrix matrix = new Matrix();
        //scale translate  rotate
        //setRotate设置旋转的角度
       // matrix.setRotate(90,copybm.getWidth()/2,copybm.getHeight()/2);
        //setTranslate平移 第一个参数 x方向平移的距离  第二个参数 y方向平移的距离
       // matrix.setTranslate(30, 0);
        //scale缩放 如果传入一个负数 就会有镜像的效果
        matrix.setScale(1f, -1f);
        //多次调用set 最近一次会把之前的效果清除掉
        //如果想在之前的效果基础上进一步处理图片 需要调用postXXXX方法
        //matrix.postTranslate(copybm.getWidth(), 0);
        matrix.postTranslate(0, copybm.getHeight());
		Paint paint = new Paint();
		canvas.drawBitmap(bitmap, matrix, paint);
		
		iv_image2.setImageBitmap(copybm);
		
    }


   
    
}
