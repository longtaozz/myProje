package com.m.lib_mvvm.constants.structure;

import java.lang.ref.WeakReference;

/**
 * 视图操作实现
 * @author lt
 * @time 2019/1/28 11:28
 **/
public abstract class ViewModel<V extends IView> implements IViewModel {
    private WeakReference<IView> mVWeakReference;

    @Override
    public void attachView(IView view) {
        mVWeakReference = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mVWeakReference != null) {
            mVWeakReference.clear();
            mVWeakReference = null;
        }
    }

    @Override
    public IView getIView() {
        if (mVWeakReference != null) {
            return mVWeakReference.get();
        }
        return null;
    }
}
