package com.m.lib_http.bean;

import java.io.Serializable;

/**
 * 通用返回值
 * @author lt
 * @time 2019/1/25 10:26
 *
 **/
public class BaseResult<T> implements Serializable {
    //返回状态
    private Integer code;
    //提示
    private String message;
    //返回值
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessge() {
        return message;
    }

    public void setMessge(String messge) {
        this.message = messge;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
