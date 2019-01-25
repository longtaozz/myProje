package com.m.lib_http.api;

import com.m.lib_http.base.DemoBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 接口请求模版
 *
 * @author lt
 * @time 2019/1/25 10:40
 **/
public interface DemoApi {

    //登录,也可以直接放个对象，如DemoBean
    @POST
    Observable<DemoBean> postLog(@Url String fileUrl,@Body Map<String,Object> map);

    //用户信息
    @GET("cloudzf/user/login")
    Observable<DemoBean> getUser(@QueryMap Map<String,Object> maps);

    //下载文件
    @GET()
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
