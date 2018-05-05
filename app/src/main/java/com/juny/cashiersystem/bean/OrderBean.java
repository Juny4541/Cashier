package com.juny.cashiersystem.bean;

/**
 *
 * Created by Junny on 2018/3/6.
 */

public class OrderBean {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 购买会员
     */
    private String memberBuyer;

    /**
     * 订单总额
     */
    private int amount;

    /**
     * 生成订单的日期
     */
    private String date;


    /**
     * 商品Id
     */
    private String goodsID;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品数量
     */
    private int account;

    /**
     * 商品价格
     */
    private int price;

    /**
     *  在订单列表中是否被选中
     */
    private boolean isSelected;

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMemberBuyer() {
        return memberBuyer;
    }

    public void setMemberBuyer(String memberBuyer) {
        this.memberBuyer = memberBuyer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
