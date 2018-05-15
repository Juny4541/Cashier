package com.juny.cashiersystem.business.homepage.cashiertab.model;

import android.support.annotation.NonNull;

import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.business.homepage.cashiertab.contract.ICashierContract;
import com.juny.cashiersystem.realm.bean.CategoryBean;
import com.juny.cashiersystem.realm.bean.GoodsBean;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * <br> ClassName:
 * <br> Description:
 * <br>
 * <br> Author:
 * <br> Date:  2018/4/8 17:17
 */

public class CashierRepository implements ICashierContract.IModel {
    private Realm mRealm;

    public CashierRepository() {
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
    }

    @Override
    public RealmResults<CategoryBean> searchCategoryData() {
        return mRealm.where(CategoryBean.class).findAll();
    }

    @Override
    public RealmResults<GoodsBean> searchGoodsData(int categoryId) {
        return mRealm.where(GoodsBean.class).equalTo("categoryId", categoryId).findAll();
    }

    @Override
    public void addCategory(final CategoryBean categoryBean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                CategoryBean category = realm.createObject(CategoryBean.class, (int) System.currentTimeMillis());
                category.setCategoryName(categoryBean.getCategoryName());
                category.setSelect(false); // 默认被选中的状态为 false
            }
        });
    }

    @Override
    public void addGoods(final GoodsBean goodsBean, final int categoryId) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                GoodsBean goods = realm.createObject(GoodsBean.class, (int) System.currentTimeMillis());
                goods.setName(goodsBean.getName());
                goods.setPrice(goodsBean.getPrice());
                goods.setRepertory(goodsBean.getRepertory());
                goods.setCategoryId(categoryId);
            }
        });
    }

    public void deleteGoods(final int goodsId) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<GoodsBean> goods = realm.where(GoodsBean.class).equalTo("id", goodsId).findAll();
                goods.deleteAllFromRealm();
            }
        });
    }

    /**
     * <br> Description: 删除分类，包括分类下面的所有商品
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/12 21:36
     */
    public void deleteCategory(final int categoryId) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 删除分类
                RealmResults<CategoryBean> category = realm.where(CategoryBean.class).equalTo("id", categoryId).findAll();
                category.deleteAllFromRealm();

                // 删除商品
                RealmResults<GoodsBean> goodsSet = realm.where(GoodsBean.class).equalTo("categoryId", categoryId).findAll();
                goodsSet.deleteAllFromRealm();
            }
        });
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
