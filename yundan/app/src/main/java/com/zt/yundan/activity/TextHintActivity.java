package com.zt.yundan.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.yundan.R;


/**
 * 文字提示界面
 */
public class TextHintActivity extends MyActivity implements View.OnClickListener {
    //头布局
    private ImageView title_img;//返回
    private TextView title_name;//标题

    private TextView hint_text_str;//提示文字

    private String textStr;//提示文字
    private String textTitle = "信息";//提示标题  默认提示


    @Override
    protected int getLayoutId() {
        return R.layout.activity_hint;
    }

    @Override
    protected int getTopView() {
        return R.id.ll_head;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {
        title_name = findViewById(R.id.title_name);
        hint_text_str = findViewById(R.id.hint_text_str);
        title_img = findViewById(R.id.title_img);
        title_img.setOnClickListener(this);
    }

    @Override
    protected void initData() {


        String textTitlex = getIntent().getStringExtra("textTitle");
        textStr = getIntent().getStringExtra("textStr");
        if (textTitlex != null && !TextUtils.isEmpty(textTitlex)) {
            textTitle = textTitlex;
        }

        title_name.setText(textTitle);
        hint_text_str.setText(textStr == null ? "" : textStr);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_img:
                finish();
                break;
        }
    }
}
