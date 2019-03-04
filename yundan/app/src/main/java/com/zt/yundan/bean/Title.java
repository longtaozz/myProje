package com.zt.yundan.bean;

import java.io.Serializable;

/**
 * 主题信息类
 * @author lt
 * @time 2019/2/25 13:42
 *
 **/
public class Title implements Serializable {
    private String leftName;
    private String centerName;
    private String rightName;

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }
}
