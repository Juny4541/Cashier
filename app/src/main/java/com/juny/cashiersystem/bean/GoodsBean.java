package com.juny.cashiersystem.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <br> ClassName: GoodsBean
 * <br> Description: 商品实体类
 * <br>
 * <br> Author: chenrunfang
 * <br> Date:  2018/4/8 18:16
 */

public class GoodsBean extends RealmObject {
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
    private int repertory;

    /**
     * 商品分类
     */
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getRepertory() {
        return repertory;
    }

    public void setRepertory(int repertory) {
        this.repertory = repertory;
    }

}
