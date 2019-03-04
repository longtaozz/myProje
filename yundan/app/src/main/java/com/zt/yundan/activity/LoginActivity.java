package com.zt.yundan.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;

import com.m.lib_mvvm.constants.base.BaseApplication;
import com.m.lib_mvvm.constants.listener.PermissionListener;
import com.m.lib_mvvm.constants.middle.MyActivity;
import com.m.lib_mvvm.constants.utils.PermissionUtil;
import com.zt.capacity.baidumap.utils.LocationUtil;
import com.zt.yundan.R;
import com.zt.yundan.bean.User;
import com.zt.yundan.databinding.ActivityLoginBinding;
import com.zt.yundan.vm.LoginVM;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @author lt
 * @time 2019/2/20 15:09
 **/
public class LoginActivity extends MyActivity<ActivityLoginBinding> implements View.OnClickListener {

    public static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String account;
    private String password;

    private User user;

    private boolean isSelect = false;//记住密码是否选中

    private LoginVM loginVM;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getTopView() {
        return R.id.login_register;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {

        PermissionUtil.Apply(this, new PermissionListener() {
            @Override
            public void onComplete() {
                //进行一次定位
                LocationUtil.getInit(LoginActivity.this).locationOne();
            }
        });
        if (loginVM == null)
            loginVM = new LoginVM();
        loginVM.attachView(this);

        baseBinDing.loginSubmit.setOnClickListener(this);
        baseBinDing.loginSelect.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        sharedPreferences = getSharedPreferences("userInfo",
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        account = sharedPreferences.getString("account", null);
        password = sharedPreferences.getString("password", null);
        if (password != null || !TextUtils.isEmpty(password)) {
            isSelect=true;
        }
        baseBinDing.loginSelect.setSelected(isSelect);
        user = new User();
        user.setUsername(account);
        user.setPassword(password);
        baseBinDing.setUser(user);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_submit:
                //登录

                String userName = baseBinDing.loginUserName.getText().toString();
                String userPassword = baseBinDing.loginUserPassword.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    toast("请输入帐号！");
                    return;
                }
                if (TextUtils.isEmpty(userPassword)) {
                    toast("请输入密码！");
                    return;
                }
                //储存帐号
                editor.putString("account", userName);
                if (isSelect) {
                    editor.putString("password", userPassword);
                } else {
                    editor.putString("password", "");
                }
                editor.commit();

                //登录
                Map<String,Object> map=new HashMap<>();
                map.put("username",userName);
                map.put("password",userPassword);
                loginVM.login(map);
                break;
            case R.id.login_select:
                //记住密码
                if (isSelect) {
                    isSelect = false;
                    baseBinDing.loginSelect.setSelected(isSelect);
                } else {
                    isSelect = true;
                    baseBinDing.loginSelect.setSelected(isSelect);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginVM != null) {
            loginVM.detachView();
        }
    }
}
