package com.zt.yundan.vm;

import android.content.Context;
import android.text.TextUtils;

import com.m.lib_http.listener.IResponseListener;
import com.m.lib_mvvm.constants.structure.ViewModel;
import com.zt.yundan.bean.VersionBean;
import com.zt.yundan.modle.welcome.IWelcomeModle;
import com.zt.yundan.modle.welcome.WelcomeModle;
import com.zt.yundan.view.IWelcomeView;

public class WelcomeVM extends ViewModel {
    private IWelcomeModle welcomeModle;
    private boolean havePassword;
    private IWelcomeView welcomeView;

    public WelcomeVM(boolean havePassword) {
        welcomeModle = new WelcomeModle();
        this.havePassword = havePassword;
        welcomeView= (IWelcomeView) getIView();
    }

    public void queryAppUpdateInfo() {
        getIView().showLoading();
        welcomeModle.queryAppUpdateInfo(new IResponseListener<VersionBean>() {
            @Override
            public void onSuccess(VersionBean data) {
                getIView().complete();
            }

            @Override
            public void onComplete() {
                getIView().complete();
            }

            @Override
            public void onFail() {
                //无更新信息直接登录
                if (havePassword){
                }
            }
        });
    }
}
