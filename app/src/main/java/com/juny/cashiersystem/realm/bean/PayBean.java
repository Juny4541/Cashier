package com.juny.cashiersystem.realm.bean;

/**
 * <br> ClassName: PayBean
 * <br> Description:  支付记录，从订单数据库中查询
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/8 14:30
 */

public class PayBean {
    /**
     * 支出
     */
    private int money;

    /**
     * 支付时间
     */
    private String time;

    /**
     * 订单号
     */
    private int orderNum;


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
