package com.zt.yundan.activity;

import android.os.Build;
import android.os.Bundle;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.yundan.R;

/**
 * 工地扫描页面
 *
 * @author lt
 * @time 2019/2/21 16:11
 **/
public class QrcConsappContentActivity extends MyActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrc_consapp;
    }

    @Override
    protected int getTopView() {
        return R.id.ll_head;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //获取传过来的值
        Bundle b = getBundle();
        if (b != null) {

        }
    }
}
