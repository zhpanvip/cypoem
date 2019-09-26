package com.zhpan.library.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * MVC模式的Base fragment
 */
public abstract class BaseFragment extends Fragment {

    //    private Unbinder unBinder;
    protected Context mContext;
    protected boolean regEvent;

    protected View mView;

    //管理事件流订阅的生命周期CompositeDisposable
//    private CompositeDisposable compositeDisposable;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), container, false);
//        unBinder = ButterKnife.bind(this , view);
        initTitle();
        initView(savedInstanceState);

        if (regEvent) {
//            EventBus.getDefault().register(this);
        }
        return mView;
    }

    /**
     * rxjava管理订阅者
     */
//    protected void addDisposable(Disposable disposable) {
//        if (compositeDisposable == null) {
//            compositeDisposable = new CompositeDisposable();
//        }
//        compositeDisposable.add(disposable);
//    }
    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.i("当前运行的fragment:" + getClass().getName());
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(BaseEventbusBean event) {
//        onEvent(event);
//    }
//
//    protected void onEvent(BaseEventbusBean event) {
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (unBinder != null) {
//            unBinder.unbind();
//        }
//        if (regEvent) {
//            EventBus.getDefault().unregister(this);
//        }
    }


    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    /**
     * 初始化标题
     */
    protected abstract void initTitle();

    /**
     * 初始化数据
     */
    protected abstract void initView(Bundle savedInstanceState);

}
