package com.app.lib_common.structure;


import com.app.lib_common.middle.MyLazyFragment;

/**
 *  懒加载 Fragment 基类
 */
public abstract class PLazyFragment<P extends PPresenter> extends MyLazyFragment {

    private P mPresenter;

    @Override
    protected void init() {
        mPresenter = initPresenter();
        mPresenter.attach(this);
        mPresenter.start();
        super.init();
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P initPresenter();
}