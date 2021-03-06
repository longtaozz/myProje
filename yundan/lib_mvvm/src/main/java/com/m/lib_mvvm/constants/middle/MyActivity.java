package com.m.lib_mvvm.constants.middle;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.hjq.umeng.UmengHelper;
import com.m.lib_mvvm.R;
import com.m.lib_mvvm.constants.structure.IView;
import com.m.lib_mvvm.constants.utils.ActivityJumpUtil;
import com.m.lib_mvvm.constants.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;


/**
 *  项目中的Activity基类
 */
public abstract class MyActivity<T> extends UIActivity<T> implements IView {


    @Override
    public void init() {

        initOrientation();

        super.init();
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        //如果没有指定屏幕方向，则默认为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        // 友盟统计
        UmengHelper.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 友盟统计
        UmengHelper.onPause(this);
    }


    /**
     * 显示一个吐司
     */
    public void toast(CharSequence s) {
        ToastUtils.show(s);
    }

    /**
     * 显示加载条
     */
    public void showProgressDialog(){
        UIUtils.showLoadingProgressDialog(this, R.string.loading_process_tip, false);
    }

    /**
     * 取消加载条
     */
    public void cancelProgressDialog(){
        UIUtils.cancelProgressDialog();
    }

    @Override
    public void showError(String message) {
        cancelProgressDialog();
        toast(message);
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void complete() {
        cancelProgressDialog();
    }

    @Override
    public void gotoActivity(Class activity) {
        startActivity(activity);
    }

    @Override
    public void gotoActivityFinish(Class activity) {
        startActivityFinish(activity);
    }

    @Override
    public void gotoActivityBund(Class activity, Bundle bundle) {
        startActivityBund(activity,bundle);
    }


}