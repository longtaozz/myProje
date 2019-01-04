package com.app.lib_common.bean;

/**
* 通用返回值，由返回值定义
* @author lt
* @time 2018/12/11 11:36
*
**/
public class Result<T> {
    //返回码
    private Integer code;
    //信息
    private String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
