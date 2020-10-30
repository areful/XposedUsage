package com.chebada.hooklib.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class HookedApiReceiver extends BroadcastReceiver {
    public static final String TAG = "CbdMethodHook";
    public static final String ACTION_NAME = "com.xbeats.myapplication.event";
    public static final String EXTRA_METHOD_NAME = "apiName";
    public static final String EXTRA_LOG = "log";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, ACTION_NAME)) {
            String apiName = intent.getStringExtra(EXTRA_METHOD_NAME);
            String log = intent.getStringExtra(EXTRA_LOG);

            Log.d(TAG, String.format("action equals ACTION_NAME, apiName: %s, log: %s", apiName, log));
        }
    }
}