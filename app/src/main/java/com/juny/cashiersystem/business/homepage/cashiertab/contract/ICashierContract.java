package com.juny.cashiersystem.business.homepage.cashiertab.contract;

import com.juny.cashiersystem.base.IBaseView;

/**
 * <br> ClassName:
 * <br> Description:
 * <br>
 * <br> Author:
 * <br> Date:  2018/4/8 17:16
 */

public interface ICashierContract {

    interface IView extends IBaseView {
        /**
         *<br> Description: 设置消息数据
         *<br> Author: chenrunfang
         *<br> Date: 2018/4/8 17:23
         */
        void setMessageDatas();
    }

    interface IPresenter {
        /**
         *<br> Description: 加载消息数据
         *<br> Author: chenrunfang
         *<br> Date: 2018/4/8 17:23
         */
        void getMessageDatas();
    }

    interface IModel {

    }

}
