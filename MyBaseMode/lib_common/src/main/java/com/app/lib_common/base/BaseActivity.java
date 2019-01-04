package com.app.lib_common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 *  Activity 基类
 */
public abstract class BaseActivity extends SupportActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        init();
    }

    public void init(){
        initView();
        initData();
    }

    //引入布局
    protected abstract int getLayoutId();

    //标题栏id
    protected abstract int getTitleBarId();

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    @Override
    public void finish() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        super.finish();
    }

    /**
     * 获取一个 Context 对象
     */
    public Context getContext() {
        return getBaseContext();
    }


    /**
     * 获取当前 Activity 对象
     */
    public <A extends BaseActivity> A getActivity() {
        return (A) this;
    }

    /**
     * 跳转到其他 Activity
     *
     * @param cls       目标Activity的Class
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转到其他 Activity 并销毁当前 Activity
     *
     * @param cls       目标Activity的Class
     */
    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivity(cls);
        finish();
    }

}