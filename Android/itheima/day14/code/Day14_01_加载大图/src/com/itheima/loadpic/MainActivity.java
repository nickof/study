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
      //①获取屏幕的宽高
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//        System.out.println("width==="+width+"height==="+height);
        Point outSize = new Point();
        //运行完这个方法, 屏幕的宽高信息就保存到了point对象中
		getWindowManager().getDefaultDisplay().getSize(outSize);
		 System.out.println("width==="+outSize.x+"height==="+outSize.y);
    }


    public void loadpic(View v){
    	//创建bitmap对象
//    	Bitmap bitmap = BitmapFactory.decodeFile(path);
//    	//把bitmap显示到imageview上
//    	iv_image.setImageBitmap(bitmap);
    	loadpic3();
    }
    
    public void loadpic1(){
    	//options 选项的意思  通过创建Options对象 告诉BitmapFactory在解码图片的时候
    	//采用一些配置好的设置信息 比如 压缩图片的压缩比例  或者 是否加载完整图片......
    	BitmapFactory.Options options = new Options();
    	//inSampleSize sample 采样的意思  
    	//inSampleSize 通过配置这个值可以压缩图片 如果inSampleSize=2 那么加载到内存中的图片
    	//宽度是原图的1/2 高度也是原图的1/2 总的大小是原图的1/4; 需要注意 如果inSampleSize<1 会按1处理
    	//解码器默认会按照2的幂指数来处理图片的压缩 所以可以传入2的幂指数来作为inSampleSize的值
    	options.inSampleSize = 2;
    	//BitmapFactory.de
    	Bitmap bitmap = BitmapFactory.decodeFile(path, options);
    	iv_image.setImageBitmap(bitmap);
    }
    
    public void loadpic2(){
    	
    	//比较图片和屏幕的分辨率 如果图片分辨率比屏幕分辨率高 
    	//可以使用 图片宽度/屏幕的宽度
    	//图片的高度/屏幕的高度
    	//通过这两个比值来确定 inSampleSize
    	
    	
    	//②获取图片的宽高
    	BitmapFactory.Options option = new Options();
    	//inJustDecodeBounds 只解析图片的宽高以及类型
    	//如果这个参数设置为true那么调用BitmapFactory.decodeXXXX方法 只会把图片的宽度 高度 图片的类型读出来
    	//不会解码图片
    	option.inJustDecodeBounds = true;
    	Bitmap bitmap = BitmapFactory.decodeFile(path, option);
//    	if(bitmap==null){
//    		System.out.println("图片的宽度"+option.outWidth+"高度"+option.outHeight);
//    	}
    	int width = option.outWidth;
    	int height = option.outHeight;
    	//③计算inSampleSize
    	//只有当图片的宽度和高度大于屏幕的宽度和高度才压缩图片
    	if(width>screenWidth||height>screenHeight){
    		int widthIndex = Math.round((float)width/(float)screenWidth);
    		int heightIndex = Math.round((float)height/(float)screenHeight);
    		//计算压缩比例 取宽高比例的最大值
    		option.inSampleSize = Math.max(widthIndex, heightIndex);
    	}
    	//用计算好的inSampleSize 加载图片
    	option.inJustDecodeBounds = false;//把inJustDecodeBounds设置为false 开始加载图片
    	bitmap = BitmapFactory.decodeFile(path, option);
    	//用imageview显示图片
    	iv_image.setImageBitmap(bitmap);
    }
    
    public void loadpic3(){
    	BitmapFactory.Options option = new Options();
    	option.inSampleSize = 1;
    	Bitmap bitmap = null;
    	int i = 1;
    	for(;;){
    		try {
    			//不断试验inSampleSize 如果压缩比例不够高 抓住outofmemory提高压缩比例 接着试验
    			//直到可以成功加载为止
    			option.inSampleSize = i;
    			 bitmap = BitmapFactory.decodeFile(path, option);
    			 break;
			} catch (Error e) {
				i*=2;
				System.out.println("i = "+i);
			}
    	}
    	//加载到imageView
    	iv_image.setImageBitmap(bitmap);
    }
    
}
