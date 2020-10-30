package com.chebada.hooklib.hookapis;

import com.chebada.hooklib.hook.MethodHook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj21798 on 2020/09/24.
 */
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
