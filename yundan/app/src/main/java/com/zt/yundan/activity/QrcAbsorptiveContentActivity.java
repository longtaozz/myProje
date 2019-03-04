package com.zt.yundan.activity;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.yundan.R;

/**
 * 消纳场扫描页面
 * @author lt
 * @time 2019/2/21 16:12
 *
 **/
public class QrcAbsorptiveContentActivity extends MyActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrc_absorptive;
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

    }
}
