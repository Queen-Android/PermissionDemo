package com.permissiondemo.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by liudan on 2018/2/26.
 * Toast公共类
 */

public class ToastUtils {
    public static void showToast(Activity activity, String message) {
        if (activity != null) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }
}
