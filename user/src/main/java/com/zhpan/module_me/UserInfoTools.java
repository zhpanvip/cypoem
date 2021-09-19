package com.zhpan.module_me;

public class UserInfoTools {

  public static User user;

  private UserInfoTools() {
  }

  private static class InstanceHolder {
    final static User INSTANCE = new User("Ryan", "123456", 18);
  }

  public static User getUser() {
    return InstanceHolder.INSTANCE;
  }

  public static String getUserName() {
    return getUser().getName();
  }

  public static String getUserId() {
    return getUser().getUserId();
  }

  public static int getUserAge() {
    return getUser().getAge();
  }
}
