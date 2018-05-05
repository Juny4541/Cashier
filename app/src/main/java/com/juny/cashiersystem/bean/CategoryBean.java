package com.juny.cashiersystem.bean;

/**
 * <br> ClassName:
 * <br> Description:  商品类别实体类
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/8 22:09
 */

public class CategoryBean {
    private String categoryName;

    private String categoryID;

    private boolean isSelect;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean getSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
