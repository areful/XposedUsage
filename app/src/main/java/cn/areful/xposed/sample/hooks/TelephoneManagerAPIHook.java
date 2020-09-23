package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;

public class TelephoneManagerAPIHook {
    private static final String PACKAGE_NAME = HookHelper.PACKAGE_NAME;

    public static List<HookEntry> list() {
        List<HookEntry> list = new ArrayList<>();

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getDeviceId",
                new Object[]{}));
        return list;
    }
}
