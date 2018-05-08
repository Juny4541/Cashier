package com.juny.cashiersystem.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.realm.bean.OrderBean;

/**
 * 订单详情
 * Created by Junny on 2018/3/6.
 */

public class OrderDetailListView extends LinearLayout {

    private TextView mNumText;
    private TextView mRemarkBtn;
    private RecyclerView mOrderRecycler;
    private TextView mCommitBtn;
    private TextView mCashText;

    private Context mContext;
    private OrderDetailAdapter mOrderDetailAdapter;

    public OrderDetailListView(@NonNull Context context) {
        this(context, null);
    }

    public OrderDetailListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderDetailListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mContext = context;
        initUI(context);
    }

    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_view_order, this);
        mOrderRecycler = findViewById(R.id.order_list);
        mNumText = findViewById(R.id.tv_order_num);
        mRemarkBtn = findViewById(R.id.tv_order_remark);
        mCommitBtn = findViewById(R.id.order_commit_btn);
        mCashText = findViewById(R.id.order_cash_text);

        mOrderRecycler.setLayoutManager(new LinearLayoutManager(context));
        mOrderDetailAdapter = new OrderDetailAdapter(getContext());
//        mOrderDetailAdapter.addAll(generateList());
        mOrderRecycler.setAdapter(mOrderDetailAdapter);
    }

    /**
     * <br> ClassName:   OrderDetailListView
     * <br> Description: 订单列表详情Adapter
     * <br>
     * <br> Author:      chenrunfnag
     * <br> Date:        2018/4/11 10:10
     */
    public static class OrderDetailAdapter extends RecyclerArrayAdapter<OrderBean> {

        public OrderDetailAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return new OrderViewHolder(parent);
        }


        /**
         * <br> ClassName:   OrderDetailAdapter
         * <br> Description:  订单详情列表 View holder
         * <br>
         * <br> Author:      Chenrunfnag
         * <br> Date:        2018/4/11 10:06
         */
        class OrderViewHolder extends BaseViewHolder<OrderBean> {
            private TextView mNameText;
            private TextView mAccountText;
            private TextView mPriceText;
            private ImageView mDeleteBtn;

            OrderViewHolder(ViewGroup parent) {
                super(parent, R.layout.common_order_list_item);
                mNameText = $(R.id.order_name_text);
                mAccountText = $(R.id.order_count_text);
                mPriceText = $(R.id.order_price_text);
                mDeleteBtn = $(R.id.order_delete_btn);
            }

            @Override
            public void setData(OrderBean orderBean) {
                super.setData(orderBean);
//                mNameText.setText(orderBean.getName());
//                mAccountText.setText("x" + String.valueOf(orderBean.getAccount()));
//                mPriceText.setText("￥" + String.valueOf(orderBean.getPrice()));
                mDeleteBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "删除", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * 暂时生成订单详情数据列表
     */

//    private ArrayList<OrderBean> generateList() {
//        ArrayList<OrderBean> orderList = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            OrderBean orderBean = new OrderBean();
//            orderBean.setGoodsID(String.valueOf(i));
//            orderBean.setOrderId(String.valueOf(i));
//            orderBean.setName("苹果");
//            orderBean.setAccount(1);
//            orderBean.setPrice(i);
//            orderList.add(orderBean);
//        }
//        return orderList;
//    }
}

