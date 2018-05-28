package com.juny.cashiersystem.business.homepage.cashiertab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.business.homepage.cashiertab.view.CategoryViewHolder;
import com.juny.cashiersystem.bean.CategoryBean;

/**
 * <br> ClassName:
 * <br> Description:  商品分类列表适配器
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/8 18:26
 */

public class CategoryListAdapter extends RecyclerArrayAdapter<CategoryBean> {
    /***布局类型***/
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    public CategoryListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(parent);
    }

    /**
     * <br> Description: 横向布局添加footer,footer显示异常，只能通过这种方式添加
     * <br> Author:      chenrunfang
     * <br> Date:        2018/4/11 12:00
     */
    @Override
    public int getViewType(int position) {
        if (position == getCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }
}
