package cn.areful.xposed.sample.hooks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;
import de.robv.android.xposed.XC_MethodHook;

public class StorageAPIHook {

    public static List<HookEntry> list() {
        String PACKAGE_NAME = HookHelper.PACKAGE_NAME;

        List<HookEntry> list = new ArrayList<>();
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageState",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageDirectory",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        return list;
    }
}
