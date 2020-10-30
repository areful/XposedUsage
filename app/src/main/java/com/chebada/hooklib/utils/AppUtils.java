package com.chebada.hooklib.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class AppUtils {

    public static List<ApplicationInfo> getAppList(PackageManager pm) {
        try {
            List<ApplicationInfo> list = new ArrayList<>();
            for (PackageInfo pi : pm.getInstalledPackages(PackageManager.GET_ACTIVITIES)) {
                ApplicationInfo ai = pi.applicationInfo;
                boolean isSysApp = (ai.flags & ApplicationInfo.FLAG_SYSTEM) == 1;
                if (!isSysApp) {
                    list.add(ai);
                }
            }
            return list;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return new ArrayList<>();
    }
}
