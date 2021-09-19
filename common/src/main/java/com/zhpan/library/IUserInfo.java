package com.zhpan.library;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IUserInfo extends IProvider {
  String getUserId();

  String getUserName();

  int getUserAge();
}
