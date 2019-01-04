package com.my.base.model.activity;

import android.view.View;

import com.app.lib_common.structure.PActivity;
import com.my.base.model.R;
import com.my.base.model.bean.CopyUser;
import com.my.base.model.contract.CopyUserContract;
import com.my.base.model.presenter.CopyUserPresenter;


/**
 * 模版activity
 *
 * @author lt
 * @time 2018/12/11 13:33
 **/
public class CopyUserActivity extends PActivity<CopyUserPresenter> implements CopyUserContract.View {

    @Override
    protected CopyUserPresenter initPresenter() {
        return new CopyUserPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_login_title;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void onLogin(View view) {
        // 登录操作
        getPresenter().login("账户", "密码");
    }

    @Override
    public void loginError(String msg) {
        //登录失败
        toast("登录失败");
    }

    @Override
    public void loginSuccess(CopyUser data) {
        //登录成功
    }

    @Override
    public void showLoading() {
        //加载条
        showProgressDialog();
    }

    @Override
    public void showEmpty() {
        //结果为空
    }

    @Override
    public void showError() {
        //出错
        toast("服务器错误");
    }

    @Override
    public void loadingComplete() {
        //加载完成
        cancelProgressDialog();
    }
}