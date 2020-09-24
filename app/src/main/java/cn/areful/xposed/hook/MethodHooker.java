package cn.areful.xposed.hook;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import cn.areful.xposed.utils.ConfigUtils;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class MethodHooker {
    private static final String TAG = MethodHook.TAG;

    public void hook(final XC_LoadPackage.LoadPackageParam params) {
        String packageName = ConfigUtils.getPackageName();
        Log.d(TAG, "getHookEntries(), packageName: " + packageName);

        if (!TextUtils.isEmpty(packageName)) {
            for (MethodHook entry : getHookEntries(packageName)) {
                if (params.packageName.equals(entry.packageName)) {
                    XposedHelpers.findAndHookMethod(
                            entry.className,
                            params.classLoader,
                            entry.methodName,
                            entry.getArgs());
                }
            }
        }
    }

    public abstract List<MethodHook> getHookEntries(String packageName);
}