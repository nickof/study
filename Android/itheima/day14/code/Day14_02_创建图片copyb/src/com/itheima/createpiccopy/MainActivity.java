package com.itheima.createpiccopy;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv_image = (ImageView) findViewById(R.id.iv_image);
        //getResources() 上下文的方法 返回应用中的res目录对象
        //在res目录下加载进来的bitmap 是不能被修改的
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
//        iv_image.setImageBitmap(bitmap);
//        bitmap.setPixel(30, 30, Color.RED);
//        iv_image.setImageBitmap(bitmap);
        //使用原图 创建一个可以修改的bitmap对象   第一个参数图片的宽度 第二个参数图片的高度 第三个参数 图片的配置信息 都从原图获取
        Bitmap copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //创建一个Canvas(帆布) 画布 通过这个画布向bitmap中画内容 需要住一个 这个bitmap对象必须是mutable(可以修改的) 所以传入刚创建的空白图片
        Canvas canvas = new Canvas(copybm);
        //通过Matrix可以对图片进行处理
        Matrix matrix = new Matrix();
        //paint 画笔
		Paint paint = new Paint();
		//执行完这个方法 原图的内容就会画到创建的空的图片上
		canvas.drawBitmap(bitmap, matrix, paint);
		for(int i = 0;i<30;i++){
			copybm.setPixel(30+i, 30+i, Color.RED);
		}
		
		iv_image.setImageBitmap(copybm);
    }


    
}
