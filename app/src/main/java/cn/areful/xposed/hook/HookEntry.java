package cn.areful.xposed.hook;

import de.robv.android.xposed.XC_MethodHook;

public class HookEntry {
    public HookEntry(String packageName, String className, String methodName, Object[] args, XC_MethodHook methodHook) {
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.args = args;
        this.methodHook = methodHook;
    }

    public String packageName;
    public String className;
    public String methodName;
    public Object[] args;
    public XC_MethodHook methodHook;

    public Object[] getArgs() {
        Object[] newArgs = new Object[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = methodHook;
        return newArgs;
    }
}