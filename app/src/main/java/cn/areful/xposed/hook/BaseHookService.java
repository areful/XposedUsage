package cn.areful.xposed.hook;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class BaseHookService implements HookEntryInterface {

    @Override
    public void hook(final XC_LoadPackage.LoadPackageParam params) {
        for (HookEntry entry : getHookEntries()) {
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