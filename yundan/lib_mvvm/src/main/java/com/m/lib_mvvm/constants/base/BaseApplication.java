package com.m.lib_mvvm.constants.base;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.hjq.umeng.UmengHelper;
import com.m.lib_http.http.HttpManager;
import com.m.lib_mvvm.BuildConfig;
import com.m.lib_mvvm.constants.middle.UIApplication;
import com.m.lib_mvvm.constants.utils.ToastStyle;

/**
 *  项目中的Application基类
 */
public class BaseApplication extends UIApplication {

    public static HttpManager httpManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化吐司工具类
        ToastUtils.init(this, new ToastStyle());

        //这个可以提前实例化好。如在baseActivity中或是Application中
        httpManager = HttpManager.getInstance(getApplicationContext());

        // 友盟统计
        UmengHelper.init(this);


        if (BuildConfig.DEBUG) {//必须写在init之前。不然没用
            ARouter.openLog();
            ARouter.openDebug();
//            ARouter.printStackTrace();
        }
        ARouter.init(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 使用 Dex分包
        MultiDex.install(this);
    }
}