package com.juny.cashiersystem.business.formstab.view;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.business.formstab.bean.SaleRangBean;

/**
 * <br> ClassName:
 * <br> Description:  表格模块中的销售排行列表ViewHolder
 * https://github.com/Jude95/EasyRecyclerView
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 9:54
 */
public class SaleRangViewHolder extends BaseViewHolder<SaleRangBean> {
    private TextView mName;
    private TextView mSaleCount;
    private TextView mSaleSum;


    public SaleRangViewHolder(ViewGroup parent) {
        super(parent, R.layout.forms_analyze_list_item); // 复用运营分析列表中的 List Item,忽略ID的命名
        mName = $(R.id.tv_forms_analyze_date);
        mSaleCount = $(R.id.tv_forms_analyze_sum);
        mSaleSum = $(R.id.tv_forms_analyze_count);
    }

    /**
     * <br> Description: 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
     * 在一些特殊情况下，只能在setData里设置监听。
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 10:26
     */
    @Override
    public void setData(SaleRangBean data) {
        super.setData(data);
        mName.setText(data.getGoodsName());
        mSaleCount.setText(String.valueOf(data.getSaleCount()));
        mSaleSum.setText(String.valueOf(data.getSaleSum()));
    }
}