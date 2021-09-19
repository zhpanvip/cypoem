package com.zhpan.library.router;

import android.text.TextUtils;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;

public class RouteServiceManager {
  public static <T extends IProvider> T provide( String path) {

    if (TextUtils.isEmpty(path)) {
      return null;
    }

    IProvider provider = null;

    try {
      provider = (IProvider) ARouter.getInstance()
          .build(path)
          .navigation();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T) provider;
  }

  public static <T extends IProvider> T provide(Class<T> clz) {

    IProvider provider = null;
    try {
      provider = ARouter.getInstance().navigation(clz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T) provider;
  }

}
