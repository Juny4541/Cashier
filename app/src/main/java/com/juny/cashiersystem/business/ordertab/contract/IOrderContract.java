package com.juny.cashiersystem.business.ordertab.contract;

import com.juny.cashiersystem.base.IBaseView;
import com.juny.cashiersystem.bean.OrderBean;

import io.realm.RealmResults;

/**
 * <br> ClassName: IOrderContract
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 9:46
 */

public interface IOrderContract {

    interface IView extends IBaseView {
        /**
         * <br> Description:  显示列表数据
         * <br> Author: Juny
         * <br> Date:  2018/5/18  15:54
         */
        void showOrderDatas(RealmResults<OrderBean> orders);
    }

    interface IPresenter {
        /**
         * <br> Description:  获取列表数据
         * <br> Author: Juny
         * <br> Date:  2018/5/18  15:54
         */
        void getOrderDatas();

        /**
         * <br> Description: 更新订单的选中状态
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/15 14:34
         */
        OrderBean updateOrder(int orderId, String isSelect);
    }

    interface IModle {
        /**
         * <br> Description:  查询列表数据
         * <br> Author: Juny
         * <br> Date:  2018/5/18  15:55
         */
        RealmResults<OrderBean> searchOrderData();

        /**
         * <br> Description: 更新订单的选中状态
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/15 14:34
         */
        OrderBean updateOrder(int orderId, String isSelect);
    }

}
