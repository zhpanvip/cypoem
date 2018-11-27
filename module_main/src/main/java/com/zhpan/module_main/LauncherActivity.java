package com.zhpan.module_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhpan.library.utils.ToastUtils;


public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ToastUtils.show("This is LauncherActivity in main module");
    }
}
