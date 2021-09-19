package com.zhpan.module_home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhpan.library.IUserInfo;
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
  @Autowired(name = RoutingTable.USER_DATA)
  IUserInfo userInfo;
  private TextView mTvUserInfo;

  @Override
  protected int getLayout() {
    return R.layout.fragment_home;
  }

  @Override
  protected void initTitle() {

  }

  @Override
  protected void initView(Bundle savedInstanceState) {
    ARouter.getInstance().inject(this);
    mTextView = mView.findViewById(R.id.btn_fragment);
    mTvUserInfo = mView.findViewById(R.id.tv_user_info);
    mTextView.setOnClickListener(v -> RouterCenter.toFindActivity());
    mTvUserInfo.setText(
        getString(R.string.user_info, userInfo.getUserName(), userInfo.getUserAge()));
  }

  public static HomeFragment getInstance() {
    return new HomeFragment();
  }
}
