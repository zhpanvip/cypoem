package com.zhpan.library.base.mvp;


import com.zhpan.library.base.mvp.inter.IDisposablePool;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 管理rxjava订阅关系,实现了连接丢弃  不继承这个类的model 要手动实现 添加和清空
 */
public class DisposablePool implements IDisposablePool {

    /**
     * 管理rxjava订阅关系
     */
    private CompositeDisposable mDisposable;

    @Override
    public void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable(disposable);
        } else {
            mDisposable.add(disposable);
        }
    }

    @Override
    public void clearPool() {
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
