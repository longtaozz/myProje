package com.zt.yundan.modle.login;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.MyApplication;
import com.zt.yundan.api.Api;
import com.zt.yundan.bean.User;

import java.util.Map;

public class LoginModle implements ILoginModle {
    @Override
    public void login(Map<String,Object> map, IResponseListener<User> listener) {
        MyApplication.httpManager.request(
                MyApplication.httpManager.createApi(Api.class)
                        .login(map),
                listener);
    }
}
