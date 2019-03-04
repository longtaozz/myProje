package com.m.lib_mvvm.constants.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.hjq.umeng.UmengHelper;


/**
 *
 * android与js通信
 *
 **/

public class BaseJavaScript {

    public Context context;

    public BaseJavaScript(Context c) {
        context = c;
    }

    /**
     * 关掉当前activity
     */
    @JavascriptInterface
    public void backActivity() {
        ((Activity) context).finish();
    }



    /**
     * 友盟统计
     * @param eventName
     */
    @JavascriptInterface
    public void umengStatistics(String eventName){
        Log.e("eventName",eventName);
        UmengHelper.onEvent(context.getApplicationContext(),eventName);
    }
}
