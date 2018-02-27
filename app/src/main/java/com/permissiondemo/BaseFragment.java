package com.permissiondemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.permissiondemo.utils.PermissionListener;

/**
 * Created by liudan on 2018/2/26.
 * 所有fragment的基类
 */

public class BaseFragment extends Fragment {
    /**
     * Activity mContext
     */
    protected Activity mContext;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        return null;
    }

    /**
     * 请求权限
     *
     * @param mActivity
     * @param permissions
     * @param mListener
     */
    public void requestRunPermission(Activity mActivity, String[] permissions, PermissionListener mListener) {
        ((BaseActivity) mActivity).requestRunPermission(mActivity, permissions, mListener);
    }
}
