package cn.areful.xposed.sample.hooks;

import android.location.GpsStatus;
import android.location.LocationManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.hook.HookEntry;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class LocationAPIHook {

    public static List<HookEntry> list() {
        String PACKAGE_NAME = HookHelper.PACKAGE_NAME;

        List<HookEntry> list = new ArrayList<>();
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "addGpsStatusListener",
                new Object[]{GpsStatus.Listener.class},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "getGpsStatus",
                new Object[]{android.location.GpsStatus.class},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.location.LocationManager",
                "getLastKnownLocation",
                new Object[]{String.class},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.net.wifi.WifiManager",
                "getScanResults",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getCellLocation",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));

        for (Method method : LocationManager.class.getDeclaredMethods()) {
            if (method.getName().equals("requestLocationUpdates")
                    && !Modifier.isAbstract(method.getModifiers())
                    && Modifier.isPublic(method.getModifiers())) {
                XposedBridge.hookMethod(method, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                });
            }
        }

        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getAllCellInfo",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));
        list.add(new HookEntry(
                PACKAGE_NAME,
                "android.telephony.TelephonyManager",
                "getNeighboringCellInfo",
                new Object[]{},
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
                    }
                }));


        return list;
    }
}
