package com.juny.cashiersystem.business.ordertab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.business.ordertab.view.OrderListViewHolder;

/**
 *<br> Description: OrderListAdapter 列表适配器
 *<br> Author: chenrunfang
 *<br> Date: 2018/4/10 10:32
 */
public class OrderListAdapter extends RecyclerArrayAdapter<OrderBean> {

    public OrderListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderListViewHolder(parent);
    }
}