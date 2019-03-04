package com.zt.yundan.activity;

import android.app.Activity;
import android.view.View;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.yundan.R;
import com.zt.yundan.bean.Title;
import com.zt.yundan.data.BaseData;
import com.zt.yundan.databinding.ActivityPersonalBinding;
import com.zt.yundan.databinding.ActivityTrafficBinding;

/**
 * 个人中心
 *
 * @author lt
 * @time 2019/2/25 15:16
 **/
public class PersonalActivity extends MyActivity<ActivityPersonalBinding> implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected int getTopView() {
        return R.id.include_title;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {
        Title title = new Title();
        title.setCenterName("个人中心");
        baseBinDing.setTitle(title);
        baseBinDing.setUser(BaseData.user);
        baseBinDing.includeTitle.back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
