package com.m.lib_mvvm.constants;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

public class ReAdpter extends RecyclerView.Adapter<ReAdpter.MyHolder> {
    String[] x;
    public ReAdpter(String[] s) {
        x=s;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //创建返回ViewHolder
        MyHolder myHolder=new MyHolder(null);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        //itme操作
    }

    @Override
    public int getItemCount() {
        return x.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
