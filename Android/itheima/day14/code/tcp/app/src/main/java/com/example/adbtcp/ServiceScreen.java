package com.example.adbtcp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.IBinder;
import android.util.Log;

import com.example.adbtcp.pulicData.Data;
import com.example.adbtcp.ut.ut;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ServiceScreen extends Service {

    private String TAG="ServiceScreen";
    private boolean boolThr=true;
    public boolean isBoolThr=false;
    public Bitmap mBitmap;
    public ServiceScreen.bool_read thrBool_readObj;
    public Thread thrBoolRead;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate: ..run");
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { // 注意notification也要适配Android 8 哦
            startForeground( 2 , new Notification());// 通知栏标识符 前台进程对象唯一ID
        }

        ServerThread2 serverThread2=new ServerThread2();
        serverThread2.start();

    }



    public class bool_read extends Thread{

        public boolean stopOn=false;
        public Socket socket;
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

                Log.d(TAG, "run: bool_thr_run"+Thread.currentThread() );
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
                    thrBool_readObj=new ServiceScreen.bool_read(socket);
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
    

    public Bitmap getPic(){
        try (Image image = Data.mImageReader.acquireLatestImage()) {
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

}
