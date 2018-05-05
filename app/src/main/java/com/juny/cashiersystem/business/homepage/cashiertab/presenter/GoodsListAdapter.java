package com.juny.cashiersystem.business.homepage.cashiertab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.bean.GoodsBean;
import com.juny.cashiersystem.business.homepage.cashiertab.view.GoodsViewHolder;

/**
 * <br> ClassName:
 * <br> Description: 商品列表适配器
 * <br>
 * <br> Author: chenrunfang
 * <br> Date:  2018/4/8 18:15
 */

public class GoodsListAdapter extends RecyclerArrayAdapter<GoodsBean> {

    public GoodsListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(parent);
    }
}