package com.juny.cashiersystem.realm.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <br> ClassName: OrderBean
 * <br> Description:  订单实体
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/8 14:05
 */

public class OrderBean extends RealmObject {
    /**
     * 订单号
     */
    @PrimaryKey
    private int id;

    /**
     * 订单号
     */
    private String orderNum;


    /**
     * 订单总额
     */
    private int amount;


    /**
     * 备注
     */
    private String remark="";

    /**
     * 生成订单的时间
     */
    private String date;

    /**
     * 支付方式，1为现金，2为
     */
    private int payType;

    /**
     * 购买会员
     */
    private int memberId;

    /**
     * 购买的商品
     */
    private RealmList<OrderGoodsBean> goods;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int member) {
        this.memberId = member;
    }

    public RealmList<OrderGoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(RealmList<OrderGoodsBean> goods) {
        this.goods = goods;
    }
}
