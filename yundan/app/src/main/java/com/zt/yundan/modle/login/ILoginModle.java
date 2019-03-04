package com.zt.yundan.modle.login;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.bean.User;

import java.util.Map;

public interface ILoginModle {
    //登录
    void login(Map<String,Object> map,IResponseListener<User> listener);
}
