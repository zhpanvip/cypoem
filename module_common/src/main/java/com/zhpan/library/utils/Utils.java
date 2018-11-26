package com.zhpan.library.utils;

import android.content.Context;

import com.zhpan.library.BuildConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author: zhpan
 * time  : 16/12/08
 * desc  : Utils初始化相关
 */
public class Utils {

    /**
     * 此处为Application的context不会造成内存泄漏
     */
    private static Context context;

    /**
     * 是否为debug版
     */
    private static Boolean sDebug;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    public static void init(Context context,boolean isDebug) {
        Utils.context = context.getApplicationContext();
        Utils.sDebug=isDebug;
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static Boolean isDebug() {
        return sDebug;
    }

    /**
     * @return 是否是debug版本
     */
    private static boolean isDebugBuild() {
        if (sDebug == null) {
            try {
                final Class<?> activityThread = Class.forName("android.app.ActivityThread");
                final Method currentPackage = activityThread.getMethod("currentPackageName");
                final String packageName = (String) currentPackage.invoke(null, (Object[]) null);
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field DEBUG = buildConfig.getField("DEBUG");
                DEBUG.setAccessible(true);
                sDebug = DEBUG.getBoolean(null);
            } catch (final Throwable t) {
                final String message = t.getMessage();
                if (message != null && message.contains("BuildConfig")) {
                    // Proguard obfuscated build. Most likely a production build.
                    sDebug = false;
                } else {
                    sDebug = BuildConfig.DEBUG;
                }
            }
        }
        return sDebug;
    }
}