package com.juny.cashiersystem.realm;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.juny.cashiersystem.realm.bean.CategoryBean;
import com.juny.cashiersystem.realm.bean.GoodsBean;
import com.juny.cashiersystem.realm.bean.MemberBean;
import com.juny.cashiersystem.realm.bean.OrderBean;
import com.juny.cashiersystem.realm.bean.RechargeBean;
import com.juny.cashiersystem.util.CSToast;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * <br> ClassName: RealmHelper
 * <br> Description:  Realm 工具类
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/8 10:52
 */

public class RealmHelper {
    private Realm mRealm;
    private RealmAsyncTask mAsyncTask;

    /**
     * <br> Description: 关闭数据库的相关操作
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 15:43
     */
    public void closeRealm() {
        if (mAsyncTask != null && !mAsyncTask.isCancelled()) {
            mAsyncTask.cancel();
        }
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /**
     * <br> Description: 获取当前格式化后的时间
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 15:42
     */
    public String gengerateTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        return time;
    }


    /**
     * <br> Description:  添加商品
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 17:38
     */
    public void addGoods(final GoodsBean goodBean) {
        mAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                GoodsBean goods = realm.createObject(GoodsBean.class);
                goods.setName(goodBean.getName());
                goods.setPrice(goodBean.getPrice());
                goods.setRepertory(goodBean.getRepertory());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CSToast.showToast("添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                CSToast.showToast("添加失败");
            }
        });
    }

    /**
     * <br> Description:  添加商品类别
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 17:38
     */
    public void addCategory(final CategoryBean categoryBean) {
        mAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                CategoryBean category = realm.createObject(CategoryBean.class);
                category.setCategoryName(categoryBean.getCategoryName());
                category.setSelect(false); // 默认被选中的状态为 false
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CSToast.showToast("添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                CSToast.showToast("添加失败");
            }
        });
    }

    /**
     * <br> Description:  添加订单
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 17:38
     */
    public void addOrder(final OrderBean orderBean) {
        mAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                OrderBean order = realm.createObject(OrderBean.class);
                order.setOrderNum(orderBean.getOrderNum());
                order.setAmount(orderBean.getAmount());
                order.setRemark(orderBean.getRemark());
                order.setMember(orderBean.getMember());
                order.setGoods(orderBean.getGoods());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CSToast.showToast("添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                CSToast.showToast("添加失败");
            }
        });
    }

    /**
     * <br> Description:  添加订单
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 17:38
     */
    public void addMember(final MemberBean memberBean) {
        mAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                MemberBean member = realm.createObject(MemberBean.class);
                member.setName(memberBean.getName());
                member.setPhone(memberBean.getPhone());
                member.setCardNum(memberBean.getCardNum());
                member.setRechargeSum(memberBean.getRechargeSum());
                member.setRechargeList(memberBean.getRechargeList());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CSToast.showToast("添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                CSToast.showToast("添加失败");
            }
        });
    }

    /**
     * <br> Description:  添加充值记录
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/8 17:38
     */
    public void addRecharge(final RechargeBean rechargeBean) {
        mAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                RechargeBean recharge = realm.createObject(RechargeBean.class);
                recharge.setMoeny(rechargeBean.getMoeny());
                recharge.setRemark(rechargeBean.getRemark());
                recharge.setTime(rechargeBean.getTime());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                CSToast.showToast("添加成功");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                CSToast.showToast("添加失败");
            }
        });
    }
}
