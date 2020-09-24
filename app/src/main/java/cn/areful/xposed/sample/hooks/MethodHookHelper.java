package cn.areful.xposed.sample.hooks;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;
import cn.areful.xposed.hook.MethodHooker;

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
