package com.zt.yundan.listener;

import android.view.View;
/**
 * view信息接口
 * @author lt
 * @time 2019/2/27 15:35
 *
 **/
public interface PowerGroupListener {
    String getGroupName(int position);

    View getGroupView(int position);
}
