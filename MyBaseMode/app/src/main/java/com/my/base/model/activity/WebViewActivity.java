package com.my.base.model.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.webkit.WebView;

import com.my.base.model.R;


/**
 * Created by Administrator on 2018-04-12.
 */

public class WebViewActivity extends BaseWebViewActivity {
    public WebView webView;
    String url = "";
    Object javaScrip = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_web_view);
        webView = findViewById(R.id.myweb);
        url = getIntent().getStringExtra("url");
        if (url == null) {
            url = "";
        }
        Log.e("webView......", "11111");
        Log.e("webView......", url);
        Log.e("webView......", "22222");
        if (Build.VERSION.SDK_INT >= 23) {
            webViewCreat(url, webView, javaScrip);
        }

    }


}
