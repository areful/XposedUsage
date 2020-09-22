package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.BaseHookService;
import cn.areful.xposed.hook.HookEntry;

public class HookHelper extends BaseHookService {
    public static final String TAG = "areful--";
    public static String PACKAGE_NAME = "com.house365.newhouse";

    @Override
    public List<HookEntry> getHookEntries() {
        List<HookEntry> list = new ArrayList<>();

        list.addAll(LocationAPIHook.list());

        list.addAll(StorageAPIHook.list());

        list.addAll(TelephoneManagerAPIHook.list());

        return list;
    }
}
