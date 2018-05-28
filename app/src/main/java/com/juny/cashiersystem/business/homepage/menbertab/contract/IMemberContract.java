package com.juny.cashiersystem.business.homepage.menbertab.contract;

import com.juny.cashiersystem.base.IBaseView;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.bean.RechargeBean;

import io.realm.RealmResults;

/**
 * <br> ClassName: IMemberContract
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 22:05
 */

public interface IMemberContract {
    interface IView extends IBaseView {
        /**
         * <br> Description: 显示会员信息
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void showMemberData(RealmResults<MemberBean> memberList);

        /**
         * <br> Description: 更新显示会员信息
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/15 17:43
         */
        void updateMemberData(MemberBean memberBean);

    }

    interface IPresenter {
        /**
         * <br> Description: 获取会员信息
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void getMemberData();


    }

    interface IModel {
        /**
         * <br> Description: 查询会员列表
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        RealmResults<MemberBean> searchMemberData();

        /**
         * <br> Description: 添加会员
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        void addMember(MemberBean memberBean);

        /**
         * <br> Description: 查询充值记录列表
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        RealmResults<RechargeBean> searchRechargeRecord();

        /**
         * <br> Description: 添加充值记录
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/15 17:54
         */
        void addRecharge(RechargeBean rechargeBean);

    }
}
