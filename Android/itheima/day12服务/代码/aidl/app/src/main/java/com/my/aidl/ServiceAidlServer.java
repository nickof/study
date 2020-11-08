package com.my.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceAidlServer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public Handler handler ;

    public class MyBinder extends IMyAidlInterface.Stub {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void callRemoteMethod() throws RemoteException {
            remoteMethod();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run(){
                Toast.makeText(getApplicationContext(), "Service is created!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void remoteMethod(){
        System.out.println( "remoteMethod is called" );
        //Toast.makeText(this,"remoteMethod is called",Toast.LENGTH_SHORT);
        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run(){
                Toast.makeText(getApplicationContext(), "remoteMethod is called!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
