package com.app.lib_common.structure;


import com.app.lib_common.middle.MyActivity;

/**
 *  MVP Activity 基类
 */
public abstract class PActivity<P extends PPresenter> extends MyActivity {

    private P mPresenter;

    @Override
    public void init() {
        mPresenter = initPresenter();
        mPresenter.attach(this);
        mPresenter.start();
        super.init();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P initPresenter();
}