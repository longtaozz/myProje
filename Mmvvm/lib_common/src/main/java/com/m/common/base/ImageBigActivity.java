package com.m.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.m.common.R;

/**
 * 查看大图
 *
 * @author lt
 * @time 2018/12/20 9:44
 **/
public class ImageBigActivity extends Activity {
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);
        imgView = findViewById(R.id.imgView);
        String imgurl = getIntent().getStringExtra("imgUrl");

        Log.e("imgurl....",imgurl);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.preloading_images)
                .error(R.drawable.preloading_images)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(this).load(imgurl).apply(options).into(imgView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finish();
        return true;
    }

}
