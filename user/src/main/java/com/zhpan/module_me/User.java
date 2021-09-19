package com.zhpan.module_me;

public class User {
  private String name;
  private String userId;
  private int age;

  public User(String name, String userId, int age) {
    this.name = name;
    this.userId = userId;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getUserId() {
    return userId;
  }

  public int getAge() {
    return age;
  }
}
