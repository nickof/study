package com.example.adbtcp.ut;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * 此类完成截图实时屏幕显示功能
 */
public class ScreenShow  extends Activity {

    public  Context context;
    public  String TAG="log";
    private String mImagePath;
    private WindowManager mWindowManager;
    private int mWindowWidth;
    private int mWindowHeight;
    private int mScreenDensity;
    private ImageReader mImageReader;
    private MediaProjectionManager mMediaProjectionManager;
    private String mVideoPath;
    private int mResultCode;
    private Intent mResultData;
    MediaProjection mMediaProjection;
    private VirtualDisplay mVirtualDisplay;
    public Bitmap mBitmap;
    public String colDevice;

    public void iiiiiii(){};

    public ScreenShow(Context context){
        this.context=context;
    }

    public void main(){
        createEnvironment();
        ((Activity) context).startActivityForResult( mMediaProjectionManager.createScreenCaptureIntent(),1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: REQUEST_MEDIA_PROJECTION="+requestCode);
        if (requestCode == 1) {

            if (resultCode != Activity.RESULT_OK) {
                Log.d(TAG, "User cancelled");
                Toast.makeText(context, "User cancelled", Toast.LENGTH_SHORT).show();
                return;
            }
            if (this == null) {
                return;
            }

            Log.d(TAG, "Starting screen capture");
            mResultCode = resultCode;
            mResultData = data;
            setUpMediaProjection();
            setUpVirtualDisplay();

           // mImageReader.setOnImageAvailableListener( new ImageAvailableListenerB(), null );
            //继续执行截图或者录屏操作
            // do somthing...
        }
    }


    public void listenRun( ImageReader reader ){

        Log.d(TAG, "onImageAvailable: run....");
        try (Image image = reader.acquireLatestImage()) {
            if (image != null) {

                int width = image.getWidth();
                int height = image.getHeight();
                final Image.Plane[] planes = image.getPlanes();
                final ByteBuffer buffer = planes[0].getBuffer();
                int pixelStride = planes[0].getPixelStride();
                int rowStride = planes[0].getRowStride();
                int rowPadding = rowStride - pixelStride * width;

                mBitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_4444 );
                mBitmap.copyPixelsFromBuffer(buffer);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height);
                image.close();
                //stopScreenCapture();
                if (mBitmap != null) {
                    //          checkPermission();
                    //saveToFile();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d(TAG, "bitmap create success ");
                                Socket s=new Socket("127.0.0.1",8600);
                                OutputStream out = s.getOutputStream();
                                //DataOutputStream dos =new DataOutputStream(out);
                                byte[] buf = new byte[1024];
                                int len = 0;
                                //2.往输出流里面投放数据
                                Log.d(TAG, "run: 开始发送");
                                out.write((colDevice+"pic").getBytes());
                                Log.d(TAG, "run: device="+colDevice );
                                mBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
                                //pic为图片
//                                    out.flush();
                                out.close();
                                Log.d(TAG, "run: 发送完成"+colDevice);
                                s.close();
                                //    s.shutdownOutput();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setUpMediaProjection() {
        mMediaProjection = mMediaProjectionManager.getMediaProjection(mResultCode, mResultData);
    }

    private void setUpVirtualDisplay() {

        mVirtualDisplay = mMediaProjection.createVirtualDisplay("ScreenCapture",
                mWindowWidth, mWindowHeight, mScreenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mImageReader.getSurface(), null, null);

    }

    private void createEnvironment() {

        colDevice=ut.getSerialNumber();
       //colDevice="111";
        mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        mWindowWidth = mWindowManager.getDefaultDisplay().getWidth();
        mWindowHeight = mWindowManager.getDefaultDisplay().getHeight();
//        mWindowWidth=360;
//        mWindowHeight=640;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.densityDpi;
        // mImageReader = ImageReader.newInstance(mWindowWidth, mWindowHeight, ImageFormat.YV12, 1);
        mImageReader = ImageReader.newInstance(mWindowWidth, mWindowHeight, 0x1, 2);
        mMediaProjectionManager = (MediaProjectionManager)
                context.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

}
