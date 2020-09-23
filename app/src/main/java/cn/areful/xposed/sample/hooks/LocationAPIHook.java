package cn.areful.xposed.sample.hooks;

import android.location.GpsStatus;
import android.location.LocationManager;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;
import de.robv.android.xposed.XposedBridge;

public class LocationAPIHook {
    private static final String PACKAGE_NAME = HookHelper.PACKAGE_NAME;

    public static List<HookEntry> list() {
        List<HookEntry> list = new ArrayList<>();

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "addGpsStatusListener",
                new Object[]{GpsStatus.Listener.class}
        ));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "getGpsStatus",
                new Object[]{android.location.GpsStatus.class}));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "getLastKnownLocation",
                new Object[]{String.class}));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.net.wifi.WifiManager",
                "getScanResults",
                new Object[]{}));

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getCellLocation",
                new Object[]{}));

        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates")
                    && !Modifier.isAbstract(method.getModifiers())
                    && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new HookEntry(
                        PACKAGE_NAME,
                        "android.location.LocationManager",
                        "requestLocationUpdates",
                        new Object[]{}));
            }
        }

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getAllCellInfo",
                new Object[]{}));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getNeighboringCellInfo",
                new Object[]{}));


        return list;
    }
}
