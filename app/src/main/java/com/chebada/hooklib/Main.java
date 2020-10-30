package com.chebada.hooklib;

import com.chebada.hooklib.hookapis.MethodHookHelper;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam params) {
        new MethodHookHelper().hook(params);
    }
}