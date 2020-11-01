package com.itheima.loadpic;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView iv_image;
    private String path = "mnt/sdcard/dog.jpg";
	private int screenWidth;
	private int screenHeight;

	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image = (ImageView) findViewById(R.id.iv_pic);
      //�ٻ�ȡ��Ļ�Ŀ��
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//        System.out.println("width==="+width+"height==="+height);
        Point outSize = new Point();
        //�������������, ��Ļ�Ŀ����Ϣ�ͱ��浽��point������
		getWindowManager().getDefaultDisplay().getSize(outSize);
		 System.out.println("width==="+outSize.x+"height==="+outSize.y);
    }


    public void loadpic(View v){
    	//����bitmap����
//    	Bitmap bitmap = BitmapFactory.decodeFile(path);
//    	//��bitmap��ʾ��imageview��
//    	iv_image.setImageBitmap(bitmap);
    	loadpic3();
    }
    
    public void loadpic1(){
    	//options ѡ�����˼  ͨ������Options���� ����BitmapFactory�ڽ���ͼƬ��ʱ��
    	//����һЩ���úõ�������Ϣ ���� ѹ��ͼƬ��ѹ������  ���� �Ƿ��������ͼƬ......
    	BitmapFactory.Options options = new Options();
    	//inSampleSize sample ��������˼  
    	//inSampleSize ͨ���������ֵ����ѹ��ͼƬ ���inSampleSize=2 ��ô���ص��ڴ��е�ͼƬ
    	//�����ԭͼ��1/2 �߶�Ҳ��ԭͼ��1/2 �ܵĴ�С��ԭͼ��1/4; ��Ҫע�� ���inSampleSize<1 �ᰴ1����
    	//������Ĭ�ϻᰴ��2����ָ��������ͼƬ��ѹ�� ���Կ��Դ���2����ָ������ΪinSampleSize��ֵ
    	options.inSampleSize = 2;
    	//BitmapFactory.de
    	Bitmap bitmap = BitmapFactory.decodeFile(path, options);
    	iv_image.setImageBitmap(bitmap);
    }
    
    public void loadpic2(){
    	
    	//�Ƚ�ͼƬ����Ļ�ķֱ��� ���ͼƬ�ֱ��ʱ���Ļ�ֱ��ʸ� 
    	//����ʹ�� ͼƬ���/��Ļ�Ŀ��
    	//ͼƬ�ĸ߶�/��Ļ�ĸ߶�
    	//ͨ����������ֵ��ȷ�� inSampleSize
    	
    	
    	//�ڻ�ȡͼƬ�Ŀ��
    	BitmapFactory.Options option = new Options();
    	//inJustDecodeBounds ֻ����ͼƬ�Ŀ���Լ�����
    	//��������������Ϊtrue��ô����BitmapFactory.decodeXXXX���� ֻ���ͼƬ�Ŀ�� �߶� ͼƬ�����Ͷ�����
    	//�������ͼƬ
    	option.inJustDecodeBounds = true;
    	Bitmap bitmap = BitmapFactory.decodeFile(path, option);
//    	if(bitmap==null){
//    		System.out.println("ͼƬ�Ŀ��"+option.outWidth+"�߶�"+option.outHeight);
//    	}
    	int width = option.outWidth;
    	int height = option.outHeight;
    	//�ۼ���inSampleSize
    	//ֻ�е�ͼƬ�Ŀ�Ⱥ͸߶ȴ�����Ļ�Ŀ�Ⱥ͸߶Ȳ�ѹ��ͼƬ
    	if(width>screenWidth||height>screenHeight){
    		int widthIndex = Math.round((float)width/(float)screenWidth);
    		int heightIndex = Math.round((float)height/(float)screenHeight);
    		//����ѹ������ ȡ��߱��������ֵ
    		option.inSampleSize = Math.max(widthIndex, heightIndex);
    	}
    	//�ü���õ�inSampleSize ����ͼƬ
    	option.inJustDecodeBounds = false;//��inJustDecodeBounds����Ϊfalse ��ʼ����ͼƬ
    	bitmap = BitmapFactory.decodeFile(path, option);
    	//��imageview��ʾͼƬ
    	iv_image.setImageBitmap(bitmap);
    }
    
    public void loadpic3(){
    	BitmapFactory.Options option = new Options();
    	option.inSampleSize = 1;
    	Bitmap bitmap = null;
    	int i = 1;
    	for(;;){
    		try {
    			//��������inSampleSize ���ѹ������������ ץסoutofmemory���ѹ������ ��������
    			//ֱ�����Գɹ�����Ϊֹ
    			option.inSampleSize = i;
    			 bitmap = BitmapFactory.decodeFile(path, option);
    			 break;
			} catch (Error e) {
				i*=2;
				System.out.println("i = "+i);
			}
    	}
    	//���ص�imageView
    	iv_image.setImageBitmap(bitmap);
    }
    
}
