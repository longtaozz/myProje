package com.m.lib_mvvm.constants.utils;

import android.view.Gravity;

import com.hjq.toast.style.ToastQQStyle;

public class ToastStyle extends ToastQQStyle {
    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int getYOffset() {
        return 140;
    }
}
