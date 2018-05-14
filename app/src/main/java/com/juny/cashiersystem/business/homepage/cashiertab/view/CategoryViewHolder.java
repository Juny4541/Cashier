package com.juny.cashiersystem.business.homepage.cashiertab.view;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.realm.bean.CategorySimpleBean;
import com.juny.cashiersystem.util.ResourceUtil;

/**
 * <br> ClassName: CategoryViewHolder
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 9:48
 */

public class CategoryViewHolder extends BaseViewHolder<CategorySimpleBean> {
    private TextView mTvCategory;

    public CategoryViewHolder(ViewGroup parent) {
        super(parent, R.layout.cashier_categoy_list_item);
        mTvCategory = $(R.id.tv_category_name);
    }

    @Override
    public void setData(CategorySimpleBean data) {
        super.setData(data);
        if (data.isSelect()) {
            mTvCategory.setBackgroundColor(ResourceUtil.getColor(R.color.theme_red));
            mTvCategory.setTextColor(Color.WHITE);
        } else {
            mTvCategory.setBackgroundColor(ResourceUtil.getColor(R.color.gray_content));
            mTvCategory.setTextColor(Color.DKGRAY);
        }
        mTvCategory.setText(data.getCategoryName());
    }
}
