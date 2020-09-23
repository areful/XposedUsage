package cn.areful.xposed.sample.hooks;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.BaseHookService;
import cn.areful.xposed.hook.HookEntry;

public class HookHelper extends BaseHookService {
    public static final String PACKAGE_NAME = "cn.areful.myapplication";

    public static final String TAG = "areful--";
    public static final String ACTION_NAME = "com.xbeats.myapplication.event";
    public static final String EXTRA_METHOD_NAME = "apiName";
    public static final String EXTRA_LOG = "log";

    @Override
    public List<HookEntry> getHookEntries() {
        List<HookEntry> list = new ArrayList<>();

        list.addAll(LocationAPIHook.list());

        list.addAll(StorageAPIHook.list());

        list.addAll(TelephoneManagerAPIHook.list());

        return list;
    }

    public static void send(String methodName) {
        Log.d(HookHelper.TAG, "sendBroadcast()");

        Context context = AndroidAppHelper.currentApplication().getApplicationContext();
        Intent intent = new Intent(ACTION_NAME);
        intent.putExtra(EXTRA_METHOD_NAME, methodName);
        intent.putExtra(EXTRA_LOG, Log.getStackTraceString(new Throwable()));
        context.sendBroadcast(intent);
    }
}
