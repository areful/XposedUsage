package cn.areful.xposed.hook;

import android.util.Log;

import cn.areful.xposed.sample.hooks.HookHelper;
import de.robv.android.xposed.XC_MethodHook;

public class HookEntry extends XC_MethodHook {
    public HookEntry(String packageName, String className, String methodName, Object[] args) {
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.args = args;
    }

    public String packageName;
    public String className;
    public String methodName;
    public Object[] args;

    public Object[] getArgs() {
        Object[] newArgs = new Object[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = this;
        return newArgs;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
        HookHelper.send(methodName);
    }
}