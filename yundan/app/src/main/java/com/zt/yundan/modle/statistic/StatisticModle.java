package com.zt.yundan.modle.statistic;

import android.util.Log;

import com.m.lib_http.listener.IResponseListener;
import com.zt.yundan.bean.Statistic;

import java.util.ArrayList;
import java.util.List;

public class StatisticModle implements IStatisticModle {

    @Override
    public void getStatistic(final int yeshu, final int geshu, final IResponseListener<List<Statistic>> listener) {
        List<Statistic> statistics = new ArrayList<>();
        for (int i = yeshu * geshu; i < (yeshu + 1) * geshu; i++) {
            if (i % 2 == 0)
                statistics.add(new Statistic("湘A3211" + i, "没给供货商" + i, "湘江消纳" + i, "2019-02-26 14:40:0" + i, true));
            else
                statistics.add(new Statistic("湘A3211" + i, "没给供货商" + i, "湘江消纳" + i, "2019-02-26 14:40:0" + i, false));
        }
        listener.onSuccess(statistics);
    }
}
