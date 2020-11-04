package com.itheima.paintboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
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

	private Canvas canvas;
	private Paint paint;
	private ImageView iv_image;
	private Bitmap copybm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_image = (ImageView) findViewById(R.id.iv_image);
//		①把背景图加载进来
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg);
		//② 创建图片的副本
		copybm = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		canvas = new Canvas(copybm);
		paint = new Paint();
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		iv_image.setImageBitmap(copybm);
		//给imageView设置一个触摸的监听
		iv_image.setOnTouchListener(new OnTouchListener() {

			private float startX;
			private float startY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					System.out.println("按下");
					//记录按下位置
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					System.out.println("移动");
					float x = event.getX();
					float y = event.getY();
					//使用canvas画线 线画在copybm 这个对象上
					canvas.drawLine(startX, startY, x, y, paint);
					System.out.println("x= "+x+"y="+y);
					//用imageview展示修改过的图片
					iv_image.setImageBitmap(copybm);
					startX = x;
					startY = y;
					
					break;
				case MotionEvent.ACTION_UP:
					System.out.println("抬起");
					break;

				default:
					break;
				}
				//返回true说明当前控件消费了这一些类的touch事件
				//touch事件是由 一个ACTION_DOWN 和 ACTION_MOVE(0个或多个) 和一个ACTION_UP组成的
				//如果ACTION_DOWN的时候没有返回true则 ACTION_MOVE ACTION_UP 就会交给父控件处理
				return true;
			}
		});
	}

	public void changeColor(View v){
		//更改画笔颜色
		paint.setColor(Color.RED);
	}
	
	public void bold(View v){
		//修改画笔的宽度
		paint.setStrokeWidth(5);
	}

	public void save(View v){
		//保存文件到sd卡
		File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".png");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			//通过这方法可以把文件保存到sd卡上
			//第一参数  保存的格式  
			//第二个参数保存的质量 0 代表质量差体积下 100代表质量最高 体积最大 
			//第三个参数 输出流 确定保存的位置
			copybm.compress(CompressFormat.PNG, 100, fos);
			//发送sd卡挂上的广播 系统受到广播之后 会扫描sd卡 把新保存的文件路径添加到数据库中
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
