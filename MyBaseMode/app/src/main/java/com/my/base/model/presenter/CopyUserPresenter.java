package com.my.base.model.presenter;

import com.app.lib_common.listener.MyCallback;
import com.app.lib_common.model.MidModel;
import com.app.lib_common.structure.PModel;
import com.app.lib_common.structure.PPresenter;
import com.my.base.model.BaseData.CopyModelRoute;
import com.my.base.model.activity.CopyUserActivity;
import com.my.base.model.contract.CopyUserContract;
import com.my.base.model.model.CopyUserModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;

/**
* 业务处理类模版
* @author lt
* @time 2018/12/11 13:53
*
**/
public class CopyUserPresenter extends PPresenter<CopyUserActivity> implements CopyUserContract.Presenter {


    @Override
    public void start() {
    }

    @Override
    public void login(String userName, String password) {
        Map map=new HashMap();
        map.put("userName",userName);
        map.put("password",password);
        MidModel.request(CopyModelRoute.API_USER)
                .params(map)
                .execute(1, new MyCallback() {
                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onHeaders(Headers headers) {

                    }
                });
    }
}