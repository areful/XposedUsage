package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;

public class HookStorageAPI {
    private static final String PACKAGE_NAME = MethodHookHelper.PACKAGE_NAME;

    public static List<MethodHook> list() {
        List<MethodHook> list = new ArrayList<>();

        list.add(new MethodHook(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageState",
                new Object[]{}));
        list.add(new MethodHook(
                PACKAGE_NAME,
                "android.os.Environment",
                "getExternalStorageDirectory",
                new Object[]{}));
        return list;
    }
}
