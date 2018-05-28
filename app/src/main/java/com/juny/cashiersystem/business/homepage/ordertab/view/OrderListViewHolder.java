package com.juny.cashiersystem.business.homepage.ordertab.view;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.business.homepage.cashiertab.model.CashierRepository;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.util.ResourceUtil;

/**
 * <br> ClassName:
 * <br> Description:  订单列表的ViewHolder,j继承EasyRecyclerView 的BaseViewHolder
 * https://github.com/Jude95/EasyRecyclerView
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 9:54
 */
public class OrderListViewHolder extends BaseViewHolder<OrderBean> {
    private TextView mDate;
    private TextView mOrderNum;
    private TextView mAmount;
    private TextView mMember;
    private TextView mRemark;
    private LinearLayout mLlOrderItem;

    public OrderListViewHolder(ViewGroup parent) {
        super(parent, R.layout.order_orders_list_item);
        mDate = $(R.id.tv_order_date);
        mOrderNum = $(R.id.tv_order_num);
        mAmount = $(R.id.tv_order_amout);
        mMember = $(R.id.tv_order_member_name);
        mRemark = $(R.id.tv_order_remark);
        mLlOrderItem = $(R.id.ll_order_item);
    }

    /**
     * <br> Description: 实现setData来实现UI修改。Adapter会在onCreateViewHolder里自动调用。
     * 在一些特殊情况下，只能在setData里设置监听。
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 10:26
     */
    @Override
    public void setData(OrderBean data) {
        super.setData(data);
        mDate.setText(data.getDate());
        mOrderNum.setText(data.getOrderNum());
        mAmount.setText(String.valueOf(data.getAmount()));
        mRemark.setText(data.getRemark());
        if (data.getMemberId() != 0){
            mMember.setText(new CashierRepository().searchMemberById(data.getMemberId()).getName());
        }else {
            mMember.setText("");
        }

        //设置选中与未选中状态
        if ("true".equals(data.getSelect())) {
            mLlOrderItem.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
        } else {
            mLlOrderItem.setBackgroundColor(ResourceUtil.getColor(R.color.gray_content));
        }
    }
}