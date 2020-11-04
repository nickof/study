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
        //getResources() �����ĵķ��� ����Ӧ���е�resĿ¼����
        //��resĿ¼�¼��ؽ�����bitmap �ǲ��ܱ��޸ĵ�
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
//        iv_image.setImageBitmap(bitmap);
//        bitmap.setPixel(30, 30, Color.RED);
//        iv_image.setImageBitmap(bitmap);
        //ʹ��ԭͼ ����һ�������޸ĵ�bitmap����   ��һ������ͼƬ�Ŀ�� �ڶ�������ͼƬ�ĸ߶� ���������� ͼƬ��������Ϣ ����ԭͼ��ȡ
        Bitmap copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //����һ��Canvas(����) ���� ͨ�����������bitmap�л����� ��Ҫסһ�� ���bitmap���������mutable(�����޸ĵ�) ���Դ���մ����Ŀհ�ͼƬ
        Canvas canvas = new Canvas(copybm);
        //ͨ��Matrix���Զ�ͼƬ���д���
        Matrix matrix = new Matrix();
        //paint ����
		Paint paint = new Paint();
		//ִ����������� ԭͼ�����ݾͻử�������Ŀյ�ͼƬ��
		canvas.drawBitmap(bitmap, matrix, paint);
		for(int i = 0;i<30;i++){
			copybm.setPixel(30+i, 30+i, Color.RED);
		}
		
		iv_image.setImageBitmap(copybm);
    }


    
}
