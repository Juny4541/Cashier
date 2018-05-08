package com.juny.cashiersystem.realm.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * <br> ClassName: GoodsBean
 * <br> Description: 商品实体类
 * <br>
 * <br> Author: chenrunfang
 * <br> Date:  2018/4/8 18:16
 */

public class GoodsBean  extends RealmObject{
    /**
     * 商品
     */
    @PrimaryKey
    private int id;

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

    /**
     * 商品分类
     */
    private CategoryBean category;


    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
