package com.zt.yundan.bean;

import java.io.Serializable;

/**
 * 趟次统计信息
 * @author lt
 * @time 2019/2/26 12:01
 *
 **/
public class Statistic implements Serializable {
    private String carNumber;
    private String projectName;
    private String absorptiveName;
    private String unloadingTime;
    private Boolean locus;

    public Boolean getLocus() {
        return locus;
    }

    public void setLocus(Boolean locus) {
        this.locus = locus;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAbsorptiveName() {
        return absorptiveName;
    }

    public void setAbsorptiveName(String absorptiveName) {
        this.absorptiveName = absorptiveName;
    }

    public String getUnloadingTime() {
        return unloadingTime;
    }

    public void setUnloadingTime(String unloadingTime) {
        this.unloadingTime = unloadingTime;
    }

    public Statistic(String carNumber, String projectName, String absorptiveName, String unloadingTime, Boolean locus) {
        this.carNumber = carNumber;
        this.projectName = projectName;
        this.absorptiveName = absorptiveName;
        this.unloadingTime = unloadingTime;
        this.locus = locus;
    }

    public Statistic() {
    }
}
