package com.m.mvvm.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 接口请求地址
 * @author lt
 * @time 2019/1/29 15:14
 *
 **/
public interface Api {

    @GET("/")
    Observable<String> get();
}
