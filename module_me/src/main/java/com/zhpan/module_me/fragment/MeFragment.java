package com.zhpan.module_me.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.base.mvc.fragment.BaseVcFragment;
import com.zhpan.module_common.router.RouterURL;
import com.zhpan.module_me.R;

/**
 * Created by zhpan on 2018/7/24.
 */
@Route(path = RouterURL.FRAGMENT_ME)
public class MeFragment extends BaseVcFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
