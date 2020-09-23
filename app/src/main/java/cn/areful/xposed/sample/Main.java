package cn.areful.xposed.sample;

import cn.areful.xposed.sample.hooks.MethodHookHelper;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam params) {
        new MethodHookHelper().hook(params);
    }
}