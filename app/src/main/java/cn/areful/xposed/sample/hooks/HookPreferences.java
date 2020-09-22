package cn.areful.xposed.sample.hooks;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HookPreferences {

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // package name begin
    public static final String KEY_PACKAGE_NAME = "packageName";

    public static void setPackageName(Context context, String pkgName) {
        getPreferences(context).edit().putString(KEY_PACKAGE_NAME, pkgName).apply();
    }

    public static String getPackageName(Context context) {
        return getPreferences(context).getString(KEY_PACKAGE_NAME, "");
    }
    // package name end
}
