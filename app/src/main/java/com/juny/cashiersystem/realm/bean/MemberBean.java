package com.juny.cashiersystem.realm.bean;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <br> ClassName: MemberBean
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/8 14:08
 */

public class MemberBean extends RealmObject {

    @PrimaryKey
    private int id;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 会员名
     */
    private String name;

    /**
     * 会员卡号
     */
    private String cardNum;

    /**
     * 充值总额
     */
    private int rechargeSum;

    private String isSelect;

    /**
     * 充值列表
     */
    private RealmList<RechargeBean> rechargeList;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getRechargeSum() {
        return rechargeSum;
    }

    public void setRechargeSum(int rechargeSum) {
        this.rechargeSum = rechargeSum;
    }

    public RealmList<RechargeBean> getRechargeList() {
        return rechargeList;
    }

    public void setRechargeList(RealmList<RechargeBean> rechargeList) {
        this.rechargeList = rechargeList;
    }

    public String getSelect() {
        return isSelect;
    }

    public void setSelect(String select) {
        isSelect = select;
    }
}
