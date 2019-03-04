package com.zt.yundan.api;

import com.m.lib_http.bean.BaseResult;
import com.zt.yundan.bean.Unloading;
import com.zt.yundan.bean.User;
import com.zt.yundan.bean.VersionBean;
import com.zt.yundan.bean.Worksite;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 接口请求地址
 * @author lt
 * @time 2019/1/29 15:14
 *
 **/
public interface Api {

    @GET("/")
    Observable<String> get();

    //版本更新信息
    @POST("common/project/version/queryAppUpdateInfo")
    Observable<BaseResult<VersionBean>> queryAppUpdateInfo();

    //登录
    @POST("api/user/login")
    Observable<BaseResult<User>> login(@Body Map<String,Object> map);

    //查询消纳场信息
    @POST("api/unloading/getUnloadingInfo")
    Observable<BaseResult<Unloading>> getUnloadingInfo(@Body Map<String,Object> map);

    //查询工地信息
    @POST("api/worksite/getWorksiteInfo")
    Observable<BaseResult<Worksite>> getWorksiteInfo(@Body Map<String,Object> map);
}
