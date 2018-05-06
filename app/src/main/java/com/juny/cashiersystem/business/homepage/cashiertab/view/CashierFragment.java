package com.juny.cashiersystem.business.homepage.cashiertab.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;
import com.juny.cashiersystem.bean.CategoryBean;
import com.juny.cashiersystem.bean.GoodsBean;
import com.juny.cashiersystem.business.homepage.cashiertab.contract.ICashierContract;
import com.juny.cashiersystem.business.homepage.cashiertab.presenter.CashierPresenter;
import com.juny.cashiersystem.business.homepage.cashiertab.presenter.CategoryListAdapter;
import com.juny.cashiersystem.business.homepage.cashiertab.presenter.GoodsListAdapter;
import com.juny.cashiersystem.util.CSToast;
import com.juny.cashiersystem.util.UiUtil;
import com.juny.cashiersystem.widget.OrderDetailListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收银台 fragment
 * Created by Junny on 2018/3/5.
 */

public class CashierFragment extends AbstractCSFragment<CashierPresenter>
        implements ICashierContract.IView {
    /***顶部分类列表***/
    @BindView(R.id.rv_cashier_category_list)
    EasyRecyclerView mRvCategoryList;
    @BindView(R.id.rv_cashier_goods_list)
    EasyRecyclerView mRvGoodsList;
    /***添加商品按钮***/
    @BindView(R.id.iv_cashier_add_goods)
    ImageView mIvAddGoods;
    /***左边订单详情列表***/
    @BindView(R.id.ol_cashier_order_view)
    OrderDetailListView mOlOrderView;

    CashierPresenter mCashierPresenter;
    private CategoryListAdapter mCategoryAdapter;
    private GoodsListAdapter mGoodsListAdapter;

    private List<CategoryBean> mCategoryList;
    /***记录顶部分类tab的选中位置，默认是第一个选中***/
    private int mCategorySelectIndex = 0;


    @Override
    protected List createPresenter() {
        List<CashierPresenter> presenters = new ArrayList<>();
        mCashierPresenter = new CashierPresenter();
        presenters.add(mCashierPresenter);
        return presenters;
    }

    @Override
    protected int getContentRes() {
        return R.layout.cashier_fragment_main;
    }

    @Override
    protected void initView(View view) {

        // 顶部分类列表tab
        mCategoryAdapter = new CategoryListAdapter(mActivity);
        mCategoryList = generateCategoryList();
        mCategoryAdapter.addAll(mCategoryList);
        mRvCategoryList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mRvCategoryList.setAdapter(mCategoryAdapter);

        mCategoryAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == mCategoryList.size() - 1) {
                    CSToast.showToast("add", true);
                } else {
                    // 更新顶部tab 的状态
                    mCategoryList.get(mCategorySelectIndex).setSelect(false); // 将上次选中的设置为未选中
                    mCategoryAdapter.update(mCategoryList.get(mCategorySelectIndex), mCategorySelectIndex);

                    mCategoryList.get(position).setSelect(true); // 新点击的设置为选中状态
                    mCategoryAdapter.update(mCategoryList.get(position), position);

                    mCategorySelectIndex = position; // 记录此次选中的位置
                }
            }
        });


        // 商品列表
         mGoodsListAdapter = new GoodsListAdapter(mActivity);
        mGoodsListAdapter.addAll(generateGoodsList());
        mRvGoodsList.setLayoutManager(new GridLayoutManager(mActivity, 6));
        mRvGoodsList.setAdapter(mGoodsListAdapter);
        // 每个item之间的空间间隔
        SpaceDecoration itemDecorationSpace = new SpaceDecoration(UiUtil.dp2px(2));
        itemDecorationSpace.setPaddingEdgeSide(true);
        itemDecorationSpace.setPaddingStart(true);
        itemDecorationSpace.setPaddingHeaderFooter(false);
        mRvGoodsList.addItemDecoration(itemDecorationSpace);

        mGoodsListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CashierPayActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void setMessageDatas() {

    }

    /**
     * 暂时生成商品列表数据
     */
    private ArrayList<GoodsBean> generateGoodsList() {
        ArrayList<GoodsBean> goodsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setGoodsID(String.valueOf(i));
            goodsBean.setName("苹果");
            goodsBean.setPrice(230);
            goodsBean.setInventory(12);
            goodsList.add(goodsBean);
        }
        return goodsList;
    }

    /**
     * 暂时生成分类列表数据
     */
    private ArrayList<CategoryBean> generateCategoryList() {
        ArrayList<CategoryBean> categoryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setCategoryID(String.valueOf(i));
            categoryBean.setCategoryName("水果");
            if (i == 0) {
                categoryBean.setSelect(true); // 默认第一个选中
            } else {
                categoryBean.setSelect(false);
            }
            categoryList.add(categoryBean);
        }
        // RecyclerArrayAdapter不支持重写getItemCount(),尾布局add按钮 在categoryList尾部添加一个数量
        categoryList.add(categoryList.size(), new CategoryBean());
        return categoryList;
    }
}
