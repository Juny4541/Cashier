package com.juny.cashiersystem.business.homepage.ordertab.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;
import com.juny.cashiersystem.business.homepage.cashiertab.view.widget.OrderDetailListView;
import com.juny.cashiersystem.business.homepage.ordertab.contract.IOrderContract;
import com.juny.cashiersystem.business.homepage.ordertab.presenter.OrderListAdapter;
import com.juny.cashiersystem.business.homepage.ordertab.presenter.OrderPresenter;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.util.CSLog;
import com.juny.cashiersystem.widget.DateSelectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 * 订单 fragment
 * Created by Junny on 2018/3/5.
 */

public class OrderFragment extends AbstractCSFragment implements IOrderContract.IView {

    @BindView(R.id.dl_order_view)
    DateSelectView mDateView;
    @BindView(R.id.rv_order_list)
    EasyRecyclerView mRvOrderList;
    @BindView(R.id.ol_order_detail_view)
    OrderDetailListView mOrderDetailView;
    private OrderListAdapter mAdapter;

    private RealmResults<OrderBean> mOrderResults;
    private OrderPresenter mOrderPresenter;

    /**
     * 单签选中得会员id
     */
    private int mCurrentOrderId;

    /***记录顶部分类tab的选中位置，默认是第一个选中***/
    private int mOrderSelectIndex = 0;

    @Override
    protected int getContentRes() {
        return R.layout.order_fragment_main;
    }

    @Override
    protected List createPresenter() {
        List<OrderPresenter> presenters = new ArrayList<>();
        mOrderPresenter = new OrderPresenter();
        presenters.add(mOrderPresenter);
        return presenters;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        // 订单列表
        mAdapter = new OrderListAdapter(mActivity);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 将上次选中的设置为未选中
                OrderBean orderBean = mOrderPresenter.updateOrder(mOrderResults.get(mOrderSelectIndex).getId(), "false");
                if (orderBean != null) {
                    mAdapter.update(orderBean, mOrderSelectIndex);
                }

                // 新点击的设置为选中状态
                OrderBean currentBean = mOrderPresenter.updateOrder(mOrderResults.get(position).getId(), "true");
                if (currentBean != null) {
                    mAdapter.update(currentBean, position);
                    updateOrderDetail(currentBean);
                    // 记录此次选中的位置
                    mCurrentOrderId = currentBean.getId();
                    mOrderSelectIndex = position; // 记录此次选中的位置
                }
            }
        });
        mOrderPresenter.getOrderDatas();

        // 订单详情
        mOrderDetailView.setOpenType(OrderDetailListView.OPEN_TYPE_ORDER);

        mDateView.setOnDateSelectListener(new DateSelectView.OnDateSelectListener() {
            @Override
            public void endDateSelected(String beginDate, String endDate) {
                updateList(getOrdersByDate(beginDate, endDate));
            }
        });
    }

    @Override
    public void showOrderDatas(RealmResults<OrderBean> orders) {
        mOrderResults = orders;

        // 初次打开，显示选中信息
        for (int i = 0; i < mOrderResults.size(); i++) {
            if ("true".equals(mOrderResults.get(i).getSelect())) {
                mCurrentOrderId = mOrderResults.get(i).getId();
                updateOrderDetail(mOrderResults.get(i));
            }
        }
        mAdapter.addAll(mOrderResults);
    }

    /**
     * <br> Description:  更新订单详情
     * <br> Author: Juny
     * <br> Date:  2018/5/18  22:38
     */
    private void updateOrderDetail(OrderBean orderBean) {
        mOrderDetailView.setGoodsDetail(orderBean);
    }

    /**
     * <br> Description:  按日期查询显示订单
     * <br> Author: Juny
     * <br> Date:  2018/5/18  22:39
     */

    private ArrayList<OrderBean> getOrdersByDate(String beginDate, String endDate) {
        ArrayList<OrderBean> list = new ArrayList<>();
        int beginIndex = 0;
        int endIndex = 0;

        // 确定始末索引
        for (int i = 0; i < mOrderResults.size(); i++) {
            if (beginDate.equals(mOrderResults.get(i).getDate())) {
                beginIndex = i;
            }

            // TODO 无法比较值是否相等
         if (endDate.equals(mOrderResults.get(i).getDate())) {
                CSLog.log(mOrderResults.get(i).getDate() + "  " + endDate);
                endIndex = i;
            }
        }

        for (int i = beginIndex; i < endIndex + 1; i++) {
            list.add(i, mOrderResults.get(i));
        }
        return list;
    }

    /**
     * <br> Description:  根据查询列表更新列表
     * <br> Author: Juny
     * <br> Date:  2018/5/18  22:50
     */
    private void updateList(List<OrderBean> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
        if (list.size() > 0) {
            mOrderSelectIndex = 0;
            updateOrderDetail(list.get(mOrderSelectIndex));
            mCurrentOrderId = list.get(mOrderSelectIndex).getId();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOrderPresenter.closeRealm();
    }
}
