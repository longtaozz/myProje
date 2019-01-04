package com.app.lib_common.structure;

import android.util.Log;


import com.app.lib_common.listener.MyCallback;
import com.app.lib_common.okhttp.OkHttpUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class PModel<T>  {
    //数据请求参数
    protected Map mParams;
    /**
     * 设置数据请求参数
     * @param args 参数数组
     */
    public PModel params(Map args){
        mParams = args;
        return this;
    }

    /**
     * 添加Callback并执行数据请求
     * @param state     区分多个请求
     * @param callback
     */
    public abstract void execute(int state,MyCallback<T> callback);

    /**
     * 通用异步get请求
     * @param url
     * @param headers   头文件，可以为null
     * @param callback
     */
    protected void requestGetAPI(String url, Map<String, Object> headers, final MyCallback<String> callback){

        //这里写具体的网络请求
        OkHttpUtil.getInstance().getDataAsyn(url, headers, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {  // 0 成功   1 失败
                    callback.onHeaders(response.headers());
                    String json = response.body().string();
                    Log.e("fanhui+++++", json);
                    callback.onSuccess(json);
                } else {
                    callback.onError();
                }
                callback.onComplete();
            }
        });
    }
    // 执行Post网络请求
    protected void requestPostAPI(String url, Map<String, Object> headers, final MyCallback<String> callback){
        //这里写具体的网络请求
        OkHttpUtil.getInstance().postDataAsynJson(url, mParams, headers, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {  // 0 成功   1 失败
                    callback.onHeaders(response.headers());
                    String json = response.body().string();
                    Log.e("fanhui+++++", json);
                    callback.onSuccess(json);
                } else {
                    callback.onError();
                }
                callback.onComplete();
            }
        });
    }

    //同步请求，返回response，一般用在带进度条下载
    protected void requestGetFile(String url,MyCallback<Response> callback){
        callback.onSuccess(OkHttpUtil.getInstance().getDataSyn(url));
    }
}
