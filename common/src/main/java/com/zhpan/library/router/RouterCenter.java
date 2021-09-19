package com.zhpan.library.router;

import com.alibaba.android.arouter.launcher.ARouter;

public class RouterCenter {
    /**
     * 跳转发现页
     */
    public static void toFindActivity() {
        ARouter.getInstance().build(RoutingTable.ACTIVITY_FIND).navigation();
    }

    /**
     * 主页
     */
    public static void toHome() {
//        ARouter.getInstance().build(RouterURL.HOME).navigation();
    }

}
