package com.zhpan.module_me.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.fragment.BaseFragment;
import com.zhpan.library.router.RoutingTable;
import com.zhpan.module_me.R;

/**
 * Created by zhpan on 2018/7/24.
 */
@Route(path = RoutingTable.FRAGMENT_ME)
public class MeFragment extends BaseFragment {
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

    public static MeFragment getInstance(){
        return new MeFragment();
    }
}
