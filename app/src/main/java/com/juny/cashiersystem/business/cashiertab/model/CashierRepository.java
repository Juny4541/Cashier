package com.juny.cashiersystem.business.cashiertab.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.business.cashiertab.contract.ICashierContract;
import com.juny.cashiersystem.bean.CategoryBean;
import com.juny.cashiersystem.bean.GoodsBean;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.bean.OrderBean;

import io.realm.OrderedRealmCollectionSnapshot;
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
                category.setSelect("false"); // 默认被选中的状态为 false
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

    /**
     * <br> Description: 添加订单
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 10:41
     */
    @Override
    public void addOrder(final OrderBean orderBean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                OrderBean order = realm.createObject(OrderBean.class, (int) System.currentTimeMillis());
                order.setOrderNum(orderBean.getOrderNum());
                order.setAmount(orderBean.getAmount());
                order.setPayType(orderBean.getPayType());
                order.setRemark(orderBean.getRemark());
                order.setDate(orderBean.getDate());
                order.setMemberId(orderBean.getMemberId());
                order.setSelect("false"); // 默认false
                for (int i = 0; i < orderBean.getGoods().size(); i++) {
                    order.getGoods().add(orderBean.getGoods().get(i));
                }
            }
        });
    }

    /**
     * <br> Description: 删除商品
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 16:24
     */
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
     * <br> Description: 更新会员信息
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 11:04
     */
    public CategoryBean updateCategorySelected(int categoryId, String isSelect) {
        // 先查询
        RealmResults<CategoryBean> all = mRealm.where(CategoryBean.class)
                .equalTo("id", categoryId)
                .findAll();
        if (all.size() == 0){
            return null;
        }
        // 更新
        mRealm.beginTransaction();
        OrderedRealmCollectionSnapshot<CategoryBean> menSnapshot = all.createSnapshot();
        for (int i = 0; i < menSnapshot.size(); i++) {
            if (!TextUtils.isEmpty(isSelect)) {
                menSnapshot.get(i).setSelect(isSelect);
            }
        }
        mRealm.commitTransaction();
        return all.get(0);  // 返回查询更新结果
    }

    /**
     * <br> Description: 根据ID 查询会员
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 9:44
     */
    public MemberBean searchMemberById(int memberId) {
        // 先查询
        RealmResults<MemberBean> all = mRealm.where(MemberBean.class)
                .equalTo("id", memberId)
                .findAll();
        if (all.size() == 0) {
            return null;
        }
        return all.get(0);
    }

    /**
     * <br> Description: 更新会员余额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 9:44
     */
    public void updateMemberBalance(MemberBean member, int newBalance) {
        mRealm.beginTransaction();
        member.setBalance(newBalance);
        mRealm.commitTransaction();
    }

    /**
     * <br> Description: 查询会员列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 9:48
     */
    public RealmResults<MemberBean> searchMembers() {
        return mRealm.where(MemberBean.class).findAll();
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
