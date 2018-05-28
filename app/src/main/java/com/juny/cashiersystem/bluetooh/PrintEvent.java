package com.juny.cashiersystem.bluetooh;

import com.juny.cashiersystem.bean.OrderBean;

/**
 * <br> ClassName: PrintEvent
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/5/28 23:07
 */

public class PrintEvent {
    private OrderBean orderBean;

    public PrintEvent(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }
}
