package com.juny.cashiersystem.business.homepage.ordertab.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;
import com.juny.cashiersystem.business.homepage.ordertab.contract.IOrderContract;
import com.juny.cashiersystem.business.homepage.ordertab.presenter.OrderListAdapter;
import com.juny.cashiersystem.realm.bean.OrderBean;
import com.juny.cashiersystem.widget.DateSelectView;
import com.juny.cashiersystem.widget.OrderDetailListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    /***记录顶部分类tab的选中位置，默认是第一个选中***/
    private int mOrderSelectIndex = 0;
    private List<OrderBean> mList;

    @Override
    protected int getContentRes() {
        return R.layout.order_fragment_main;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new OrderListAdapter(mActivity);
        mList = generateList();
        mAdapter.addAll(mList);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                // 更新选中 的状态
////                mList.get(mOrderSelectIndex).setSelected(false); // 将上次选中的设置为未选中
//                mAdapter.update(mList.get(mOrderSelectIndex), mOrderSelectIndex);
//
////                mList.get(position).setSelected(true); // 新点击的设置为选中状态
//                mAdapter.update(mList.get(position), position);
//                CSToast.showToast("select" + String.valueOf(position));
//
//                mOrderSelectIndex = position; // 记录此次选中的位置
            }
        });
    }


    /**
     * 暂时生成订单详情数据列表
     */
    private ArrayList<OrderBean> generateList() {
        ArrayList<OrderBean> orderList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            OrderBean orderBean = new OrderBean();
////            orderBean.setOrderId(String.valueOf(i));
//            orderBean.setAmount(i);
//            orderBean.setDate("2018/04/10");
////            orderBean.setMemberBuyer("Juny");
//            orderBean.setRemark("社会社会");
//
////            // 默认相中第一项
////            if (i == 0) {
////                orderBean.setSelected(true);
////            } else {
////                orderBean.setSelected(false);
////            }
//
//            orderList.add(orderBean);
//        }
        return orderList;
    }

}
