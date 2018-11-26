package com.zhpan.module_home.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.base.mvc.fragment.BaseVcFragment;
import com.zhpan.library.router.RouterURL;
import com.zhpan.module_home.R;
import com.zhpan.module_home.R2;

import butterknife.BindView;

/**
 * Created by zhpan on 2018/7/24.
 */
@Route(path = RouterURL.FRAGMENT_HOME)
public class HomeFragment extends BaseVcFragment {
    @BindView(R2.id.tv_fragment)
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

    }

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }
}
