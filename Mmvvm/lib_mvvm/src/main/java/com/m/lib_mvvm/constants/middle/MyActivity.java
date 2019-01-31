package com.m.lib_mvvm.constants.middle;

import android.content.pm.ActivityInfo;

import com.hjq.toast.ToastUtils;
import com.m.lib_mvvm.R;
import com.m.lib_mvvm.constants.structure.IView;
import com.m.lib_mvvm.constants.utils.UIUtils;



/**
 *  项目中的Activity基类
 */
public abstract class MyActivity extends UIActivity implements IView {


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
    public boolean statusBarDarkFont() {
        //返回true表示黑色字体
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 友盟统计
//        UmengHelper.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 友盟统计
//        UmengHelper.onPause(this);
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
}