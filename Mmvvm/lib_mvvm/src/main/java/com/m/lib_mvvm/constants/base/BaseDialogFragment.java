package com.m.lib_mvvm.constants.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

/**
 *  DialogFragment 基类
 */
public abstract class BaseDialogFragment extends DialogFragment {

    /**
     * 父类同名方法简化
     */
    public void show(FragmentActivity activity) {
        show(activity.getSupportFragmentManager(), getClass().getName());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // 不使用 Dialog，替换成 BaseDialog 对象
        return new BaseDialog(getActivity());
    }
}