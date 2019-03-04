package com.zt.yundan.vm;

import com.m.lib_http.listener.IResponseListener;
import com.m.lib_mvvm.constants.structure.ViewModel;
import com.zt.yundan.activity.LoginActivity;
import com.zt.yundan.activity.MainActivity;
import com.zt.yundan.bean.User;
import com.zt.yundan.data.BaseData;
import com.zt.yundan.modle.login.ILoginModle;
import com.zt.yundan.modle.login.LoginModle;

import java.util.Map;

public class LoginVM extends ViewModel<LoginActivity> {
    private ILoginModle loginModle;

    public LoginVM() {
        this.loginModle = new LoginModle();
    }

    public void login(Map<String, Object> map) {
        getIView().showLoading();
        loginModle.login(map, new IResponseListener<User>() {
            @Override
            public void onSuccess(User data) {
                getIView().complete();
                BaseData.user = data;
                getIView().gotoActivityFinish(MainActivity.class);
            }

            @Override
            public void onComplete() {
                getIView().complete();
            }

            @Override
            public void onFail() {
                //登录失败
                getIView().showError("登录失败！");
                //没有接口
//                getIView().gotoActivityFinish(MainActivity.class);
            }
        });
    }
}
