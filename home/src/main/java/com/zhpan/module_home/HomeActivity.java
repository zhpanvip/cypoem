package com.zhpan.module_home;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhpan.library.router.RoutingTable;

@Route(path = RoutingTable.FRAGMENT_HOME)
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        ToastUtils.show("this is home");
//        ARouter.getInstance().inject(this);
    }
}
