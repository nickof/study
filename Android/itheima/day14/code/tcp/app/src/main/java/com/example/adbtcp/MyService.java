package com.example.adbtcp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.adbtcp.pulicData.Data;
import com.example.adbtcp.ut.ut;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MyService extends Service {

    private static final String TAG = "log";
    public  ServerThread serverThread=null;
    public static Activity instance;

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

    public  void handle_Main(String str){
        if(str.contains("find")){
            show_num(str);
        }
    }

    public void show_num(String str){

        String[] strings=str.split(",");
        Log.d(TAG,strings[1]);

        SharedPreferences sharedPref =this.getSharedPreferences("ui",MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("colNum",strings[1]);
        Log.d("log",strings[1]);
        editor.commit();

        Intent intent=new Intent( getApplicationContext(),MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
        startActivity( intent );
        //handler.sendEmptyMessage(0);

    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {

        Log.d("log","service onCr");
            Log.d(TAG, "onStartCommand: screeContext=null");
            Intent intent2=new Intent( getApplicationContext(), ScreenActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity( intent2 );

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

   /*     Log.d("log","service st");

        if ( Data.screeContext==null ){
            Log.d(TAG, "onStartCommand: screeContext=null");
            Intent intent2=new Intent( getApplicationContext(), ScreenActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity( intent2 );
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//            ServerThread2 serverThread2=new ServerThread2();
//            serverThread2.start();
//        Log.d("log","service st2");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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


    public static int send_var_data(Socket s, InputStream stream) {

        byte[] data=null;
        try {
            data = ut.toByteArray( stream );
        } catch (IOException e) {
            e.printStackTrace();
        }

        int total=0;
        int size=data.length;
        int dateleft=size;
        int sent;
        byte[] datasize=new byte[4];
        datasize= ut.int2Bytes(size) ;
        Log.d(TAG, "send_var_data: size="+size);

        try {
            DataOutputStream os= new DataOutputStream( s.getOutputStream());
            os .write( datasize );
            os.write( data );
            os.flush();

        } catch (IOException e) {
            Log.e(TAG, "send_var_data: catchRun..");
            e.printStackTrace();
            return 0;

        }
        return  size;
    }

    public static int send_var_data(OutputStream os,byte[] data){

        int total=0;
        int size=data.length;
        int dateleft=size;
        int sent;
        byte[] datasize=new byte[4];
        datasize= ut.int2Bytes(size) ;

        try {
            //OutputStream os=s.getOutputStream();
            os.write(datasize);
            os.write(data,total,data.length);
            os.flush();
            //total+=size;
            //}
//            os.write( data );
//            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  size;
    }

    class ServerThread2 extends Thread {

        boolean isLoop = true;
        public void setIsLoop(boolean isLoop) {
            this.isLoop = isLoop;
        }

        @Override
        public void run() {
            Log.d(TAG, "running....");
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(9000);
                while (true) {
                    Log.d(TAG, "run: accept");
                    String msg;
                    Socket socket = serverSocket.accept();
                    InputStream fns= socket.getInputStream();
                    BufferedReader br=new BufferedReader(new InputStreamReader(fns,"GBK")  );

                    while (isLoop) {
                        //  
                        FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "/1.jpg");
                        DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                       int len;
                       byte[] b=new byte[1000];

                       int size=fis.available();//+"end".getBytes().length;
                        Log.d(TAG, "run: size=+++"+size);
                       int total=0;
                       out.write( ut.int2Bytes(size) );
                       while ((len=fis.read(b))!=-1){
                           // send_var_data(out,b);
                           out.write(b,0,len);
                           out.flush();
                           total+=len;
                           Log.d(TAG, "run: read.."+total+"/"+size);
                       }
                        while (true){
                           Log.d(TAG, "run: waiting");
                           String str=br.readLine();
                           Log.d(TAG, "run av: "+fns.available()+",str--"+str);
                           if (str.equals("ok"))break;
                       }
                    }
                }
                } catch(Exception e){
                    e.printStackTrace();
                    if (serverSocket != null) {
                        Log.e(TAG, "catch: 关闭");

                        try {
                            serverSocket.close();

                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } finally{
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
