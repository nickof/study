package com.itheima.erasecloth;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Bitmap copybm;
	private ImageView iv_image;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image = (ImageView) findViewById(R.id.iv_front);
        
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.front);
        copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //②准备画布
        Canvas canvas = new Canvas(copybm);
        //③把原图画到空的图片上
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
        iv_image.setImageBitmap(copybm);
        
        iv_image.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					float x = event.getX();
					float y = event.getY();
					//修改手指移动过的像素点为透明
					try {
						for(int i=-6;i<=6;i++){
							for(int j = -6;j<=6;j++){
								if(Math.sqrt(i*i+j*j)<=6){
									//这里修改的是原图的 x y 对应的像素点的颜色
									//但是获取的是屏幕的坐标 如果是图片是在Imageview上点对点的显示 就不会有问题
									copybm.setPixel((int)x+i, (int)y+j, Color.TRANSPARENT);	
								}
							}
						}
					} catch (Exception e) {
					}
					
					//设置到imageview展示效果
					iv_image.setImageBitmap(copybm);
					break;

				default:
					break;
				}
				return true;
			}
		});
    }


    
    
}
