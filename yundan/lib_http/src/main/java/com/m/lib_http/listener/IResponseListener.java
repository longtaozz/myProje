package com.m.lib_http.listener;

/**
 * 回调监听
 * @author lt
 * @time 2019/1/24 16:55
 *
 **/
public interface IResponseListener<T> {

    //成功数据返回
    void onSuccess(T data);

    //请求完成，不管成功失败
    void onComplete();

    //错误
    void onFail();
}
