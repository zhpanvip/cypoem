package com.zhpan.cypoem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhpan.library.router.RouterCenter;
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
        TextView textView = findViewById(R.id.tv_text);
        textView.setOnClickListener(v -> RouterCenter.toHome());
    }
}
