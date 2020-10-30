package com.chebada.hooklib.hookapis;

import android.location.GpsStatus;
import android.location.LocationManager;

import com.chebada.hooklib.hook.MethodHook;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class HookLocationAPI {

    public static List<MethodHook> list(String packageName) {
        List<MethodHook> list = new ArrayList<>();

        list.add(new MethodHook(
                packageName,
                "android.location.LocationManager",
                "addGpsStatusListener",
                new Object[]{GpsStatus.Listener.class}
        ));
        list.add(new MethodHook(
                packageName,
                "android.location.LocationManager",
                "getGpsStatus",
                new Object[]{GpsStatus.class}));
        list.add(new MethodHook(
                packageName,
                "android.location.LocationManager",
                "getLastKnownLocation",
                new Object[]{String.class}));
        list.add(new MethodHook(
                packageName,
                "android.net.wifi.WifiManager",
                "getScanResults",
                new Object[]{}));

        list.add(new MethodHook(
                packageName,
                "android.telephony.TelephonyManager",
                "getCellLocation",
                new Object[]{}));

        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates")
                    && !Modifier.isAbstract(method.getModifiers())
                    && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new MethodHook(
                        packageName,
                        "android.location.LocationManager",
                        "requestLocationUpdates",
                        new Object[]{}));
            }
        }

        list.add(new MethodHook(
                packageName,
                "android.telephony.TelephonyManager",
                "getAllCellInfo",
                new Object[]{}));
        list.add(new MethodHook(
                packageName,
                "android.telephony.TelephonyManager",
                "getNeighboringCellInfo",
                new Object[]{}));


        return list;
    }
}
