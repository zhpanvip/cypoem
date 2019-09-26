package com.zhpan.module_home.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.R2;
import com.zhpan.library.fragment.BaseFragment;
import com.zhpan.library.router.RouterCenter;
import com.zhpan.library.router.RoutingTable;
import com.zhpan.module_home.R;

/**
 * Created by zhpan on 2018/7/24.
 */
@Route(path = RoutingTable.FRAGMENT_HOME)
public class HomeFragment extends BaseFragment {
//    @BindView(R2.id.tv_fragment)
    TextView mTextView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTextView = mView.findViewById(R.id.tv_fragment);
        mTextView.setOnClickListener(v -> RouterCenter.toFindActivity());

    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
}
