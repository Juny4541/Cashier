package com.juny.cashiersystem.business.cashiertab.view;

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
import com.juny.cashiersystem.business.cashiertab.contract.ICashierContract;
import com.juny.cashiersystem.business.cashiertab.presenter.CashierPresenter;
import com.juny.cashiersystem.business.cashiertab.presenter.CategoryListAdapter;
import com.juny.cashiersystem.business.cashiertab.presenter.GoodsListAdapter;
import com.juny.cashiersystem.business.cashiertab.view.widget.OrderDetailListView;
import com.juny.cashiersystem.util.CSToast;
import com.juny.cashiersystem.util.UiUtil;
import com.juny.cashiersystem.widget.AddDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

import static com.juny.cashiersystem.business.cashiertab.presenter.CashierPresenter.DIALOG_TYPE_CATEGORY_DELETE;
import static com.juny.cashiersystem.business.cashiertab.presenter.CashierPresenter.DIALOG_TYPE_GOODS_DELETE;

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
    @BindView(R.id.iv_cashier_add_category)
    ImageView mIvAddCategory;

    private CashierPresenter mCashierPresenter;
    private CategoryListAdapter mCategoryAdapter;
    private GoodsListAdapter mGoodsListAdapter;

    /**
     * 商品数据分类列表
     */
    private RealmResults<CategoryBean> mCategoryRealmResults;
    /**
     * 商品数据查询列表
     */
    private RealmResults<GoodsBean> mGoodsRealmResults;

    /**
     * 当前选中分类
     */
    private int mCurrentSelectIndex = 0;

    /**
     * 当前分类的id
     */
    private int mCurrentCategoryId;

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

        initCategoryList();// 顶部分类列表tab,此方法需要先于initGoodsList执行

        initGoodsList();// 商品列表

        // 按钮监听
        mIvAddGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentCategoryId != 0) {
                    mCashierPresenter.showAddDialog(mActivity, AddDialog.DIALOG_TYPE_GOODS, "goodsDialog", mCurrentCategoryId);
                } else {
                    CSToast.showToast("请先添加并选择分类");
                }
            }
        });

        mIvAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCashierPresenter.showAddDialog(mActivity, AddDialog.DIALOG_TYPE_CATEGORY, "categoryDialog", mCurrentCategoryId);
            }
        });
    }

    /**
     * <br> Description: 分类列表的 初始化和监听
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/11 17:54
     */
    private void initCategoryList() {
        mCategoryAdapter = new CategoryListAdapter(mActivity);
        mRvCategoryList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mRvCategoryList.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setNotifyOnChange(true); // 设置自定刷新

        mCategoryAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 将上次选中的设置为未选中
                CategoryBean categoryBean1 = mCashierPresenter.updateCategorySelected(mCategoryRealmResults.get(mCurrentSelectIndex).getId(), "false");
                if (categoryBean1 != null) {
                    mCategoryAdapter.update(categoryBean1, mCurrentSelectIndex);
                }

                // 新点击的设置为选中状态
                CategoryBean categoryBean2 = mCashierPresenter.updateCategorySelected(mCategoryRealmResults.get(position).getId(), "true");
                if (categoryBean2 != null) {
                    mCategoryAdapter.update(categoryBean2, position);
                    // 记录此次选中的位置
                    mCurrentSelectIndex = position;
                    mCurrentCategoryId = categoryBean2.getId();

                    // 更新商品列表
                    mGoodsListAdapter.clear();
                    mCashierPresenter.getGoodsData(mCurrentCategoryId);
                }
            }
        });

        mCategoryAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                mCashierPresenter.showDeleteDialog(mActivity, DIALOG_TYPE_CATEGORY_DELETE,
                        mCategoryRealmResults.get(position).getId(), "确定删除该商品");
                return false;
            }
        });

        // 数据查询初始化,
        mCashierPresenter.getCategoryData();
    }

    /**
     * <br> Description: 显示商品分类数据
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 10:33
     */
    @Override
    public void showCategoryData(RealmResults<CategoryBean> categoryResults) {
        mCategoryRealmResults = categoryResults;
        // 初次打开显示选中状态的会员信息
        for (int i = 0; i < mCategoryRealmResults.size(); i++) {
            if ("true".equals(mCategoryRealmResults.get(i).getSelect())) {
                mCurrentCategoryId = mCategoryRealmResults.get(i).getId();
            }
        }
        mCategoryAdapter.addAll(mCategoryRealmResults);

        mCategoryRealmResults.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<CategoryBean>>() {
            @Override
            public void onChange(RealmResults<CategoryBean> collection, OrderedCollectionChangeSet changeSet) {
                int[] insertIndexes = changeSet.getInsertions();
                int[] deleteIndexes = changeSet.getDeletions();

                if (insertIndexes.length > 0) {
                    for (int i = 0; i < insertIndexes.length; i++) {
                        mCategoryAdapter.add(collection.get(insertIndexes[i]));
                    }
                }

                if (deleteIndexes.length > 0) {
                    for (int i = 0; i < deleteIndexes.length; i++) {
                        mCategoryAdapter.remove(deleteIndexes[i]);
                    }
                }
            }
        });
    }

    /**
     * <br> Description:  初始化商品列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/11 17:54
     */
    private void initGoodsList() {
        mGoodsListAdapter = new GoodsListAdapter(mActivity);
        mRvGoodsList.setLayoutManager(new GridLayoutManager(mActivity, 6)); // 6是指列数
        mRvGoodsList.setAdapter(mGoodsListAdapter);
        mGoodsListAdapter.setNotifyOnChange(true); // 设置自定刷新
        // 每个item之间的空间间隔
        SpaceDecoration itemDecorationSpace = new SpaceDecoration(UiUtil.dp2px(2));
        itemDecorationSpace.setPaddingEdgeSide(true);
        itemDecorationSpace.setPaddingStart(true);
        itemDecorationSpace.setPaddingHeaderFooter(false);
        mRvGoodsList.addItemDecoration(itemDecorationSpace);

        mGoodsListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                updateOrder(mGoodsRealmResults.get(position));
            }
        });
        mGoodsListAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                mCashierPresenter.showDeleteDialog(mActivity, DIALOG_TYPE_GOODS_DELETE,
                        mGoodsRealmResults.get(position).getId(), "确定删除该商品？");
                return false;
            }
        });

        // 首次查询显示数据
        mCashierPresenter.getGoodsData(mCurrentCategoryId);
    }


    /**
     * <br> Description: 显示商品数据
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 10:32
     */
    @Override
    public void showGoodsData(RealmResults<GoodsBean> goodsRealmResults) {
        mGoodsRealmResults = goodsRealmResults;
        mGoodsListAdapter.addAll(mGoodsRealmResults);
        // 添加更新监听
        mGoodsRealmResults.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<GoodsBean>>() {
            @Override
            public void onChange(RealmResults<GoodsBean> collection, OrderedCollectionChangeSet changeSet) {
                int[] insertIndexes = changeSet.getInsertions();
                int[] deleteIndexes = changeSet.getDeletions();

                // 插入
                if (insertIndexes.length > 0) {
                    for (int i = 0; i < insertIndexes.length; i++) {
                        mGoodsListAdapter.add(collection.get(insertIndexes[i]));
                    }
                }

                // 删除
                if (deleteIndexes.length > 0) {
                    for (int i = 0; i < deleteIndexes.length; i++) {
                        mGoodsListAdapter.remove(deleteIndexes[i]);
                    }
                }
            }
        });
    }

    /**
     * <br> Description: 更新订单列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 10:37
     */
    private void updateOrder(GoodsBean goodsBean) {
        mOlOrderView.updateGoodsList(goodsBean);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCategoryRealmResults != null) {
            mCategoryRealmResults.removeAllChangeListeners();
        }
        if (mGoodsRealmResults != null) {
            mGoodsRealmResults.removeAllChangeListeners();
        }
        mCashierPresenter.closeRealm();
    }
}
