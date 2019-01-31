package com.m.lib_mvvm.constants.structure;

/**
 * 通用view操作接口
 * @author lt
 * @time 2019/1/28 11:27
 **/
public interface IView {
    // 显示错误提示
    void showError(String message);

    //显示成功提示
    void showSuccess(String message);

    //显示正在加载
    void showLoading();

    //请求完成
    void complete();
}
