package com.juny.cashiersystem.business.homepage.menbertab.contract;

import com.juny.cashiersystem.base.IBaseView;
import com.juny.cashiersystem.realm.bean.CategoryBean;
import com.juny.cashiersystem.realm.bean.MemberBean;

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
         * <br> Description:
         * 数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void showMemberData(RealmResults<MemberBean> MemberList);
    }

    interface IPresenter {
        /**
         * <br> Description: 获取分类数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void getMemberData();
    }

    interface IModel {
        /**
         * <br> Description: 查询分类数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        RealmResults<MemberBean> searchMemberData();

        /**
         * <br> Description: 添加分类
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        void addMember(CategoryBean categoryBean);
    }
}
