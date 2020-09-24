package cn.areful.xposed.hook;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;

public class MethodHook extends XC_MethodHook {
    public static final String TAG = "areful--";

    public MethodHook(String packageName, String className, String methodName, Object[] args) {
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
        Log.d(TAG, Log.getStackTraceString(new Throwable()));
        MethodBroadcastSender.send(methodName);
    }
}