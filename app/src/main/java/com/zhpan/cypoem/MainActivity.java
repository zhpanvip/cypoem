package com.zhpan.cypoem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zhpan.library.utils.AppUtils;
import com.zhpan.library.utils.ToastUtils;
import com.zhpan.module_common.router.RouterURL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtils.AppInfo appInfo = AppUtils.getAppInfo(this);
        String packageName = appInfo.getPackageName();
        ToastUtils.show(packageName);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_main:
//                RouterCenter.toMain();
                ARouter.getInstance().build(RouterURL.MAIN).navigation();
                break;
            case R.id.btn_home:
                ARouter.getInstance().build(RouterURL.HOME).navigation();
                break;
        }
    }
}
