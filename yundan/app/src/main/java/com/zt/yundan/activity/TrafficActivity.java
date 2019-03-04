package com.zt.yundan.activity;

import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.m.lib_mvvm.constants.middle.MyActivity;
import com.zt.capacity.baidumap.data.LocationData;
import com.zt.capacity.baidumap.utils.MapOperation;
import com.zt.yundan.R;
import com.zt.yundan.bean.Title;
import com.zt.yundan.databinding.ActivityTrafficBinding;

/**
 * 路况信息
 *
 * @author lt
 * @time 2019/2/25 13:24
 **/
public class TrafficActivity extends MyActivity<ActivityTrafficBinding> implements View.OnClickListener {
    private BaiduMap baiduMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traffic;
    }

    @Override
    protected int getTopView() {
        return R.id.include_title;
    }

    @Override
    protected boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected void initView() {
        baiduMap = baseBinDing.mBaiduMap.getMap();
        MapOperation.hideBaiDuIcon(baseBinDing.mBaiduMap);//隐藏百度图标

        Title title = new Title();
        title.setCenterName("路况信息");
        baseBinDing.setTitle(title);
        baseBinDing.includeTitle.back.setOnClickListener(this);

        baiduMap.setTrafficEnabled(true);//开启路况
        if (LocationData.point != null) {
            MapOperation.setCenterPositionAndZoom(baiduMap, LocationData.point, 13);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
