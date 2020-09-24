package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;

public class HookStorageAPI {

    public static List<MethodHook> list(String packageName) {
        List<MethodHook> list = new ArrayList<>();

        list.add(new MethodHook(
                packageName,
                "android.os.Environment",
                "getExternalStorageState",
                new Object[]{}));
        list.add(new MethodHook(
                packageName,
                "android.os.Environment",
                "getExternalStorageDirectory",
                new Object[]{}));
        return list;
    }
}
