package cn.areful.xposed.hook;

import android.app.AndroidAppHelper;
import android.content.Intent;
import android.util.Log;

public class MethodBroadcastSender {
    private static final String TAG = MethodHook.TAG;
    private static final String ACTION_NAME = "com.xbeats.myapplication.event";
    private static final String EXTRA_METHOD_NAME = "apiName";
    private static final String EXTRA_LOG = "log";

    public static void send(String methodName) {
        Log.d(TAG, "MethodBroadcastSender.send()");

        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(EXTRA_METHOD_NAME, methodName);
        intent.putExtra(EXTRA_LOG, Log.getStackTraceString(new Throwable()));
        AndroidAppHelper.currentApplication().getApplicationContext().sendBroadcast(intent);
    }
}