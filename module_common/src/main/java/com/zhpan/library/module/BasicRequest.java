package com.zhpan.library.module;


import com.zhpan.library.utils.SharedPreferencesHelper;
import com.zhpan.library.utils.Utils;

public class BasicRequest {
    public String token;


    public BasicRequest() {
        token = (String) SharedPreferencesHelper.get(Utils.getContext(), "token", "");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void updateToken(String token) {
        this.token = token;
    }
}
