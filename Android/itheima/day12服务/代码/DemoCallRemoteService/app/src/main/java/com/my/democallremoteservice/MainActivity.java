package com.my.democallremoteservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.my.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    public MyConnection connection ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startRemoteService(View view) {

        System.out.println("startRemoteService");
        connection = new MyConnection();
        Intent intent=new Intent();
        intent.setAction("com.my.aidl");
        intent.setPackage("com.my.aidl");
        bindService( intent, connection,BIND_AUTO_CREATE );

    }

    public class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface mStub=IMyAidlInterface.Stub.asInterface(service);
            System.out.println("MyConnection connected"+mStub);
            try {
                mStub.callRemoteMethod();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("Service disconnected");;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

}
