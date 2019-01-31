package com.m.lib_http;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.m.lib_http.api.DemoApi;
import com.m.lib_http.base.DemoBean;
import com.m.lib_http.http.HttpManager;
import com.m.lib_http.listener.IResponseListener;

import java.util.HashMap;
import java.util.Map;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个可以提前实例化好。如在baseActivity中或是Application中
        HttpManager httpManager = HttpManager.getInstance(getApplicationContext());
        Map<String , Object> map=new HashMap<>();
        map.put("username", "xiexin");
        map.put("password", "123456");
        httpManager.request(httpManager.createApi(DemoApi.class).post(map), new IResponseListener<DemoBean>() {
            @Override
            public void onSuccess(DemoBean data) {
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFail() {

            }
        });
    }
}
