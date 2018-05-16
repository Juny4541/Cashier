package com.juny.cashiersystem.realm.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <br> ClassName:
 * <br> Description:  商品类别
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/8 22:09
 */

public class CategoryBean extends RealmObject {

    @PrimaryKey
    private int id;

    private String categoryName;

    private String isSelect;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSelect() {
        return isSelect;
    }

    public void setSelect(String select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
