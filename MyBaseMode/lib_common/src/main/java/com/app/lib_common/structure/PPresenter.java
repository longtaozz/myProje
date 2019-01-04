package com.app.lib_common.structure;

/**
 *    业务基类
 */
public abstract class PPresenter<V> {

    private V mView;

    //绑定视图
    public void attach(V view){
        mView = view;
    }

    //解除绑定
    public void detach() {
        mView = null;
    }

    //是否绑定
    public boolean isAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public abstract void start();
}