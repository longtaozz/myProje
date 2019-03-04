package com.m.lib_mvvm.constants;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class GTset extends ViewGroup {
    public GTset(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
