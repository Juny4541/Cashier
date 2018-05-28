package com.juny.cashiersystem.business.formstab.bean;

/**
 * <br> ClassName: DaySumBean
 * <br> Description: 日销售情况
 * <br> Author: Juny
 * <br> Date:  2018/5/18 18:34
 */

public class DaySumBean {
    private String date;
    private int orderSumMoney;
    private int orderCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderSumMoney() {
        return orderSumMoney;
    }

    public void setOrderSumMoney(int orderSumMoney) {
        this.orderSumMoney = orderSumMoney;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
}
