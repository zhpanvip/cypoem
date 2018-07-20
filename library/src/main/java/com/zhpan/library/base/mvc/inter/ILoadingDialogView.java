/*
 * Copyright (c) 18-2-1 下午1:58. XQ Yang
 */

package com.zhpan.library.base.mvc.inter;

public interface ILoadingDialogView {

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();


}
