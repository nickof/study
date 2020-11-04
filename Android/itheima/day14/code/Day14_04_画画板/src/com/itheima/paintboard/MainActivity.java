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
//		�ٰѱ���ͼ���ؽ���
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.bg);
		//�� ����ͼƬ�ĸ���
		copybm = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		canvas = new Canvas(copybm);
		paint = new Paint();
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		iv_image.setImageBitmap(copybm);
		//��imageView����һ�������ļ���
		iv_image.setOnTouchListener(new OnTouchListener() {

			private float startX;
			private float startY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					System.out.println("����");
					//��¼����λ��
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					System.out.println("�ƶ�");
					float x = event.getX();
					float y = event.getY();
					//ʹ��canvas���� �߻���copybm ���������
					canvas.drawLine(startX, startY, x, y, paint);
					System.out.println("x= "+x+"y="+y);
					//��imageviewչʾ�޸Ĺ���ͼƬ
					iv_image.setImageBitmap(copybm);
					startX = x;
					startY = y;
					
					break;
				case MotionEvent.ACTION_UP:
					System.out.println("̧��");
					break;

				default:
					break;
				}
				//����true˵����ǰ�ؼ���������һЩ���touch�¼�
				//touch�¼����� һ��ACTION_DOWN �� ACTION_MOVE(0������) ��һ��ACTION_UP��ɵ�
				//���ACTION_DOWN��ʱ��û�з���true�� ACTION_MOVE ACTION_UP �ͻύ�����ؼ�����
				return true;
			}
		});
	}

	public void changeColor(View v){
		//���Ļ�����ɫ
		paint.setColor(Color.RED);
	}
	
	public void bold(View v){
		//�޸Ļ��ʵĿ��
		paint.setStrokeWidth(5);
	}

	public void save(View v){
		//�����ļ���sd��
		File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".png");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			//ͨ���ⷽ�����԰��ļ����浽sd����
			//��һ����  ����ĸ�ʽ  
			//�ڶ���������������� 0 ��������������� 100����������� ������ 
			//���������� ����� ȷ�������λ��
			copybm.compress(CompressFormat.PNG, 100, fos);
			//����sd�����ϵĹ㲥 ϵͳ�ܵ��㲥֮�� ��ɨ��sd�� ���±�����ļ�·����ӵ����ݿ���
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
