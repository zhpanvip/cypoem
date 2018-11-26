package com.zhpan.library.net.common;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhpan.library.utils.helper.HUDFactory;

import java.lang.ref.WeakReference;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhpan on 2018/3/22.
 */

public class ProgressUtils {
    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final WeakReference<Activity> weakActivity, String msg) {
        KProgressHUD kProgressHUD = createHud(weakActivity, msg);
        return upstream -> upstream
                .doOnSubscribe(disposable -> {
                })
                .doOnTerminate(kProgressHUD::dismiss)
                .doOnSubscribe((Consumer<Disposable>) disposable -> {

                });
    }

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final WeakReference<Activity> weakActivity) {
        return applyProgressBar(weakActivity, null);
    }

    private static KProgressHUD createHud(WeakReference<Activity> activity, String msg) {
        KProgressHUD kProgressHUD = HUDFactory.getInstance().createHUD(activity.get())
                .setLabel(msg)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);
        kProgressHUD.show();
        return kProgressHUD;
    }
}
