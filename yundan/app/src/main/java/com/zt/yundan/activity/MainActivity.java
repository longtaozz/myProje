package com.zt.yundan.activity;

import android.view.View;

import com.m.lib_mvvm.constants.listener.PermissionListener;
import com.m.lib_mvvm.constants.middle.MyActivity;
import com.m.lib_mvvm.constants.utils.PermissionUtil;
import com.zt.yundan.R;
import com.zt.yundan.data.BaseData;
import com.zt.yundan.databinding.ActivityMainBinding;

public class MainActivity extends MyActivity<ActivityMainBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTopView() {
        return R.id.main_head;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return false;
    }

    @Override
    protected void initView() {
        baseBinDing.mainSelectTctj.setOnClickListener(this);//趟次统计
        baseBinDing.mainSweepButton.setOnClickListener(this);//扫一扫
        baseBinDing.mainSelectSslk.setOnClickListener(this);//路况
        baseBinDing.mainUserImg.setOnClickListener(this);//个人中心头像
        baseBinDing.mainSelectTccx.setOnClickListener(this);//趟次查询
    }

    @Override
    protected void initData() {
        if (BaseData.user != null) {
            baseBinDing.setUser(BaseData.user);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_select_tctj:
                //趟次统计
                break;
            case R.id.main_sweep_button:
                //扫一扫
                gotoActivity(CaptureActivity.class);
                break;
            case R.id.main_select_sslk:
                //路况
                gotoActivity(TrafficActivity.class);
                break;
            case R.id.main_user_img:
                //个人中心
                gotoActivity(PersonalActivity.class);
                break;
            case R.id.main_select_tccx:
                //趟次查询
                gotoActivity(StatisticActivity.class);
                break;
        }
    }
}
