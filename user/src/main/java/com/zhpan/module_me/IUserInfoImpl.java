package com.zhpan.module_me;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.IUserInfo;
import com.zhpan.library.router.RoutingTable;

@Route(path = RoutingTable.USER_DATA)
public class IUserInfoImpl implements IUserInfo {
  @Override public String getUserId() {
    return UserInfoTools.getUserId();
  }

  @Override public String getUserName() {
    return UserInfoTools.getUserName();
  }

  @Override public int getUserAge() {
    return UserInfoTools.getUserAge();
  }

  @Override public void init(Context context) {

  }
}
