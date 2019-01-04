package com.my.base.model.model;


import android.se.omapi.Reader;
import android.text.TextUtils;

import com.app.lib_common.bean.Result;
import com.app.lib_common.listener.MyCallback;
import com.app.lib_common.structure.PModel;
import com.app.lib_common.utils.JSONUtil;
import com.my.base.model.BaseData.ApiData;
import com.my.base.model.bean.CopyUser;

import okhttp3.Headers;

/**
 * 可进行拷贝的接口实现类
 */
public class CopyUserModel extends PModel<CopyUser> {

    @Override
    public void execute(int state, final MyCallback<CopyUser> callback) {
        switch (state) {
            case 1:
                //登录
                requestPostAPI(ApiData.LOGIN, null, new MyCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data != null && !TextUtils.isEmpty(data)){
                            try {
                                Result<CopyUser> copyUser= JSONUtil.strToObject(data,CopyUser.class);
                                callback.onSuccess(copyUser.getData());
                            }catch (Exception e){
                                callback.onSuccess(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        callback.onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }

                    @Override
                    public void onHeaders(Headers headers) {
                        callback.onHeaders(headers);
                    }
                });
                break;
        }
    }
}