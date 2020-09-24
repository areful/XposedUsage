package cn.areful.xposed.sample.hooks;

import android.location.GpsStatus;
import android.location.LocationManager;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.MethodHook;
import de.robv.android.xposed.XposedBridge;

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
                new Object[]{android.location.GpsStatus.class}));
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
