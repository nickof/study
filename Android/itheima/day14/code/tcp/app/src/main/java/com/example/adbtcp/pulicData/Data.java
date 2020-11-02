package com.example.adbtcp.pulicData;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.util.Log;
import android.view.WindowManager;

public class Data {

    public static Context screeContext=null;
    public static String colDevice;
    public static WindowManager mWindowManager;
    public static int mWindowWidth;
    public static int mWindowHeight;
    public static int mScreenDensity;
    public static ImageReader mImageReader;
    public static MediaProjectionManager mMediaProjectionManager;
    public static int mResultCode;
    public static Intent mResultData;
    public static MediaProjection mMediaProjection;
    public static VirtualDisplay mVirtualDisplay;

    static  String TAG="Data";
    public static void setContext(Context screeContext) {
        Data.screeContext = screeContext;
        Log.d(TAG, "setContext: ..."+Data.screeContext);
    }
}
