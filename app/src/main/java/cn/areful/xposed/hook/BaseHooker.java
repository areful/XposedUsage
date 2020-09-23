package cn.areful.xposed.hook;

import java.util.List;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class BaseHooker {

    public void hook(final XC_LoadPackage.LoadPackageParam params) {
        for (MethodHook entry : getHookEntries()) {
            if (params.packageName.equals(entry.packageName)) {
                XposedHelpers.findAndHookMethod(
                        entry.className,
                        params.classLoader,
                        entry.methodName,
                        entry.getArgs());
            }
        }
    }

    public abstract List<MethodHook> getHookEntries();
}