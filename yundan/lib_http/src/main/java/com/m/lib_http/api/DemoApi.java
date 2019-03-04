package com.m.lib_http.api;

import com.m.lib_http.base.DemoBean;
import com.m.lib_http.bean.BaseResult;

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
    @POST("cloudzf/user/login")
    Observable<BaseResult<DemoBean>> post(@Body Map<String,Object> map);

//    @GET("cloudzf/user/login")此写法报错
    //用户信息
    @GET
    Observable<DemoBean> get(@Url String fileUrl,@QueryMap Map<String,Object> maps);

    //下载文件
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
