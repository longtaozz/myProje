package com.zt.yundan;

import com.baidu.mapapi.SDKInitializer;
import com.m.lib_mvvm.constants.base.BaseApplication;
import com.zt.capacity.baidumap.utils.LocationUtil;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化baiduMap
        SDKInitializer.initialize(getApplicationContext());
    }

}
