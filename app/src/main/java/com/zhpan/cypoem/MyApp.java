package com.zhpan.cypoem;


import android.content.Context;

import androidx.multidex.MultiDex;

import com.zhpan.library.base.BaseApp;

/**
 * Created by zhangpan on 2018/11/26.
 */
public class MyApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
