package cn.areful.xposed.hook;

import java.util.List;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public interface HookEntryInterface {
    void hook(XC_LoadPackage.LoadPackageParam params);

    List<HookEntry> getHookEntries();
}
