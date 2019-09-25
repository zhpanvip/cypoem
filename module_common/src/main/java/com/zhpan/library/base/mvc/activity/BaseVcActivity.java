package com.zhpan.library.base.mvc.activity;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhpan.library.common.BaseApplication;
import com.zhpan.library.common.BaseEventbusBean;
import com.zhpan.library.dialog.DialogUtils;
import com.zhpan.library.utils.LogUtils;
import com.zhpan.library.utils.helper.StatuBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * MVC模式的Base Activity
 */

public abstract class BaseVcActivity extends RxAppCompatActivity {
    protected DialogUtils mDialogUtils;
    private Unbinder unBinder;
    protected boolean regEvent;
    public BaseVcActivity mActivity;
    protected boolean isDestory = false;
    //管理事件流订阅的生命周期CompositeDisposable
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        mDialogUtils = new DialogUtils(this);
        //加入activity管理
//        BaseApplication.getAppContext().getActivityControl().addActivity(this);
        //沉浸式状态栏
        //setImmeriveStatuBar();
        mActivity = this;

        initTitle();
        initView(savedInstanceState);
        if (regEvent) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * rxjava管理订阅者
     */
    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("当前运行的activity:" + getClass().getName());
    }

    /**
     * 销毁
     *
     * @param v
     */
    public void back(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除订阅关系
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
        isDestory = true;
        //移除类
        BaseApplication.getAppContext().getActivityControl().removeActivity(this);

    }

    /**
     * 沉浸式状态栏
     */
    protected void setImmeriveStatuBar() {
        StatuBarCompat.setImmersiveStatusBar(true, Color.WHITE, this);

    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView(Bundle savedInstanceState);


}
