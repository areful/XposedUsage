package com.chebada.hooklib.hookapis;

import android.text.TextUtils;

import com.chebada.hooklib.hook.MethodHook;
import com.chebada.hooklib.hook.MethodHooker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class MethodHookHelper extends MethodHooker {

    @Override
    public List<MethodHook> getHookEntries(String packageName) {
        List<MethodHook> list = new ArrayList<>();
        if (!TextUtils.isEmpty(packageName)) {
            list.addAll(HookLocationAPI.list(packageName));
            list.addAll(HookStorageAPI.list(packageName));
            list.addAll(HookTelephoneManagerAPI.list(packageName));
        }
        return list;
    }
}
