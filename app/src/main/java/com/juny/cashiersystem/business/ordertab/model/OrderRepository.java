package com.juny.cashiersystem.business.ordertab.model;

import android.text.TextUtils;

import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.business.ordertab.contract.IOrderContract;

import io.realm.OrderedRealmCollectionSnapshot;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * <br> ClassName: OrderRepository
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 9:51
 */

public class OrderRepository implements IOrderContract.IModle {
    private Realm mRealm;

    public OrderRepository() {
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
    }

    @Override
    public RealmResults<OrderBean> searchOrderData() {
        return mRealm.where(OrderBean.class).findAll();
    }

    /**
     * <br> Description: 更新订单的选中状态
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 14:34
     */
    public OrderBean updateOrder(int orderId, String isSelect) {
        // 先查询
        RealmResults<OrderBean> all = mRealm.where(OrderBean.class)
                .equalTo("id", orderId)
                .findAll();
        // 更新
        mRealm.beginTransaction();
        OrderedRealmCollectionSnapshot<OrderBean> menSnapshot = all.createSnapshot();
        for (int i = 0; i < menSnapshot.size(); i++) {
            if (!TextUtils.isEmpty(isSelect)) {
                menSnapshot.get(i).setSelect(isSelect);
            }
        }
        mRealm.commitTransaction();

        if (all.get(0).getId() == orderId) {
            return all.get(0);
        }
        return null;
    }

    /**
     * <br> Description: 关闭数据库的相关操作
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 15:43
     */
    public void closeRealm() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }
}
