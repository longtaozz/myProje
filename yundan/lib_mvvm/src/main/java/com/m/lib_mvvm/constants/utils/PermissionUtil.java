package com.m.lib_mvvm.constants.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.m.lib_mvvm.constants.listener.PermissionListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * 权限请求
 *
 * @author lt
 * @time 2019/2/19 15:23
 **/
public class PermissionUtil {

    /**
     * 整个程序的权限请求
     *
     * @param context
     */
    public static void Apply(Context context, final PermissionListener onListener) {
        String[] permissionsx = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,//写入
                Manifest.permission.READ_EXTERNAL_STORAGE,//读取
                android.Manifest.permission.CAMERA,//拍照
                Manifest.permission.ACCESS_FINE_LOCATION//定位
        };
        AndPermission.with(context)
                .runtime()
                .permission(permissionsx)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        onListener.onComplete();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        //拒绝
                        onListener.onComplete();
                    }
                })
                .start();
    }
}
