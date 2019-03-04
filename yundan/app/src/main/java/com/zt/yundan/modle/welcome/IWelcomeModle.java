package com.zt.yundan.modle.welcome;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.bean.VersionBean;

public interface IWelcomeModle {
    //版本更新信息
    void queryAppUpdateInfo(IResponseListener<VersionBean> listener);
}
