package com.app.lib_common.middle;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.app.lib_common.utils.ActivityStackManager;
import com.hjq.toast.ToastUtils;

/**
 *  项目中的Application基类
 */
public class MyApplication extends UIApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化吐司工具类
        ToastUtils.init(this);

        // 友盟统计
//        UmengHelper.init(this);

        // Activity 栈管理
        ActivityStackManager.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        MultiDex.install(this);
    }
}