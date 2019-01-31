package com.m.mvvm.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.m.mvvm.R;
import com.m.mvvm.VM.MainViewModel;
import com.m.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends MyActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if (mainViewModel == null)
            mainViewModel = new MainViewModel();
        mainViewModel.attachView(this);
    }

    @Override
    protected void initData() {
        mainViewModel.lodStr();
    }


    //显示成功
    @Override
    public void showSuccess(String message) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainViewModel != null) {
            mainViewModel.detachView();
        }
    }
}
