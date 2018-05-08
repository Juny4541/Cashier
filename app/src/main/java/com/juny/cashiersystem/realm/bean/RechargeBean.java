package com.juny.cashiersystem.realm.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * <br> ClassName: RechargeBean
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/8 14:30
 */

public class RechargeBean extends RealmObject {
    @PrimaryKey
    private int id;

    /**
     * 充值金额
     */
    private int moeny;

    /**
     * 充值时间
     */
    private String time;

    /**
     *  备注
     */
    private String remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoeny() {
        return moeny;
    }

    public void setMoeny(int moeny) {
        this.moeny = moeny;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
