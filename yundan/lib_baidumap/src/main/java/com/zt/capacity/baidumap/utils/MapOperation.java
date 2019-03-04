package com.zt.capacity.baidumap.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.zt.capacity.baidumap.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 地图工具类
 * @author lt
 * @time 2019/2/19 14:09
 *
 **/

public class MapOperation {

    //隐藏百度图标
    public static void hideBaiDuIcon(MapView mMapView) {
        int count = mMapView.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = mMapView.getChildAt(i);
            if (child instanceof ZoomControls || child instanceof ImageView) {
                child.setVisibility(View.INVISIBLE);
            }
        }
    }

    //设置地图中心位置
    public static void setCenterPosition(BaiduMap mBaiduMap, LatLng point) {
//        LatLng cenpt = new LatLng(29.806651,121.606983);
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(point);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        //设置比例
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(
                new MapStatus.Builder().zoom(14).build()));
    }

    //设置地图中心位置
    public static void setCenterPositionAndZoom(BaiduMap mBaiduMap, LatLng point, float zoom) {
//        LatLng cenpt = new LatLng(29.806651,121.606983);
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLng(point);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

        //设置比例
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(
                new MapStatus.Builder().zoom(zoom).build()));
    }

    //View转bitmap
    public static Bitmap viewToBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();

        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }


    /**
     * 图片旋转
     *
     * @param origin 原图
     * @param alpha  旋转角度，可正可负
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 绘制多边形
     *
     * @param mBaiduMap
     * @param latLngs
     */
    public static void addCustomElementsDemo(BaiduMap mBaiduMap, List<LatLng> latLngs) {

        List<LatLng> pts = new ArrayList<LatLng>();
        for (int i = 0; i < latLngs.size(); i++) {
            LatLng pt1 = TransformationUtil.gpsToBaiduCoordinate(latLngs.get(i));
            pts.add(pt1);
        }
        // 添加多边形
        OverlayOptions ooPolygon = new PolygonOptions().points(pts)
                .stroke(new Stroke(5, 0xAA00FF00)).fillColor(0xAAFFFF00);
        mBaiduMap.addOverlay(ooPolygon);
    }


    /**
     * 绘制多边形
     *
     * @param mBaiduMap
     * @param latLngs
     */
    public static Overlay addCustomElementsDemoReturn(BaiduMap mBaiduMap, List<LatLng> latLngs) {

        List<LatLng> pts = new ArrayList<LatLng>();
        for (int i = 0; i < latLngs.size(); i++) {
            LatLng pt1 = TransformationUtil.gpsToBaiduCoordinate(latLngs.get(i));
            pts.add(pt1);
        }
        // 添加多边形
        OverlayOptions ooPolygon = new PolygonOptions().points(pts)
                .stroke(new Stroke(5, 0xAA00FF00)).fillColor(0xAAFFFF00);
        return mBaiduMap.addOverlay(ooPolygon);
    }

    /**
     * 绘制线
     *
     * @param mBaiduMap
     * @param latLngs
     */
    public static void addCustomXian(BaiduMap mBaiduMap, List<LatLng> latLngs) {
        if (latLngs.size() < 2) {
            return;
        }
        List<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < latLngs.size(); i++) {
            Log.e("getGpsPosY", latLngs.get(i).latitude + "w" + latLngs.get(i).longitude);
            LatLng p = TransformationUtil.gpsToBaiduCoordinate(new LatLng(latLngs.get(i).latitude,
                    latLngs.get(i).longitude));
            points.add(p);
        }

        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .color(0xAAFF0000).points(points);
        mBaiduMap.addOverlay(ooPolyline);
    }

    /**
     * 有超速的线
     *
     * @param mBaiduMap
     * @param latLngs：坐标集合
     */
    public static void addCustomXianChao(BaiduMap mBaiduMap, List<LatLng> latLngs, Integer type) {
        if (latLngs.size() < 2) {
            return;
        }
        // 构造折线点坐标
        List<LatLng> points = new ArrayList<LatLng>();
        //构建分段颜色索引数组
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < latLngs.size(); i++) {
            Log.e("getGpsPosY", latLngs.get(i).latitude + "w" + latLngs.get(i).longitude);
            LatLng p = TransformationUtil.gpsToBaiduCoordinate(new LatLng(latLngs.get(i).latitude,
                    latLngs.get(i).longitude));
            points.add(p);
            if (type == 1) {
                //超速绘制红色的线
                colors.add(Integer.valueOf(Color.RED));
            } else {
                colors.add(Integer.valueOf(Color.BLUE));
            }
        }


        OverlayOptions ooPolyline = new PolylineOptions().width(10)
                .colorsValues(colors).points(points);

        //添加在地图中
        mBaiduMap.addOverlay(ooPolyline);

    }

    /**
     * 绘制圆形
     *
     * @param mBaiduMap
     * @param centLatLng：中心点
     * @param raidus：半径
     */
    public static void drawCircular(BaiduMap mBaiduMap, LatLng centLatLng, Integer raidus) {
        //设置颜色和透明度，均使用16进制显示，0xAARRGGBB，如 0xAA000000 其中AA是透明度，000000为颜色
        OverlayOptions ooCircle = new CircleOptions().fillColor(R.color.color5)
                .center(centLatLng).stroke(new Stroke(3, R.color.xians))
                .radius(raidus);
        mBaiduMap.addOverlay(ooCircle);
    }

    /**
     * 获得图形的中心点
     */
    public static LatLng getCenterPoint(List<LatLng> latLngs) {
        //var path = e.;//Array<Point> 返回多边型的点数组
        //var ret=parseFloat(num1)+parseFloat(num2);
        double x = 0.0;
        double y = 0.0;
        for (int i = 0; i < latLngs.size(); i++) {
            x = x + latLngs.get(i).longitude;
            y = y + latLngs.get(i).latitude;
        }
        x = x / latLngs.size();
        y = y / latLngs.size();
        return new LatLng(y, x);
    }


    /**
     * LatLngs获得图形中心点
     *
     * @param latLngs
     * @return
     */
    public static LatLng getCenterLatLng(List<LatLng> latLngs) {
        List<LatLng> latLngs1 = new ArrayList<LatLng>();
        for (int i = 0; i < latLngs.size(); i++) {
            latLngs1.add(new LatLng(latLngs.get(i).latitude, latLngs.get(i).longitude));
        }
        LatLng latLng = getCenterPoint(latLngs1);
        return latLng;
    }

    //在地图上添加Marker
    public static void addMarker(BaiduMap mBaiduMap, LatLng point, Bundle bundle, Bitmap bitmap) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        MarkerOptions options = new MarkerOptions()
                .position(point)//设置位置
//                .rotate(carRuntime.getGpsDirect())//设置方向
                .icon(bitmapDescriptor)//设置图标样式
                .zIndex(9) // 设置marker所在层级
                .draggable(false); // 设置手势拖拽;
        //添加marker
        Marker marker = (Marker) mBaiduMap.addOverlay(options);
        marker.setExtraInfo(bundle);

    }

    /**
     * 添加有方向的maker
     *
     * @param mBaiduMap
     * @param point     坐标
     * @param bundle    储存的数据
     * @param bitmap    图标
     * @param rotate    旋转角度
     * @return
     */
    public static Marker addMarkerByRotate(BaiduMap mBaiduMap, LatLng point, Bundle bundle, Bitmap bitmap, Float rotate) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        MarkerOptions options = new MarkerOptions()
                .position(point)//设置位置
                .rotate(rotate)//设置方向
                .icon(bitmapDescriptor)//设置图标样式
                .zIndex(9) // 设置marker所在层级
                .draggable(false); // 设置手势拖拽;
        //添加marker
        Marker marker = (Marker) mBaiduMap.addOverlay(options);
        marker.setExtraInfo(bundle);
        return marker;

    }


}
