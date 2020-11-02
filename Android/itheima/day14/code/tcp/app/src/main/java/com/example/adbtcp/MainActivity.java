package com.example.adbtcp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adbtcp.ut.ScreenShow;
import com.example.adbtcp.ut.ut;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class MainActivity extends Activity {

    private static final String TAG = "log";
    ServerThread serverThread;
    public static Activity instance;
    private static final int REQUEST_MEDIA_PROJECTION = 1;
    public  MediaProjectionManager mMediaProjectionManager;
    private WindowManager mWindowManager;
    private String mImageName;
    private String mImagePath;
    private int mScreenDensity;
    private int mWindowWidth;
    private int mWindowHeight;
    private ImageReader mImageReader;
    private String mVideoPath;
    private VirtualDisplay mVirtualDisplay;
    private Bitmap mBitmap;
    private ImageView mImageView;
    private final int REQUEST_CODE_SAVE_IMAGE_FILE = 110;
    public ScreenShow screenShow;

    MediaProjection mMediaProjection;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            msg.getData().getString("MSG", "Toast");
            String str=msg.getData().getString("MSG", "Toast");
            Log.d(TAG, str);
            handle_Main(str);
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    };

    private int mResultCode;
    private Intent mResultData;
    private TextView tv_ip;
    private  String ip;

    public  void handle_Main(String str){
        if(str.contains("find")){
            show_num(str);
        }
    }

    public void show_num(String str){

        String[] strings=str.split(",");
        Log.d(TAG,strings[1]);
        instance.finish();

        SharedPreferences sharedPref =this.getSharedPreferences("ui",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("colNum",strings[1]);
        editor.commit();
//      Intent intent = new Intent(MainActivity.this,MainActivity.class );
//      startActivity( intent );
        //handler.sendEmptyMessage(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.d("log","activity_st");
//        TextView textView =findViewById(R.id.colNum);
//        SharedPreferences sharedPref = this.getSharedPreferences("ui",0);
//        String colNum = sharedPref.getString("colNum", "null");
//        textView.setText(colNum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);
        //instance=this;
        //SharedPreferences sharedPreferences
        TextView textView =findViewById(R.id.colNum);
        SharedPreferences sharedPref = this.getSharedPreferences("ui",MODE_MULTI_PROCESS);
        String colNum = sharedPref.getString("colNum", "null");
        Log.d("log","activy_colNum="+colNum);
        //原文链接：https://blog.csdn.net/u010057266/article/details/53470239
        textView.setText(colNum);
        tv_ip = findViewById(R.id.tv_ip);
        mImageView= findViewById(R.id.img);
        ip = ut.getIP(this);
        Log.d(TAG, "onCreate: ip="+ ip);
        tv_ip.setText(ip);

//        Intent intent2=new Intent( getApplicationContext(), ScreenActivity.class);
//        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
//        startActivity( intent2 );

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //serverThread.setIsLoop(false);

    }

    class ServerThread extends Thread {

        boolean isLoop = true;
        public void setIsLoop(boolean isLoop) {
            this.isLoop = isLoop;
        }
        @Override
        public void run() {
            Log.d(TAG, "running");
            ServerSocket serverSocket = null;
            try {

                serverSocket = new ServerSocket(9000);
                while (isLoop) {

                    Socket socket = serverSocket.accept();
                    Log.d(TAG, "accept");
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    String msg;
//                    BufferedReader inputStream = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()) );
//                    // 得到服务器信息
//                   String msg = inputStream.readLine();

                    BufferedReader br=new BufferedReader( new InputStreamReader(socket.getInputStream(),"GBK") );
                    msg=br.readLine();
                    Log.d(TAG,msg);
//                  msg  = inputStream.readUTF();
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("MSG", msg);
                    message.setData(bundle);
                    handler.sendMessage(message);
                    socket.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Log.d(TAG, "destory");
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
