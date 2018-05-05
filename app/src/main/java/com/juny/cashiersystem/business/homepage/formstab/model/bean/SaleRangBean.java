package com.juny.cashiersystem.business.homepage.formstab.model.bean;

/**
 * <br> ClassName:
 * <br> Description:  销售排行列表实体类
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 16:51
 */

public class SaleRangBean {
    private String goodsName;
    private int saleCount;
    private int saleSum;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getSaleSum() {
        return saleSum;
    }

    public void setSaleSum(int saleSum) {
        this.saleSum = saleSum;
    }
}
