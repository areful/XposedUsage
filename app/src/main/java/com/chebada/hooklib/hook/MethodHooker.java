package com.chebada.hooklib.hook;

import android.text.TextUtils;
import android.util.Log;

import com.chebada.hooklib.utils.ConfigUtils;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by gj21798 on 2020/09/24.
 */
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