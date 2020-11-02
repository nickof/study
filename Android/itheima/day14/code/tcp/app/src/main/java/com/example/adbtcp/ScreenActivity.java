package com.example.adbtcp;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.adbtcp.pulicData.Data;
import com.example.adbtcp.ut.ut;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ScreenActivity extends AppCompatActivity {

    public String TAG="ScreenActivity";
    public Context context;
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
    public ServerThread2 serverThread2;
    public void iiiiiii(){};
    public boolean boolThr=true;
    public Socket socket;
    public boolean isBoolThr=false;
    public Thread thrBoolRead;
    public ScreenActivity.bool_read thrBool_readObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        Log.i(TAG, "onCreate: "+ut.getSerialNumber() );
        Log.i(TAG, "onCreate: ScreenActivity ScreenActivity ScreenActivity");
        Data.setContext( this );
        main();

//        try{
//            Runtime runtime=Runtime.getRuntime();
//            runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//
//        }catch(IOException e){
//            Log.e("Exception when doBack", e.toString());
//        }


    }

    public void init(){
            colDevice=ut.getSerialNumber();
    }

    public void main(){
        createEnvironment();
        startActivityForResult( mMediaProjectionManager.createScreenCaptureIntent(),1);
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
            Data.mResultCode=mResultCode;
            mResultData = data;
            Data.mResultData=mResultData;

            setUpMediaProjection();
            setUpVirtualDisplay();
            Intent intent=new Intent(this,ServiceScreen.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            }else{
                startService(intent);
            }
//            ServerThread2 serverThread2=new ServerThread2();
//            serverThread2.start();


            //mImageReader.setOnImageAvailableListener( new ImageAvailableListener(), null );
            //继续执行截图或者录屏操作
            // do somthing...
      /*      while (true){

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }*/

        }
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: onStop.." );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ....onDestroy..");

        super.onDestroy();
    }

    private class ImageAvailableListener implements ImageReader.OnImageAvailableListener {
        @Override
        public void onImageAvailable(ImageReader reader) {
            Log.d(TAG, "onImageAvailable: run..screen..");
            try (Image image = reader.acquireLatestImage()) {
                if (image != null) {
                    final int width = image.getWidth();
                    final int height = image.getHeight();
                    final Image.Plane[] planes = image.getPlanes();
                    final ByteBuffer buffer = planes[0].getBuffer();
                    final int pixelStride = planes[0].getPixelStride();
                    final int rowStride = planes[0].getRowStride();
                    final int rowPadding = rowStride - pixelStride * width;
                    Log.d(TAG, "onImageAvailable: "+width+","+height+"\r\n"
                    +pixelStride+","+rowStride+","+rowPadding);
                    
                    mBitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
                    mBitmap.copyPixelsFromBuffer(buffer);
                    mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height);
                    image.close();
                    String order;
                    //stopScreenCapture();
                    if (mBitmap != null) {
                        //          checkPermission();
                        //saveToFile();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Log.d(TAG, "bitmap create success ");
                                    Socket s = new Socket("127.0.0.1", 8600);
                                    OutputStream out = s.getOutputStream();
                                    //DataOutputStream dos =new DataOutputStream(out);
                                    byte[] buf = new byte[1024];
                                    int len = 0;
                                    //2.往输出流里面投放数据
                                    out.write((colDevice+"pic").getBytes());
                                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                                    mBitmap.compress( Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream );
                                    //pic为图片
//                                    out.flush();
//                                    out.close();
                                    Log.d(TAG, "run: 发送完成");
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
    }

    public Bitmap getPic(){
        try (Image image = mImageReader.acquireLatestImage()) {
            if (image != null) {
                final int width = image.getWidth();
                final int height = image.getHeight();
                final Image.Plane[] planes = image.getPlanes();
                final ByteBuffer buffer = planes[0].getBuffer();
                final int pixelStride = planes[0].getPixelStride();
                final int rowStride = planes[0].getRowStride();
                final int rowPadding = rowStride - pixelStride * width;
//                Log.d(TAG, "getPic: "+width+","+height+"\r\n"
//                        +pixelStride+","+rowStride+","+rowPadding);
                mBitmap = Bitmap.createBitmap(width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888);
                mBitmap.copyPixelsFromBuffer(buffer);
                mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height);
                image.close();
                String order;
                //stopScreenCapture();
                if (mBitmap != null) {
                    Log.d(TAG, "getPic: recive bmp.ok..");
                    return mBitmap;
                }

            }
        } catch (Exception e) {
            Log.d(TAG, "getPic: catch."+e.toString() );
            e.printStackTrace();
        }
        return null;
    }

    class ServerThread2 extends Thread {

        boolean isLoop = true;
        public void setIsLoop( boolean isLoop ) {
            this.isLoop = isLoop;
        }

        @Override
        public void run() {

            Log.d(TAG, "running....");
            Socket socket=null;
            InputStream fns=null;
            BufferedReader br=null;
            //DataInputStream br=null;
            ServerSocket serverSocket = null;
            DataOutputStream out=null;
            Bitmap bitmap=null;
            byte[]  b=new byte[2];

            try {
                serverSocket = new ServerSocket(9000);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    Log.d(TAG, "run: accept");
//                   if (thrBoolRead!=null){
//                       thrBoolRead.interrupt();
//                   }

                    //监控读的线程
                    if (thrBool_readObj!=null){
                        Log.d(TAG, "run: 尝试关闭线程"+thrBoolRead.getState());
                        thrBool_readObj.stopOn=true;
                         ut.wait_for_thr_close(thrBoolRead);
                        Log.d(TAG, "run: 线程关闭成功"+thrBoolRead.getState());
                    }
                    socket = serverSocket.accept();
                    //Start monitoring thread
                    thrBool_readObj=new bool_read(socket);
                    thrBoolRead= new Thread(thrBool_readObj);
                    thrBoolRead.start();
                    Log.d(TAG, "线程状态 "+  thrBoolRead.getState());

     /*               thrBool_readObj.stopOn=true;
                    Log.d(TAG, "run: 开关中断");
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: 中断成功"+thrBool_readObj.getState());*/

                     fns = socket.getInputStream();
                     br = new BufferedReader(new InputStreamReader(fns, "GBK"));
                     //br=new DataInputStream(fns);
                     out = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int n=0;
                while (isLoop) {
                    //
                    try {
                        bitmap=getPic();
                        if(bitmap!=null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            //ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream()
                            mBitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
                            byteArrayOutputStream.flush();
                            int size=byteArrayOutputStream.size();
                            //Log.d(TAG, "run: 图片size为"+size);
                            out.write(ut.int2Bytes(size));
                            byteArrayOutputStream.writeTo(out);
                            byteArrayOutputStream.close();
                               // Log.d(TAG, "run: waiting");
                            int len=0;
                            long t1=System.currentTimeMillis();

                            while (true){
                                    boolThr=false;
                                    //Log.d(TAG, "run: waiting");
                                    len=fns.read(b);
                                        if(len>0){
                                        boolThr=true;
                                        isBoolThr=true;
                                        break;
                                    }
                                    if (System.currentTimeMillis()-t1>5000){
                                        Log.d(TAG, "run: 接收超时");
                                        socket.close();
                                        break;
                                    }
                            }
                           // Log.d(TAG, "run: waiting ok");
                            /*    if (System.currentTimeMillis()-t1>5000){
                                    Log.d(TAG, "run: Re Accept");
                                        socket.close();
                                        socket = serverSocket.accept();
                                        fns = socket.getInputStream();
                                        //br = new BufferedReader(new InputStreamReader(fns, "GBK"));
                                        br=new DataInputStream(fns);
                                        out = new DataOutputStream(socket.getOutputStream());
                                    Log.d(TAG, "run: break");
                                        break;
                                }*/
                        }else{
                           // Log.d(TAG, "run: 屏幕未变化,继续读取");
                            boolThr=true;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(TAG, "run: catch Io" );
                        if(serverSocket!=null){
                            try {
                                if (socket!=null)
                                    socket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        }
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    /**
     * Determines that the block is longer than the specified time
     */

    public class bool_read extends Thread{
        public boolean stopOn=false;
        public  Socket socket;
        public bool_read(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            while (!stopOn){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Thread.currentThread().isInterrupted()){
                    Log.d(TAG, "run: 线程停止啦");
                    return;
                }

                Log.d(TAG, "run: bool_thr_run"+Thread.currentThread());
                if (!boolThr){
                    Log.d(TAG, "run: bool_thr_run_find_false");
                    try {
                        isBoolThr=false;
                        Thread.sleep(5000);
                        if (!isBoolThr){
                            Log.d(TAG, "run: bool_thr_false_outtime");
                            if (socket!=null){
                                Log.d(TAG, "run: socket close");
                                socket.close();
                                isBoolThr=true;
                                boolThr=true;
                                //break;
                            }
                        }else{
                            Log.d(TAG, "run: bool_thr_false_changgeTo_true");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else
                {
                    Log.d(TAG, "run: bool_thr_true");
                }

            }

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
        Data.mMediaProjection=mMediaProjection;
    }

    private void setUpVirtualDisplay() {
        mVirtualDisplay = mMediaProjection.createVirtualDisplay("ScreenCapture",
                mWindowWidth, mWindowHeight, mScreenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mImageReader.getSurface(), null, null);
        Data.mVirtualDisplay=mVirtualDisplay;

    }

    private void createEnvironment() {

        colDevice= ut.getSerialNumber();
        Data.colDevice=colDevice;
        mWindowManager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Data.mWindowManager=mWindowManager;
        mWindowWidth = mWindowManager.getDefaultDisplay().getWidth();
        Data.mWindowWidth=mWindowWidth;
        mWindowHeight = mWindowManager.getDefaultDisplay().getHeight();
        Data.mWindowHeight=mWindowHeight;
        //mWindowWidth=360;
        //mWindowHeight=640;
        Log.d(TAG, "createEnvironment: "+mWindowWidth+".,"+mWindowHeight);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.densityDpi;
        Data.mScreenDensity=mScreenDensity;
        // mImageReader = ImageReader.newInstance(mWindowWidth, mWindowHeight, ImageFormat.YV12, 1);
        mImageReader = ImageReader.newInstance(mWindowWidth, mWindowHeight, 0x1, 5);
        Data.mImageReader=mImageReader;
        mMediaProjectionManager = (MediaProjectionManager)getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Data.mMediaProjectionManager=mMediaProjectionManager;

    }

    @Override
    protected void onStart() {
        super.onStart();
        setVisible(true);
    }

    private boolean startScreenCapture() {
        Log.e("0525", "startScreenCapture");
        if (this == null) {
            return false;
        }
        if (mMediaProjection != null) {
            Log.e("0525", "startScreenCapture 1");
            setUpVirtualDisplay();
            return true;
        } else if (mResultCode != 0 && mResultData != null) {
            Log.e("0525", "startScreenCapture 2");
            setUpMediaProjection();
            setUpVirtualDisplay();
            return true;
        } else {
            Log.e("0525", "startScreenCapture 3");
            Log.d(TAG, "Requesting confirmation");
            // This initiates a prompt dialog for the user to confirm screen projection.
            startActivityForResult(
                    mMediaProjectionManager.createScreenCaptureIntent(),
                    1);
            return false;
        }
    }

    private void stopScreenCapture() {
        if (mVirtualDisplay != null) {
            mVirtualDisplay.release();
            mVirtualDisplay = null;
        }
    }

}
