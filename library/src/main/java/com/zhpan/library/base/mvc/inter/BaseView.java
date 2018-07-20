package com.zhpan.library.base.mvc.inter;

public interface BaseView extends ILoadingDialogView {

    //void onSuccess();

    void showError(String msg, String code);

   // void showErrorMsg(String errorMsg);


}
