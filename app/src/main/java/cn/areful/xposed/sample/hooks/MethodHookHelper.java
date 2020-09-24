package cn.areful.xposed.sample.hooks;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;
import cn.areful.xposed.hook.MethodHooker;

public class MethodHookHelper extends MethodHooker {
    public static final String TAG = "areful--";
    public static final String ACTION_NAME = "com.xbeats.myapplication.event";
    public static final String EXTRA_METHOD_NAME = "apiName";
    public static final String EXTRA_LOG = "log";

    @Override
    public List<MethodHook> getHookEntries(String packageName) {
        List<MethodHook> list = new ArrayList<>();
        if (!TextUtils.isEmpty(packageName)) {
            list.addAll(HookLocationAPI.list(packageName));
            list.addAll(HookStorageAPI.list(packageName));
            list.addAll(HookTelephoneManagerAPI.list(packageName));
        }
        return list;
    }

    public static void sendMethodInterceptedBroadcast(String methodName) {
        Log.d(MethodHookHelper.TAG, "sendMethodInterceptedBroadcast()");

        Context context = AndroidAppHelper.currentApplication().getApplicationContext();
        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(EXTRA_METHOD_NAME, methodName);
        intent.putExtra(EXTRA_LOG, Log.getStackTraceString(new Throwable()));
        context.sendBroadcast(intent);
    }
}
