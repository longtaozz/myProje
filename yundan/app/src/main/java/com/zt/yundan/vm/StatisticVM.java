package com.zt.yundan.vm;

import android.util.Log;

import com.m.lib_http.listener.IResponseListener;
import com.m.lib_mvvm.constants.structure.IView;
import com.m.lib_mvvm.constants.structure.ViewModel;
import com.zt.yundan.activity.StatisticActivity;
import com.zt.yundan.bean.Statistic;
import com.zt.yundan.modle.statistic.IStatisticModle;
import com.zt.yundan.modle.statistic.StatisticModle;
import com.zt.yundan.view.IRecyclerView;

import java.util.List;

public class StatisticVM extends ViewModel<StatisticActivity> {
    private IStatisticModle statisticModle;
    private IRecyclerView<List<Statistic>> view;

    public StatisticVM() {
        statisticModle=new StatisticModle();
    }

    public void getStatistic(int yeshu, int geshu, final int state){
        if(view == null){
            view= (IRecyclerView) getIView();
        }
        view.showLoading();
        statisticModle.getStatistic(yeshu,geshu,new IResponseListener<List<Statistic>>() {
            @Override
            public void onSuccess(List<Statistic> data) {
                view.complete();
                if(state==0){
                    view.setViewData(data);
                }
                if(state==1){
                    view.pullRefresh(data);
                }
                if(state==2){
                    view.loadMoreListener(data);
                }
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onFail() {

            }
        });
    }

}
