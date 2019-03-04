package com.m.lib_mvvm.constants.middle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjq.toast.ToastUtils;
import com.hjq.umeng.UmengHelper;


/**
 *  项目中Fragment懒加载基类
 */
public abstract class MyLazyFragment extends UILazyFragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 友盟统计
        UmengHelper.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 友盟统计
        UmengHelper.onPause(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 显示一个吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }
}