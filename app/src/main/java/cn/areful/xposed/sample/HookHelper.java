package cn.areful.xposed.sample;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.BaseHookService;
import cn.areful.xposed.hook.HookEntry;
import cn.areful.xposed.sample.hooks.LocationAPIHook;
import cn.areful.xposed.sample.hooks.StorageAPIHook;
import cn.areful.xposed.sample.hooks.TelephoneManagerAPIHook;

public class HookHelper extends BaseHookService {
    public static final String PACKAGE_NAME = "cn.areful";

    @Override
    public List<HookEntry> getHookEntries() {
        List<HookEntry> list = new ArrayList<>();

        list.addAll(LocationAPIHook.list());

        list.addAll(StorageAPIHook.list());

        list.addAll(TelephoneManagerAPIHook.list());

        return list;
    }
}
