package com.juny.cashiersystem.business.homepage.cashiertab.model.bean;

/**
 * <br> ClassName: KeyBean
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/5/5 22:28
 */

public class KeyBean {
    private Integer code;
    private String lable;
    public KeyBean(Integer code,String lable){
        this.code = code;
        this.lable = lable;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
