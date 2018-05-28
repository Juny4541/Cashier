package com.juny.cashiersystem.business.homepage.formstab.bean;

/**
 * <br> ClassName:
 * <br> Description:  运营分析列表实体类
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 16:42
 */

public class AnalyzeBean {
    private String date;
    private int sum;
    private int count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
