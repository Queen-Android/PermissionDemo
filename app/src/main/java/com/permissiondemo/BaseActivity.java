package com.permissiondemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.permissiondemo.utils.PermissionListener;
import com.permissiondemo.utils.PermissionUtils;

/**
 * Created by liudan on 2018/2/26.
 * 所有Activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    protected Activity mContext = this;
    /**
     * 权限监听
     */
    private PermissionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 请求权限(在onResume中会重复调用弹窗,直到允许权限)
     *
     * @param mActivity 上下文
     * @param permissions 权限
     * @param mListener 监听者
     */
    public void requestRunPermission(Activity mActivity, String[] permissions, PermissionListener mListener) {
        this.mListener = mListener;
        new PermissionUtils().requestRunPermission(mActivity, permissions, mListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        new PermissionUtils().requestPermissionsResult(requestCode, permissions, grantResults, mListener);
    }
}
