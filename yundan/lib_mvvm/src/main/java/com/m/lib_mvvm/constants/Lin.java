package com.m.lib_mvvm.constants;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class Lin extends LinearLayout {
    public Lin(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    public static void main(String[] args) {
        Comparable c = null;
        Comparable[] x = new Comparable[3];
        int f=countGreaterThan(x,x[0]);
        System.out.print(f);
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)
                ++count;
        return count;
    }

}

interface Comparable<T>{
    int compareTo(T o);
}


