package com.juny.cashiersystem.business.homepage.cashiertab.view;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.realm.bean.GoodsBean;

/**
 * <br> ClassName:   CashierFragment
 * <br> Description: 商品列表的ViewHolder
 * <br>
 * <br> Author:      Chenrunfang
 * <br> Date:        2018/4/11 9:34
 */
public class GoodsViewHolder extends BaseViewHolder<GoodsBean> {
    private TextView inventory;
    private TextView name;
    private TextView price;

    public GoodsViewHolder(ViewGroup parent) {
        super(parent, R.layout.cashier_goods_list_item);
        inventory = $(R.id.tv_goods_inventory);
        name = $(R.id.tv_goods_name);
        price = $(R.id.tv_goods_price);
    }

    @Override
    public void setData(GoodsBean goodBean) {
        super.setData(goodBean);
        name.setText(goodBean.getName());
        price.setText(String.valueOf(goodBean.getPrice()));
        inventory.setText(String.valueOf(goodBean.getInventory()));
    }
}
