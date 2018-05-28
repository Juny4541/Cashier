package com.juny.cashiersystem.business.homepage.formstab.view;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.business.homepage.formstab.bean.AnalyzeBean;

/**
 * <br> ClassName:
 * <br> Description:  表格模块中的运营分析列表ViewHolder
 * https://github.com/Jude95/EasyRecyclerView
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 9:54
 */
public class AnalyzeListViewHolder extends BaseViewHolder<AnalyzeBean> {
    private TextView mDate;
    private TextView mSum;
    private TextView mCount;


    public AnalyzeListViewHolder(ViewGroup parent) {
        super(parent, R.layout.forms_analyze_list_item);
        mDate = $(R.id.tv_forms_analyze_date);
        mSum = $(R.id.tv_forms_analyze_sum);
        mCount = $(R.id.tv_forms_analyze_count);
    }

    /**
     * <br> Description: 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
     * 在一些特殊情况下，只能在setData里设置监听。
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 10:26
     */
    @Override
    public void setData(AnalyzeBean data) {
        super.setData(data);
        mDate.setText(data.getDate());
        mSum.setText(String.valueOf(data.getSum()));
        mCount.setText(String.valueOf(data.getCount()));
    }
}