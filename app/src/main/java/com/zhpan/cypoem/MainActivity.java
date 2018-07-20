package com.zhpan.cypoem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhpan.library.utils.AppUtils;
import com.zhpan.library.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtils.AppInfo appInfo = AppUtils.getAppInfo(this);
        String packageName = appInfo.getPackageName();
        ToastUtils.show(packageName);
    }
}
