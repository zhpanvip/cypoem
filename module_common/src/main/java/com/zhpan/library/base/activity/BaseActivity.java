package com.zhpan.library.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zhpan.library.R;
import com.zhpan.library.dialog.DialogUtils;
import com.zhpan.library.utils.BarUtils;
import com.zhpan.library.utils.SnackbarUtils;
import com.zhpan.library.utils.ToastUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;
    protected LinearLayout parentLinearLayout;
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    protected SwipeRefreshLayout mRefreshLayout;
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setTranslucentStatus(this,true);
        // setStatusBarTransparent();
        // setStatusBarColor(R.color.white);
        mRootView = View.inflate(this, R.layout.activity_base, null);
        addContent();
        setContentView(mRootView);
        setStatusBarColor(R.color.white);
        BarUtils.StatusBarLightMode(this);
        initToolBar();
        init(savedInstanceState);
    }

    private void addContent() {
        FrameLayout contentView = (FrameLayout) mRootView.findViewById(R.id.fl_content);
        View content = View.inflate(this, getLayoutId(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            contentView.addView(content, params);
            ButterKnife.bind(this, mRootView);
        }
    }


    //当你确定要使用沉浸式模式，那么只需要重写Activity的onWindowFocusChanged()方法，然后加入如下逻辑即可
   /* @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //  初始化刷新加载框架，子类中需要的时候调用
    /*protected void initPtr(boolean isAutoRefresh) {
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame);
        if (mPtrFrame == null) return;
        mPtrFrame.setMode(PtrFrameLayout.Mode.BOTH);
        PtrClassicListHeader header = new PtrClassicListHeader(this);
        header.setLastUpdateTimeRelateObject(this);
        PtrClassicListFooter footer = new PtrClassicListFooter(this);
        footer.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.setFooterView(footer);
        mPtrFrame.addPtrUIHandler(footer);

        setPtrHandler(null);

        mPtrFrame.setKeepHeaderWhenRefresh(true);
        if (isAutoRefresh)
            mPtrFrame.postDelayed((() -> mPtrFrame.autoRefresh()), 1000);
    }*/

  /*  protected void setPtrHandler(View view) {
        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                onPtrLoadMoreBegin(frame);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onPtrRefreshBegin(frame);
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return super.checkCanDoLoadMore(frame, null == view ? content : view, footer);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, null == view ? content : view, header);
            }
        });
    }

    *//**
     * 上拉加载
     *//*
    protected void onPtrLoadMoreBegin(PtrFrameLayout frame) {

    }

    *//**
     * 下拉刷新
     *//*
    protected void onPtrRefreshBegin(PtrFrameLayout frame) {

    }*/

    @Override
    public void onRefresh() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            mRefreshLayout.setRefreshing(false);
        }, 2000);
    }

    /******************************************* TollBar相关 ******************************************************/
    private void initToolBar() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);
        mToolbarSubTitle.setVisibility(View.GONE);
         /*
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Sub Title");
        */
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     *
     * @return toolbar标题
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    /**
     * 设置头部标题
     *
     * @param title 标题内容
     */
    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    public void setToolbarTitleColor(@ColorInt int color) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setTextColor(color);
        } else {
            getToolbar().setTitleTextColor(color);
        }
    }


    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.drawable.ic_back);
        //  返回按钮点击事件
        getToolbar().setNavigationOnClickListener((v) -> onBackPress());
    }


    protected ImageView getRightIv() {
        return (ImageView) findViewById(R.id.iv_right);
    }

    protected void setRightIvRes(@DrawableRes int res) {
        getRightIv().setBackgroundResource(res);
        getRightIv().setVisibility(View.VISIBLE);
    }

    protected void onBackPress() {
        onBackPressed();
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return 是否显示toolbar返回键
     */
    protected boolean isShowBacking() {
        return true;
    }

    /******************************************* TollBar相关结束 ******************************************************/

    private void setStatusBarTransparent() {
        //  把状态栏去掉
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //  设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * 设置系统标题栏透明
     *
     * @param activity 要设置的Activity
     * @param on       是否透明
     */
    protected void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     */
    public void setStatusBarColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(this, true);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Window statusBar = getWindow();
            statusBar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            statusBar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBar.setStatusBarColor(getResources().getColor(color));
        }
    }


    /**
     * Requests given permission.
     * If the permission has been denied previously, a Dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    protected void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            DialogUtils dialogUtils = new DialogUtils(this);
            dialogUtils.showTwoButtonDialog(getString(R.string.label_ok), v -> ActivityCompat.requestPermissions(BaseActivity.this,
                    new String[]{permission}, requestCode), v -> dialogUtils.dismissDialog());

           /* showAlertDialog(getString(R.string.permission_title_rationale), rationale,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(BaseActivity.this,
                                    new String[]{permission}, requestCode);
                        }
                    }, getString(R.string.label_ok), null, getString(R.string.label_cancel));*/
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    /**
     * 启动Activity
     *
     * @param context      跳转起始页面
     * @param baseActivity 跳转目的页面
     */
    public static void start(Context context, Class<? extends BaseActivity> baseActivity) {
        Intent intent = new Intent(context, baseActivity);
        if (!(context instanceof Activity))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context      跳转起始页面
     * @param baseActivity 跳转目的页面
     * @param bundle       跳转携带数据
     */
    public static void start(Context context, Bundle bundle, Class<? extends BaseActivity> baseActivity) {
        Intent intent = new Intent(context, baseActivity);
        if (!(context instanceof Activity))
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bundle", bundle);
        context.startActivity(intent);
    }

    public static void startForResult(Activity activity, int requestCode, Class<? extends BaseActivity> baseActivity) {
        Intent intent = new Intent(activity, baseActivity);
        activity.startActivityForResult(intent, requestCode);
    }

    public void showToast(String msg){
        ToastUtils.show(msg);
    }

    public void showSnackBar(View parent,String msg){
        SnackbarUtils.showShortSnackbar(parent,msg, Color.parseColor("#FFFFFF"),Color.parseColor("#222222"));

    }

    public void showToast(@StringRes int resId){
        ToastUtils.show(resId);
    }
}
