package com.juny.cashiersystem.business.menbertab.model;

import android.text.TextUtils;

import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.bean.RechargeBean;
import com.juny.cashiersystem.business.menbertab.contract.IMemberContract;

import io.realm.OrderedRealmCollectionSnapshot;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * <br> ClassName: MemberRepository
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 22:07
 */

public class MemberRepository implements IMemberContract.IModel {
    private Realm mRealm;

    public MemberRepository() {
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
    }

    @Override
    public RealmResults<MemberBean> searchMemberData() {
        return mRealm.where(MemberBean.class).findAll();
    }


    @Override
    public void addMember(final MemberBean memberBean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemberBean bean = mRealm.createObject(MemberBean.class, (int) System.currentTimeMillis());
                bean.setName(memberBean.getName());
                bean.setPhone(memberBean.getPhone());
                bean.setCardNum(memberBean.getCardNum());
            }
        });
    }

    @Override
    public RealmResults<RechargeBean> searchRechargeRecord() {
        return mRealm.where(RechargeBean.class).findAll();
    }

    @Override
    public void addRecharge(final RechargeBean rechargeBean) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RechargeBean bean = mRealm.createObject(RechargeBean.class, (int) System.currentTimeMillis());
                bean.setMoeny(rechargeBean.getMoeny());
                bean.setRemark(rechargeBean.getRemark());
                bean.setTime(rechargeBean.getTime());
            }
        });
    }


    /**
     * <br> Description: 删除会员
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 10:09
     */
    public void deleteMember(final int memberId) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MemberBean> members = realm.where(MemberBean.class).equalTo("id", memberId).findAll();
                members.deleteAllFromRealm();
            }
        });
    }

    /**
     * <br> Description: 更新会员的选中状态
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 14:34
     */
    public MemberBean updateMember(int memberId, String isSelect) {
        return updateMember(memberId, 0, null, null, isSelect);
    }

    /**
     * <br> Description: 更新会员的充值总额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 14:34
     */
    public MemberBean updateMember(int memberId, int rechargeSum) {
        return updateMember(memberId, rechargeSum, null, null, null);
    }

    /**
     * <br> Description: 编辑更新会员姓名和电话
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 14:34
     */
    public MemberBean updateMember(int memberId, String name, String phone) {
        return updateMember(memberId, 0, name, phone, null);
    }

    /**
     * <br> Description: 更新会员信息
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 11:04
     */
    public MemberBean updateMember(int memberID, int rechargeSum, String name, String phone, String isSelect) {
        // 先查询
        RealmResults<MemberBean> all = mRealm.where(MemberBean.class)
                .equalTo("id", memberID)
                .findAll();
        // 更新
        mRealm.beginTransaction();
        OrderedRealmCollectionSnapshot<MemberBean> menSnapshot = all.createSnapshot();
        for (int i = 0; i < menSnapshot.size(); i++) {
            if (rechargeSum != 0) {
                menSnapshot.get(i).setRechargeSum(rechargeSum);
            }
            if (!TextUtils.isEmpty(name)) {
                menSnapshot.get(i).setName(name);
            }
            if (!TextUtils.isEmpty(phone)) {
                menSnapshot.get(i).setPhone(phone);
            }
            if (!TextUtils.isEmpty(isSelect)) {
                menSnapshot.get(i).setSelect(isSelect);
            }
        }
        mRealm.commitTransaction();

        if (all.get(0).getId() == memberID) {
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
