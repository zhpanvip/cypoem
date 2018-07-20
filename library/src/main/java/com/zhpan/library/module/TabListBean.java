package com.zhpan.library.module;


import com.zhpan.library.base.BaseCoreFragment;

/**
 * @Created by TOME .
 * @时间 2018/6/5 15:27
 * @描述 ${}
 */
public class TabListBean {
    private String title;
    private BaseCoreFragment fragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BaseCoreFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseCoreFragment fragment) {
        this.fragment = fragment;
    }

    public TabListBean(String title, BaseCoreFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
