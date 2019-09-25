package com.zhpan.library.base.mvc.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.zhpan.library.R;
import com.zhpan.library.utils.LogUtils;

/**
 * PermissionsDispatcher 处理权限管理
 */

public abstract class BaseVcPermissionActivity extends BaseVcActivity {

    /***照相机权限*/
    public static final int PERMISSION_CAMERA = 10001;

    /**
     * 文件管理权限
     */
    public static final int PERMISSION_STORAGE = 10002;

    /***电话权限*/
    public static final int PERMISSION_PHONE = 10003;


    private boolean isShouldShow = false;

    /**
     * 判断是否含有当前权限
     */
    public boolean getPermission(String permission, int requestCode) {
        LogUtils.d("申请权限!isGranted(permission)=" + !isGranted(permission));
        LogUtils.d("申请权限isMarshmallow=" + isMarshmallow());

        if (!isGranted(permission) && isMarshmallow()) {
            //当前权限未授权，并且系统版本为6.0以上，需要申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                isShouldShow = true;
                LogUtils.d("申请权限=" + permission);
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            } else {
                LogUtils.d("没有申请权限=" + permission);
                isShouldShow = false;
                showPresmissionDialog(requestCode);
            }
            return false;
        }
        return true;
    }

    /**
     * 判断当前是否已经授权
     */
    protected boolean isGranted(String permission) {
        int granted = ActivityCompat.checkSelfPermission(this, permission);
        return granted == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 判断当前版本为6.0以上
     */
    protected boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions != null && permissions.length > 0 && grantResults != null && grantResults.length > 0) {
            int grantResult = grantResults[0];
            String permission = permissions[0];
            if (grantResult == PackageManager.PERMISSION_DENIED && !ActivityCompat.shouldShowRequestPermissionRationale(this, permission) && !isShouldShow) {
                //没有获取到权限,并且用户选择了不在提醒
                showPresmissionDialog(requestCode);
            }
        }
    }


    private void showPresmissionDialog(int requestCode) {
        mDialogUtils.showTwoButtonDialog("权限设置", v -> {
            //去设置
            mDialogUtils.dismissDialog();
            Uri packageURI = Uri.parse("package:" + getPackageName());
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            startActivity(intent);
        }, v -> mDialogUtils.dismissDialog());
    }


    public String initView(int requestCode) {
        String message = "";
        if (requestCode == BaseVcPermissionActivity.PERMISSION_CAMERA) {
            //照相机权限
            message = getString(R.string.home_permission_camera);
        } else if (requestCode == BaseVcPermissionActivity.PERMISSION_PHONE) {
            //电话权限
            message = getString(R.string.home_permission_phone);
        } else if (requestCode == BaseVcPermissionActivity.PERMISSION_STORAGE) {
            //文件操作权限
            message = getString(R.string.home_permission_storage);
        } else {
            message = getString(R.string.home_permission_default);
        }
        return message;
//        return requestCode+"";
    }
}
