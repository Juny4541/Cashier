package com.juny.cashiersystem.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <br> ClassName: OrderGoodsBean
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/13 8:56
 */

public class OrderGoodsBean extends RealmObject {


    @PrimaryKey
    private int id;
    /**
     * 商品
     */
    private int goodsId;
    /**
     * 购买数量
     */
    private int count;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
