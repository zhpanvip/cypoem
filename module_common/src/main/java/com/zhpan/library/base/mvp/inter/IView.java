package com.zhpan.library.base.mvp.inter;

import androidx.lifecycle.LifecycleOwner;
import android.content.Context;

/**
 * Description : 顶级view接口
 */
public interface IView extends LifecycleOwner {

    Context getContext();

    void showError(String msg, String code);

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();
}
