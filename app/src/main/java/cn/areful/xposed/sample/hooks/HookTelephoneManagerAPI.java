package cn.areful.xposed.sample.hooks;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;

public class HookTelephoneManagerAPI {
    private static final String PACKAGE_NAME = MethodHookHelper.PACKAGE_NAME;

    public static List<MethodHook> list() {
        List<MethodHook> list = new ArrayList<>();

        list.add(new MethodHook(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getDeviceId",
                new Object[]{}));
        return list;
    }
}