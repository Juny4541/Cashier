package com.juny.cashiersystem.business.homepage.formstab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.business.homepage.formstab.bean.AnalyzeBean;
import com.juny.cashiersystem.business.homepage.formstab.view.AnalyzeListViewHolder;

/**
 * <br> Description: AnalyzeList Adapter 列表适配器
 * <br> Author: chenrunfang
 * <br> Date: 2018/4/10 10:32
 */
public class AnalyzeListAdapter extends RecyclerArrayAdapter<AnalyzeBean> {

    public AnalyzeListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnalyzeListViewHolder(parent);
    }
}