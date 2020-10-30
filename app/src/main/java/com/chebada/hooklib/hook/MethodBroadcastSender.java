package com.chebada.hooklib.hook;

import android.app.AndroidAppHelper;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class MethodBroadcastSender {
    public static final String TAG = "CbdMethodHook";
    public static final String ACTION_NAME = "com.xbeats.myapplication.event";
    public static final String EXTRA_METHOD_NAME = "apiName";
    public static final String EXTRA_LOG = "log";

    public static void send(String methodName) {
        Log.d(TAG, "MethodBroadcastSender.send()");

        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(EXTRA_METHOD_NAME, methodName);
        intent.putExtra(EXTRA_LOG, Log.getStackTraceString(new Throwable()));
        AndroidAppHelper.currentApplication().getApplicationContext().sendBroadcast(intent);
    }
}