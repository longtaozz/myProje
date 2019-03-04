package com.zt.capacity.baidumap.utils;

import android.content.Context;

import com.zt.capacity.baidumap.listener.MyLocationListener;
import com.zt.capacity.baidumap.service.LocationService;

public class LocationUtil {
    private static Context context;
    private static LocationUtil location;

    public static LocationUtil getInit(Context contextx) {
        context = contextx;
        if(location==null){
            location=new LocationUtil();
        }
        return location;
    }

    //开启一次定位
    public void locationOne() {

        LocationService locationService = new LocationService(context, 0);
        locationService.getDefaultLocationClientOption(0);
        MyLocationListener myLocationListener = new MyLocationListener(context);//定位监听
        //定位回调监听
        locationService.registerListener(myLocationListener);
        //注册监听
        locationService.start();
    }

    public void remoService() {

        // 程序终止的时候执行
//        locationService.unregisterListener(myLocationListener); //注销掉监听
//        locationService.stop(); //停止定位服务
    }


}
