package com.permissiondemo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.targetSdkVersion;

/**
 * Created by liudan on 2018/2/26.
 * 动态权限管理
 */

public class PermissionUtils {
    private static final int PERMISSION_REQUEST_CODE = 100;
    /**
     * 请求权限
     * @param mActivity
     * @param permissions
     */
    public void requestRunPermission(Activity mActivity, String[] permissions, PermissionListener mListener) {
        List<String> permissionLists = new ArrayList<>();
        for (String permission : permissions) {
            if (selfPermissionGranted(mActivity, permission)) {
                permissionLists.add(permission);
            }
        }
        if (!permissionLists.isEmpty()) {
            ActivityCompat.requestPermissions(mActivity, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUEST_CODE);
        } else {
            //表示全都授权了
            mListener.onGranted();
        }
    }
    /**
     * 判断权限是否已授权
     * @param permission
     * @return
     */
    public boolean selfPermissionGranted(Activity mContext,String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(mContext, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return result;
    }
    /**
     * 请求权限结果，baseActivity调用
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param mListener
     */
    public void requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, PermissionListener mListener) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0){
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //说明都授权了
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 打开设置权限页面
     * @param activity
     * @param message
     */
    public static void openSettingActivity(final Activity activity, String message) {
        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                ToastUtils.showToast(activity,"请设置必要的权限！");
            }
        });
    }

    /**
     * 显示弹框
     * @param context
     * @param message
     * @param settingListener
     * @param cancelListener
     */
    private static void showMessageOKCancel(final Activity context, String message,
                                            DialogInterface.OnClickListener settingListener,
                                            DialogInterface.OnClickListener cancelListener) {
        String permissionMessage = "当前应用缺少必要权限("+message+")\n" +
                "\n 请点击“设置”-“权限”-打开所需权限。\n"+"" +
                "\n 最后点击两次后退按钮，即可返回";
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage(permissionMessage)
                .setPositiveButton("设置", settingListener)
                .setNegativeButton("取消", cancelListener)
                .create()
                .show();

    }
}
