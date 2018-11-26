package com.zhpan.module_publish.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.base.mvc.fragment.BaseVcFragment;
import com.zhpan.library.router.RouterURL;
import com.zhpan.module_publish.R;

/**
 * Created by zhpan on 2018/7/24.
 */
@Route(path = RouterURL.FRAGMENT_PUBLISH)
public class PublishFragment extends BaseVcFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_publish;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public static PublishFragment getInstance() {
        return new PublishFragment();
    }
}
