package com.zt.yundan.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.yundan.R;
import com.zt.yundan.adapter.StatisticAdapter;
import com.zt.yundan.bean.DataUtil;
import com.zt.yundan.bean.Statistic;
import com.zt.yundan.databinding.ActivityNumberStatisticBinding;
import com.zt.yundan.databinding.ItemStatisticRecyclerBinding;
import com.zt.yundan.listener.PowerGroupListener;
import com.zt.yundan.view.IRecyclerView;
import com.zt.yundan.vm.LoginVM;
import com.zt.yundan.vm.StatisticVM;
import com.zt.yundan.weiget.HoverDecotation;
import com.zt.yundan.weiget.ListDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.free.sticky.StickyItemDecoration;

/**
 * 趟次统计
 *
 * @author lt
 * @time 2019/2/26 12:11
 **/
public class StatisticActivity extends MyActivity<ActivityNumberStatisticBinding> implements View.OnFocusChangeListener, View.OnClickListener, IRecyclerView<List<Statistic>> {

    private SwipeRefreshLayout statisticSwipeRefresh;
    private RecyclerView recyclerView;
    private StatisticAdapter statisticAdapter;
    private StatisticVM statisticVM;

    private int yeshu = 0;
    private int geshu = 10;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_number_statistic;
    }

    @Override
    protected int getTopView() {
        return R.id.statistic_title;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {

        if (statisticVM == null)
            statisticVM = new StatisticVM();
        statisticVM.attachView(this);

        baseBinDing.statisticAuto.setOnFocusChangeListener(this);
        baseBinDing.setIsGone(false);
        baseBinDing.statisticBack.setOnClickListener(this);

        //recyclerview
        recyclerView = baseBinDing.statisticRecycler;
        statisticSwipeRefresh = baseBinDing.statisticSwipeRefresh;

        initPullRefresh();
        initLoadMoreListener();
    }

    @Override
    protected void initData() {
        statisticVM.getStatistic(yeshu, geshu, 0);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.statistic_auto) {
            baseBinDing.setIsGone(b);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.statistic_back:
                finish();
                break;
        }
    }

    @Override
    public void setViewData(List<Statistic> statistics) {
        //最后一个参数为是否逆转布局
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new StickyItemDecoration());
        recyclerView.addItemDecoration(new ListDividerItemDecoration());
        statisticAdapter = new StatisticAdapter(statistics);
        recyclerView.setAdapter(statisticAdapter);
    }

    @Override
    public void pullRefresh(List<Statistic> statistics) {
        statisticAdapter.AddHeaderItem(statistics);
        //刷新完成
        statisticSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void loadMoreListener(List<Statistic> statistics) {
        statisticAdapter.AddFooterItem(statistics);
    }


    public void initPullRefresh() {
        statisticSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                statisticVM.getStatistic(yeshu, geshu, 1);
            }
        });
    }

    public void initLoadMoreListener() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == statisticAdapter.getItemCount()) {
                    //设置正在加载更多
                    statisticAdapter.changeMoreStatus(statisticAdapter.LOADING_MORE);
                    statisticVM.getStatistic(yeshu, geshu, 2);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }


    /**
     * 添加悬浮布局
     */
    public void initDecoration(List<Statistic> statistics) {
//        HoverDecotation decoration = HoverDecotation.Builder
//                .init()
//                //设置高度
//                .setGroupHeight(80)
//                .build();
//        recyclerView.addItemDecoration(decoration);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (statisticVM != null) {
            statisticVM.detachView();
        }
    }

}
