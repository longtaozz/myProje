package com.zt.yundan.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zt.yundan.R;
import com.zt.yundan.bean.Statistic;
import com.zt.yundan.databinding.ItemStatisticRecyclerBinding;
import com.zt.yundan.databinding.LoadMoreBinding;

import java.util.List;

/**
 * 趟次统计中recycler适配器
 *
 * @author lt
 * @time 2019/2/26 13:37
 **/
public class StatisticAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private ItemStatisticRecyclerBinding recyclerBinding;
    private LoadMoreBinding loadMoreBinding;
    private List<Statistic> statistics;
    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;

    public StatisticAdapter(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    //创建View，一般是一个ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == TYPE_ITEM) {
            //创建item的databinding
            recyclerBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_statistic_recycler, viewGroup, false);
            return new MyHolder(recyclerBinding);
        } else {
            loadMoreBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.load_more, viewGroup, false);
            return new FooterViewHolder(loadMoreBinding);
        }
    }

    //每个item数据绑定与操作
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            recyclerBinding.setStatistic(statistics.get(i));
            recyclerBinding.getRoot().setTag(statistics.get(i).getLocus());
            if (statistics.get(i).getLocus()) {
                myHolder.locus_str_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //轨迹能点击
                        Toast.makeText(view.getContext(), "查看", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else if (holder instanceof FooterViewHolder) {
            loadMoreBinding.getRoot().setTag(false);
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    loadMoreBinding.setStr("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    loadMoreBinding.setStr("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    loadMoreBinding.loadLayout.setVisibility(View.GONE);
                    break;

            }
        }

    }


    @Override
    public int getItemCount() {
        return statistics == null ? 0 : statistics.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    //下拉刷新
    public void AddHeaderItem(List<Statistic> items) {
        statistics=items;
        notifyDataSetChanged();
    }

    //上拉加载
    public void AddFooterItem(List<Statistic> items) {
        statistics.addAll(items);
        notifyDataSetChanged();
    }

    //更新加载更多状态
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public TextView locus_str_click;

        public MyHolder(ItemStatisticRecyclerBinding recyclerBinding) {
            super(recyclerBinding.getRoot());
            locus_str_click = recyclerBinding.locusStrClick;
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout loadLayout;

        public FooterViewHolder(LoadMoreBinding loadMoreBinding) {
            super(loadMoreBinding.getRoot());
            loadLayout = loadMoreBinding.loadLayout;
        }
    }
}
