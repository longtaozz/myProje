package com.m.mvvm.model.main;

import com.m.lib_http.listener.IResponseListener;

public interface IMainModel {
    void lodStr(IResponseListener<String> listener);
}
