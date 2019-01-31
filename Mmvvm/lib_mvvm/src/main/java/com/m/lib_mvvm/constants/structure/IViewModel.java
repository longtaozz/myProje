package com.m.lib_mvvm.constants.structure;

/**
 * 通用视图接口
 * @author lt
 * @time 2019/1/28 11:27
 **/
public interface IViewModel<V extends IView> {

    //绑定view
    void attachView(V view);

    //解除绑定
    void detachView();

    //获取view
    V getIView();
}
