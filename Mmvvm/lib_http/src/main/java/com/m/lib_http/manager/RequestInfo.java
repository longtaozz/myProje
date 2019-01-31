package com.m.lib_http.manager;

import android.content.Context;

import com.m.lib_http.http.HttpManager;

/**
 * 请求类
 * @author lt
 * @time 2019/1/29 15:02
 *
 **/
public class RequestInfo {
    private static HttpManager httpManager;

    //这个可以提前实例化好。如在baseActivity中或是Application中
    public static void init(Context context){
        httpManager = HttpManager.getInstance(context);
    }
}
