package cn.areful.xposed.sample.hooks;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.BaseHooker;
import cn.areful.xposed.hook.MethodHook;

public class HookHelper extends BaseHooker {
    public static final String PACKAGE_NAME = "cn.areful.myapplication";

    public static final String TAG = "areful--";
    public static final String ACTION_NAME = "com.xbeats.myapplication.event";
    public static final String EXTRA_METHOD_NAME = "apiName";
    public static final String EXTRA_LOG = "log";

    @Override
    public List<MethodHook> getHookEntries() {
        List<MethodHook> list = new ArrayList<>();
        list.addAll(LocationAPIHook.list());
        list.addAll(StorageAPIHook.list());
        list.addAll(TelephoneManagerAPIHook.list());
        return list;
    }

    public static void sendMethodInterceptedBroadcast(String methodName) {
        Log.d(HookHelper.TAG, "sendMethodInterceptedBroadcast()");

        Context context = AndroidAppHelper.currentApplication().getApplicationContext();
        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(EXTRA_METHOD_NAME, methodName);
        intent.putExtra(EXTRA_LOG, Log.getStackTraceString(new Throwable()));
        context.sendBroadcast(intent);
    }
}
