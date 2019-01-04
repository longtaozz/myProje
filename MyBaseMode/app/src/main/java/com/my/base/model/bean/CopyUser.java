package com.my.base.model.bean;


import java.io.Serializable;
/**
* 用户信息（bean模版）
* @author lt
* @time 2018/12/11 16:33
*
**/
public class CopyUser implements Serializable {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
