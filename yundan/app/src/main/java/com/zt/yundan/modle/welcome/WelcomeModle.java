package com.zt.yundan.modle.welcome;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.MyApplication;
import com.zt.yundan.api.Api;
import com.zt.yundan.bean.VersionBean;

public class WelcomeModle implements IWelcomeModle {
    @Override
    public void queryAppUpdateInfo(IResponseListener<VersionBean> listener) {
        MyApplication.httpManager.request(
                MyApplication.httpManager.createApi(Api.class)
                        .queryAppUpdateInfo(),
                listener);
    }
}
