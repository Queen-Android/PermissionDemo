package com.permissiondemo.utils;

import java.util.List;

/**
 * Created by liudan on 2018/2/26.
 * 申请权限的接口回调
 */

public interface PermissionListener {
    void onDenied(List<String> permissions);//未授权的权限

    void onGranted();//授权了
}
