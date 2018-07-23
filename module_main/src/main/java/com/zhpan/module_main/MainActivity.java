package com.zhpan.module_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.utils.ToastUtils;
import com.zhpan.module_common.router.RouterURL;

@Route(path = RouterURL.MAIN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ToastUtils.show("this is main");
    }
}
