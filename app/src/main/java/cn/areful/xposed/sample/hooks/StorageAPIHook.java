package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;

public class StorageAPIHook {
    private static final String PACKAGE_NAME = HookHelper.PACKAGE_NAME;

    public static List<HookEntry> list() {
        List<HookEntry> list = new ArrayList<>();

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageState",
                new Object[]{}));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageDirectory",
                new Object[]{}));
        return list;
    }
}
