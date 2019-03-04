package com.zt.yundan.view;


import com.m.lib_mvvm.constants.structure.IView;

public interface IRecyclerView<T> extends IView {
    void setViewData(T t);

    //下拉刷新
    void pullRefresh(T t);

    //上拉加载
    void loadMoreListener(T t);

    void initDecoration(T t);
}
