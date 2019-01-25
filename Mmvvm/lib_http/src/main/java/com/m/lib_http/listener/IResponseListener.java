package com.m.lib_http.listener;

/**
 * 回调监听
 * @author lt
 * @time 2019/1/24 16:55
 *
 **/
public interface IResponseListener<T> {
    void onSuccess(T data);

    void onFail();
}
