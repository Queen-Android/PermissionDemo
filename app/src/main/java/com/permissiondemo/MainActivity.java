package com.permissiondemo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.permissiondemo.utils.PermissionListener;
import com.permissiondemo.utils.PermissionUtils;
import com.permissiondemo.utils.ToastUtils;

import java.util.List;

/**
 * 工具类的测试使用
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button goTabButton = (Button) findViewById(R.id.button2);

        Button openPermissionActivity = (Button) findViewById(R.id.openPermissionActivity);
        openPermissionActivity.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
            intent.setData(uri);
            mContext.startActivity(intent);
        });
        permission();
        button.setOnClickListener(v -> permission());
        goTabButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BeautifulTabActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 判断权限
     */
    private void permission() {
        requestRunPermission(mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE}, new PermissionListener() {
            @Override
            public void onDenied(List<String> permissions) {


                PermissionUtils.openSettingActivity(mContext, "请打开权限");
            }

            @Override
            public void onGranted() {
                //表示所有权限都授权了
                ToastUtils.showToast(mContext, "已经全部接受，可以处理其他事儿了^_^");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
