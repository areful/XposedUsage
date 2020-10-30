package com.chebada.hooklib.hookapis;

import com.chebada.hooklib.hook.MethodHook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class HookTelephoneManagerAPI {

    public static List<MethodHook> list(String packageName) {
        List<MethodHook> list = new ArrayList<>();

        list.add(new MethodHook(
                packageName,
                "android.telephony.TelephonyManager",
                "getDeviceId",
                new Object[]{}));
        return list;
    }
}
