package com.m.mvvm.model.main;

import com.m.lib_http.listener.IResponseListener;
import com.m.mvvm.MyApplication;
import com.m.mvvm.api.Api;

public class MainModel implements IMainModel {
    @Override
    public void lodStr(IResponseListener<String> listener) {
        MyApplication.httpManager.request(
                MyApplication.httpManager.createApi(Api.class)
                        .get(),
                listener);
    }
}
