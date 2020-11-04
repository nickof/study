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
        
        //�ټ���ͼƬ��Դ
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
        //�ڴ���ͼƬ�ĸ���
        //2.1 ����ԭͼ�Ĵ�С��������Ϣ����һ��һ����Ŀյ�ͼƬ
       Bitmap copybm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //2.2 ׼��һ������ �ѿյ�ͼƬ�ŵ�������
        Canvas canvas = new Canvas(copybm);
        //2.3 ͨ���������� drawBitmap���� ��ԭͼ�����յ�ͼƬ��
        Matrix matrix = new Matrix();
        //scale translate  rotate
        //setRotate������ת�ĽǶ�
       // matrix.setRotate(90,copybm.getWidth()/2,copybm.getHeight()/2);
        //setTranslateƽ�� ��һ������ x����ƽ�Ƶľ���  �ڶ������� y����ƽ�Ƶľ���
       // matrix.setTranslate(30, 0);
        //scale���� �������һ������ �ͻ��о����Ч��
        matrix.setScale(1f, -1f);
        //��ε���set ���һ�λ��֮ǰ��Ч�������
        //�������֮ǰ��Ч�������Ͻ�һ������ͼƬ ��Ҫ����postXXXX����
        //matrix.postTranslate(copybm.getWidth(), 0);
        matrix.postTranslate(0, copybm.getHeight());
		Paint paint = new Paint();
		canvas.drawBitmap(bitmap, matrix, paint);
		
		iv_image2.setImageBitmap(copybm);
		
    }


   
    
}
