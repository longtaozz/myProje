package com.zt.yundan.modle.statistic;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.bean.Statistic;

import java.util.List;

public interface IStatisticModle {
    void getStatistic(int yeshu,int geshu,IResponseListener<List<Statistic>> listener);
}
