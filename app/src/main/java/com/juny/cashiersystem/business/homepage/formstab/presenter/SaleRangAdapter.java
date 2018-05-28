package com.juny.cashiersystem.business.homepage.formstab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.business.homepage.formstab.bean.SaleRangBean;
import com.juny.cashiersystem.business.homepage.formstab.view.SaleRangViewHolder;

/**
 * <br> Description:  销售排行列表 列表适配器
 * <br> Author: chenrunfang
 * <br> Date: 2018/4/10 10:32
 */
public class SaleRangAdapter extends RecyclerArrayAdapter<SaleRangBean> {

    public SaleRangAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaleRangViewHolder(parent);
    }
}