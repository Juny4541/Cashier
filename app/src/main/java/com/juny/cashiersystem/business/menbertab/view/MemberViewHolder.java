package com.juny.cashiersystem.business.menbertab.view;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.util.ResourceUtil;

/**
 * <br> ClassName: MemberViewHolder
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 22:11
 */

public class MemberViewHolder extends BaseViewHolder<MemberBean> {
    private TextView mPhoneNum;
    private TextView mName;
    private RelativeLayout mRlItem;

    public MemberViewHolder(ViewGroup parent) {
        super(parent, R.layout.menber_list_item);
        mPhoneNum = $(R.id.tv_member_phone_item);
        mName = $(R.id.tv_member_name_item);
        mRlItem = $(R.id.rl_member_item);
    }

    @Override
    public void setData(MemberBean data) {
        super.setData(data);
        mPhoneNum.setText(data.getPhone());
        mName.setText(data.getName());

        //设置选中与未选中状态
        if ("true".equals(data.getSelect())) {
            mRlItem.setBackgroundColor(ResourceUtil.getColor(R.color.gray_line));
        } else {
            mRlItem.setBackgroundColor(ResourceUtil.getColor(R.color.gray_content));
        }
    }
}
