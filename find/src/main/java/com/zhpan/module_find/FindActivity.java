package com.zhpan.module_find;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import static com.zhpan.library.router.RoutingTable.ACTIVITY_FIND;

@Route(path = ACTIVITY_FIND)
public class FindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
    }
}
