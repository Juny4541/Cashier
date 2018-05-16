package com.juny.cashiersystem.business.homepage.cashiertab.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.juny.cashiersystem.base.BasePresenter;
import com.juny.cashiersystem.business.homepage.cashiertab.contract.ICashierContract;
import com.juny.cashiersystem.business.homepage.cashiertab.model.CashierRepository;
import com.juny.cashiersystem.realm.bean.CategoryBean;
import com.juny.cashiersystem.realm.bean.GoodsBean;
import com.juny.cashiersystem.widget.AddDialog;

/**
 * <br> ClassName:
 * <br> Description:
 * <br>
 * <br> Author:
 * <br> Date:  2018/4/8 17:18
 */

public class CashierPresenter extends BasePresenter<ICashierContract.IView>
        implements ICashierContract.IPresenter {
    public final static int DIALOG_TYPE_GOODS_DELETE = 1;
    public final static int DIALOG_TYPE_CATEGORY_DELETE = 2;

    private CashierRepository mRepository;

    public CashierPresenter() {
        mRepository = new CashierRepository();
    }

    @Override
    public void getCategoryData() {
        if (isViewAttached()) {
            getView().showCategoryData(mRepository.searchCategoryData());
        }
    }

    @Override
    public void getGoodsData(int categoryId) {
        if (isViewAttached()) {
            getView().showGoodsData(mRepository.searchGoodsData(categoryId));
        }
    }

    /**
     * <br> Description: 显示插入商品数据对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 15:02
     */
    public void showAddDialog(Activity activity, int dialogType, String tag, final int categoryId) {
        AddDialog dialog = new AddDialog();
        dialog.setDialogType(dialogType); // 需要先设置对话框的类型，再显示， 才能显示相应的自定义布局
        dialog.show(activity.getFragmentManager(), tag);

        dialog.setOnCashierAddListener(new AddDialog.OnCashierAddListener() {
            @Override
            public void onCategoryAdd(String name) {
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCategoryName(name);
                categoryBean.setSelect("false"); // 默认选中状态为false
                mRepository.addCategory(categoryBean); // 执行数据库插入
            }

            @Override
            public void onGoodsAdd(String name, int price, int repertory) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setName(name);
                goodsBean.setPrice(price);
                goodsBean.setRepertory(repertory);
                mRepository.addGoods(goodsBean, categoryId); // 执行数据库插入
            }
        });
    }



    /**
     *<br> Description: 显示删除对话框
     *<br> Author: chenrunfang
     *<br> Date: 2018/5/16 17:06
     */
    public void showDeleteDialog(Activity activity, final int dialogType, final int id, String content) {
        new AlertDialog.Builder(activity)
                .setMessage(content)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (DIALOG_TYPE_GOODS_DELETE == dialogType) {
                            mRepository.deleteGoods(id);
                        }
                        if (DIALOG_TYPE_CATEGORY_DELETE == dialogType) {
                            mRepository.deleteCategory(id);
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    /**
     * <br> Description: 更新会员信息
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 11:04
     */
    public CategoryBean updateCategorySelected(int categoryId, String isSelect){
        return mRepository.updateCategorySelected(categoryId,isSelect);
    }

    /**
     * <br> Description: 关闭数据库相关的操作
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 15:44
     */
    public void closeRealm() {
        mRepository.closeRealm();
    }
}
