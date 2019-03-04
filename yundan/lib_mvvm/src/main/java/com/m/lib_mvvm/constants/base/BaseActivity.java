package com.m.lib_mvvm.constants.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.Constraints;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Activity 基类
 */
public abstract class BaseActivity<T> extends SupportActivity {

    public T baseBinDing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() > 0) {
            baseBinDing = (T) DataBindingUtil.setContentView(this, getLayoutId());
        }
        if (getTopView() > 0) {
            final View v = findViewById(getTopView());

            //通过View.post方法把Runnable对象放到消息队列的末尾，当执行到这个runable方法的时候，View所有的初始化测量方法说明都已经执行完毕了。
            v.post(new Runnable() {
                @Override
                public void run() {
                    int result = 0;
                    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                    if (resourceId > 0) {
                        result = getResources().getDimensionPixelSize(resourceId);
                    }
                    ViewGroup.LayoutParams lp = v.getLayoutParams();
                    lp.height = v.getHeight() + result;
                    v.setLayoutParams(lp);
                    v.setPadding(0, result, 0, 0);
                }
            });
        }
        init();
    }

    public void init() {
        initView();
        initData();
    }

    //引入布局
    protected abstract int getLayoutId();

    //头布局沉侵式状态栏用的,不使用传null
    protected abstract int getTopView();

    //状态栏颜色(true黑色，false白色)
    protected abstract boolean statusBarDarkFont();

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
     * @param cls 目标Activity的Class
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 跳转到其他 Activity 并销毁当前 Activity
     *
     * @param cls 目标Activity的Class
     */
    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivity(cls);
        finish();
    }

    /**
     * 跳转并传值
     *
     * @param cls
     * @param bundle
     */
    public void startActivityBund(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("Message", bundle);
        startActivity(intent, bundle);
    }

    /**
     * 传值统一用Message
     */
    public Bundle getBundle() {
        return getIntent().getBundleExtra("Message");
    }

}