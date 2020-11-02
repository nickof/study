package com.example.adbtcp.ut;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.stream.Stream;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class ut {

    //String apkRoot="chmod 777 "+getPackageCodePath();
    //ut.RootCommand(apkRoot);
    public static String TAG="log";

    //转换
    public static byte[] int2Bytes(int num) {

            byte[] b = new byte[4];
            b[0] = (byte)(num & 0xff);
            b[1] = (byte)((num >> 8) & 0xff);
            b[2] = (byte)((num >> 16) & 0xff);
            b[3] = (byte)((num >> 24) & 0xff);

            return  b;
    }

    /**
     * Wait for the thread state to TERMINATED
     * @param thr
     */
    public static void wait_for_thr_close(Thread thr){
        while (!thr.getState().equals(Thread.State.TERMINATED)){
            try {
                Thread.sleep(1000);
                Log.d(TAG, "run: 关闭等待中"+thr.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static int isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = "com.example.adbtcp";

        if (context==null)
            return  -1;

        String bingMapMainActivityClassName = "com.example.adbtcp.ScreenActivity";
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            ComponentName topConponent = tasksInfo.get(0).topActivity;
            Log.d(TAG,
                    "topConponent.getPackageName()..."
                            + topConponent.getPackageName());
            if (packageName.equals(topConponent.getPackageName())) {
                // 当前的APP在前台运行
                if (topConponent.getClassName().equals(
                        bingMapMainActivityClassName)) {
                    // 当前正在运行的是不是期望的Activity
                    Log.d(TAG, "MainFragmentActivity在运行");
                    return 2;
                }
                Log.d(TAG, "前台运行");
                return 1;
            } else {
                // 当前的APP在后台运行
                Log.d(TAG, "后台运行");
                return 0;
            }
        }
        return 0;
    }

    public static byte[] toByteArray(InputStream input) throws IOException {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*4];

        int n = 0;
        while (-1 != (n = input.read(buffer)) ) {
            output.write(buffer, 0, n);
        }

        byte [] b=output.toByteArray();
        output.close();
        return b;

    }

    public static int bytes2Int(byte[] byteNum) {
        int num = 0;
        for (int ix = 0; ix < 4; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static byte int2OneByte(int num) {
        return (byte) (num & 0x000000ff);
    }

    public static int oneByte2Int(byte byteNum) {
        return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
    }

    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }

    public static boolean checkSelfPermission(Context context, String permission) {

        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }

    public static boolean RootCommand(String command){
        Process process = null;
        DataOutputStream os = null;
        try{
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e){
            return false;
        } finally{
            if (os != null){
                try {
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(process!=null) {
                process.destroy();
            }
        }
        return true;
    }



    //版权声明：本文为CSDN博主「呱呱侠」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/u013986975/article/details/71709866

    /**
     *
     * @param context
     * @return
     */
    public static String getIP(Context context){

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }

    private static String getGsfAndroidId(Context context)
    {
        Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        String ID_KEY = "android_id";
        String params[] = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (!c.moveToFirst() || c.getColumnCount() < 2)
            return null;
        try
        {
            return Long.toHexString(Long.parseLong(c.getString(1)));
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }



    public static String getSerialNumber(){

        String serial = null;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
            System.out.println(serial);

        }

        catch (Exception ignored) {

        }
        return serial;
    }

    public static Bitmap getBitmap(String path) {
        Bitmap bt = BitmapFactory.decodeFile(path);
        return bt;
    }

}


