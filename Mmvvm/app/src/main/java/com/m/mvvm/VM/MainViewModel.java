package com.m.mvvm.VM;

import com.m.lib_http.listener.IResponseListener;
import com.m.lib_mvvm.constants.structure.ViewModel;
import com.m.mvvm.activity.MainActivity;
import com.m.mvvm.model.main.IMainModel;
import com.m.mvvm.model.main.MainModel;

public class MainViewModel extends ViewModel<MainActivity> {
    private IMainModel mainModel;

    public MainViewModel() {
        mainModel = new MainModel();
    }

    public void lodStr() {
        getIView().showLoading();
        mainModel.lodStr(new IResponseListener<String>() {
            @Override
            public void onSuccess(String data) {
                getIView().complete();
            }

            @Override
            public void onComplete() {
                getIView().complete();
            }

            @Override
            public void onFail() {
                getIView().showError("服务器繁忙，请稍候");
            }
        });
    }
}
