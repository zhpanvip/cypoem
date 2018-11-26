package com.zhpan.library.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhpan.library.common.AppConfig;
import com.zhpan.library.utils.CrashUtils;
import com.zhpan.library.utils.SPUtils;
import com.zhpan.library.utils.Utils;


public class BaseApp extends Application {
    public static final String ROOT_PACKAGE = "com.zhpan";
    @Override
    public void onCreate() {
        super.onCreate();
        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/
        initRouter();
        SPUtils.init(this);
        Utils.init(this,true);
        CrashUtils.getInstance().init();

    }

    private void initRouter() {
        if (AppConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
