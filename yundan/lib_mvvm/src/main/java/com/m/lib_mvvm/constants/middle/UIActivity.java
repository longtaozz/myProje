package com.m.lib_mvvm.constants.middle;

import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.m.lib_mvvm.R;
import com.m.lib_mvvm.constants.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;


/**
 * 支持沉浸式和侧滑的Activity基类（默认开启沉浸式状态栏和侧滑功能）
 */
public abstract class UIActivity<T> extends BaseActivity<T>
        implements BGASwipeBackHelper.Delegate, ViewTreeObserver.OnGlobalLayoutListener {

    private BGASwipeBackHelper mSwipeBackHelper;//侧滑返回

    @Override
    public void init() {

        //初始化沉浸式状态栏
        if (isStatusBarEnabled()) {
            ImmersionBar.with(this).statusBarDarkFont(statusBarDarkFont()).init();
        }
        initSwipeBackFinish(isSupportSwipeBack());
        super.init();
    }

    public BGASwipeBackHelper getSwipeBackHelper() {
        return mSwipeBackHelper;
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     *
     * @param swipeBackEnable 是否开启侧滑返回
     */
    private void initSwipeBackFinish(Boolean swipeBackEnable) {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(swipeBackEnable);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * {@link BGASwipeBackHelper.Delegate}
     */

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     */
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }


    /**
     * 是否使用沉浸式状态栏
     */
    public boolean isStatusBarEnabled() {
        return true;
    }


    /**
     * {@link ViewTreeObserver.OnGlobalLayoutListener}
     */
    @Override
    public void onGlobalLayout() {
    }//不用写任何方法




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }
}