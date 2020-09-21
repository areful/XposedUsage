package cn.areful.xposed.sample.hooks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;
import cn.areful.xposed.sample.HookHelper;
import de.robv.android.xposed.XC_MethodHook;

public class StorageAPIHook {
    private static final String PACKAGE_NAME = HookHelper.PACKAGE_NAME;
    
    public static List<HookEntry> list() {
        List<HookEntry> list = new ArrayList<>();

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageState",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d("areful--", Log.getStackTraceString(new Throwable()));
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
                        Log.d("areful--", Log.getStackTraceString(new Throwable()));
                    }
                }));
        return list;
    }
}
