package com.juny.cashiersystem.business.homepage.cashiertab.contract;

import com.juny.cashiersystem.base.IBaseView;
import com.juny.cashiersystem.realm.bean.CategoryBean;
import com.juny.cashiersystem.realm.bean.GoodsBean;
import com.juny.cashiersystem.realm.bean.OrderBean;

import io.realm.RealmResults;

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
         * <br> Description: 设置分类数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void showCategoryData(RealmResults<CategoryBean> categoryList);

        /**
         * <br> Description: 设置商品数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void showGoodsData(RealmResults<GoodsBean> goodsRealmResults);

    }

    interface IPresenter {
        /**
         * <br> Description: 获取分类数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/4/8 17:23
         */
        void getCategoryData();

        /**
         * <br> Description: 获取商品数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:19
         */
        void getGoodsData(int categoryId);

        /**
         *<br> Description: 添加订单
         *<br> Author: chenrunfang
         *<br> Date: 2018/5/17 10:44
         */
        void addOrder(OrderBean orderBean);

    }

    interface IModel {
        /**
         * <br> Description: 查询分类数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        RealmResults<CategoryBean> searchCategoryData();

        /**
         * <br> Description: 查询商品数据
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        RealmResults<GoodsBean> searchGoodsData(int categoryId);

        /**
         * <br> Description: 添加分类
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:16
         */
        void addCategory(CategoryBean categoryBean);


        /**
         * <br> Description: 添加商品
         * <br> Author: chenrunfang
         * <br> Date: 2018/5/10 15:17
         */
        void addGoods(GoodsBean goodsBean, int goodsId);

        /**
         *<br> Description: 添加订单
         *<br> Author: chenrunfang
         *<br> Date: 2018/5/17 10:44
         */
        void addOrder(OrderBean orderBean);

    }

}
