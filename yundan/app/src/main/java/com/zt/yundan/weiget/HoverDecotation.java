package com.zt.yundan.weiget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.zt.yundan.bean.Statistic;
import com.zt.yundan.listener.PowerGroupListener;

import java.util.List;

public class HoverDecotation extends RecyclerView.ItemDecoration {

//    private static final String TAG = "SectionDecoration";
//
//    private List<Statistic> statistics;
//    /**
//     * 悬浮栏高度
//     */
//    private int mGroupHeight = 80;
//    /**
//     * 是否靠左边
//     */
//    private boolean isAlignLeft = true;
//
//    private HoverDecotation(List<Statistic> statistics) {
//        this.statistics = statistics;
//    }
//
//
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
//        int pos = parent.getChildAdapterPosition(view);
//        //只有是同一组的第一个才显示悬浮栏
//        if (pos == 0 || isFirstInGroup(pos)) {
//            outRect.top = mGroupHeight;
//        }
//
//    }
//
//    @Override
//    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDrawOver(c, parent, state);
//        //获取单条的数目
//        int itemCount = state.getItemCount();
//        int childCount = parent.getChildCount();
//        //得出距离左边和右边的padding
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        //开始绘制
//        String preGroupName;
//        String currentGroupName = null;
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(view);
//            if (!statistics.get(i).getLocus()) {
//                continue;
//            }
//            int viewBottom = view.getBottom();
//            //top 决定当前顶部第一个悬浮Group的位置
//            int top = Math.max(mGroupHeight, view.getTop());
//            if (position + 1 < itemCount) {
//                //获取下个GroupName
//                String nextGroupName = getGroupName(position + 1);
//                //下一组的第一个View接近头部
//                if (!statistics.get(i).getLocus() && viewBottom < top) {
//                    top = viewBottom;
//                }
//            }
//
//            //根据position获取View
//            View groupView = getGroupView(position);
//            if (groupView == null) {
//                return;
//            }
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mGroupHeight);
//            groupView.setLayoutParams(layoutParams);
//            groupView.setDrawingCacheEnabled(true);
//            groupView.measure(
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            //指定高度、宽度的groupView
//            groupView.layout(0, 0, right, mGroupHeight);
//            groupView.buildDrawingCache();
//            Bitmap bitmap = groupView.getDrawingCache();
//            int marginLeft = isAlignLeft ? 0 : right - groupView.getMeasuredWidth();
//            c.drawBitmap(bitmap, left + marginLeft, top - mGroupHeight, null);
//        }
//    }
//
//    /**
//     * 判断是不是组中的第一个位置
//     * 根据前一个组名，判断当前是否为新的组
//     */
//    private boolean isFirstInGroup(int pos) {
//        if (pos == 0) {
//            return true;
//        } else {
//            return statistics.get(pos).getLocus();
//        }
//    }
//
//
//    public static class Builder {
//        HoverDecotation hoverDecotation;
//
//        private Builder(List<Statistic> statistics) {
//            hoverDecotation = new HoverDecotation(statistics);
//        }
//
//        /**
//         * 初始化 listener
//         *
//         * @return
//         */
//        public static Builder init(List<Statistic> statistics) {
//            return new Builder(statistics);
//        }
//
//        /**
//         * 设置Group高度
//         *
//         * @param groutHeight 高度
//         * @return this
//         */
//        public Builder setGroupHeight(int groutHeight) {
//            hoverDecotation.mGroupHeight = groutHeight;
//            return this;
//        }
//
//        /**
//         * 是否靠左边
//         * true 靠左边（默认）、false 靠右边
//         *
//         * @param b b
//         * @return this
//         */
//        public Builder isAlignLeft(boolean b) {
//            hoverDecotation.isAlignLeft = b;
//            return this;
//        }
//
//        public HoverDecotation build() {
//            return hoverDecotation;
//        }
//    }
}
