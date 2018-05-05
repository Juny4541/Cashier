package com.juny.cashiersystem.bean;

/**
 * <br> ClassName: GoodsBean
 * <br> Description: 商品实体类
 * <br>
 * <br> Author: chenrunfang
 * <br> Date:  2018/4/8 18:16
 */

public class GoodsBean {
    /**
     * 商品
     */
    private String goodsID;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private int price;

    /**
     * 库存数量
     */
    private int inventory;


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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
