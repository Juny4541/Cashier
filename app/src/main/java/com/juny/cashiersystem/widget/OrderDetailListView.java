package com.juny.cashiersystem.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.realm.bean.GoodsBean;
import com.juny.cashiersystem.realm.bean.OrderBean;
import com.juny.cashiersystem.realm.bean.OrderGoodsBean;
import com.juny.cashiersystem.util.CSLog;

import io.realm.RealmList;

/**
 * 订单详情
 * Created by Junny on 2018/3/6.
 */

public class OrderDetailListView extends LinearLayout {

    private TextView mRemarkBtn;
    private RecyclerView mOrderRecycler;
    private TextView mCommitBtn;
    private TextView mCashText;
    private TextView mMemberAddBtn;
    private Context mContext;

    private OrderDetailAdapter mOrderDetailAdapter;

    /**
     * 该订单的商品列表
     */
    private RealmList<OrderGoodsBean> mGoodsRealmList;
    /**
     * 订单
     */
    private OrderBean mOrderBean;

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
        initListener();
    }

    private void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.common_view_order, this);
        mOrderRecycler = findViewById(R.id.order_list);
        mRemarkBtn = findViewById(R.id.tv_order_remark);
        mCommitBtn = findViewById(R.id.order_commit_btn);
        mCashText = findViewById(R.id.order_cash_text);
        mMemberAddBtn = findViewById(R.id.tv_order_member_add);

        mOrderRecycler.setLayoutManager(new LinearLayoutManager(context));
        mOrderDetailAdapter = new OrderDetailAdapter(getContext());
        mOrderDetailAdapter.addAll(mGoodsRealmList);
        mOrderRecycler.setAdapter(mOrderDetailAdapter);

        mGoodsRealmList = new RealmList<>();
        mOrderBean = new OrderBean();
    }

    private void initListener() {
        mMemberAddBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showMemberDialog();
            }
        });
        mRemarkBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
        mCommitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 显示收银对话框
            }
        });
    }

    /**
     * <br> Description: 更新列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 10:31
     *
     * @param bean 商品信息
     */
    public void updateGoodsList(final GoodsBean bean) {
        CSLog.log("onGoodsClick", bean.getName());
        boolean isHasContained = false;
        for (int pos = 0; pos < mGoodsRealmList.size(); pos++) {
            if (mGoodsRealmList.get(pos).getGoodsId() == bean.getId()) {
                int newCount = mGoodsRealmList.get(pos).getCount() + 1;
                OrderGoodsBean newOrderGoods = new OrderGoodsBean();
                newOrderGoods.setCount(newCount);
                newOrderGoods.setName(mGoodsRealmList.get(pos).getName());
                newOrderGoods.setPrice(mGoodsRealmList.get(pos).getPrice());
                newOrderGoods.setId(mGoodsRealmList.get(pos).getId());
                newOrderGoods.setGoodsId(mGoodsRealmList.get(pos).getGoodsId());
                mGoodsRealmList.set(pos, newOrderGoods);
                mOrderDetailAdapter.update(newOrderGoods, pos);
                isHasContained = true;
            }
        }

        if (!isHasContained) {
            OrderGoodsBean goodsBean2 = new OrderGoodsBean();
            goodsBean2.setGoodsId(bean.getId());
            goodsBean2.setName(bean.getName());
            goodsBean2.setPrice(bean.getPrice());
            goodsBean2.setCount(1); // 购买一个
            goodsBean2.setId((int) System.currentTimeMillis());
            mOrderDetailAdapter.add(goodsBean2);
            mGoodsRealmList.add(goodsBean2);
        }
        setCashText(mGoodsRealmList);
    }

    /**
     * <br> Description: 设置订单总额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 11:16
     */
    private void setCashText(RealmList<OrderGoodsBean> goods) {
        int amount = 0;
        if (goods != null && goods.size() > 0) {
            for (int i = 0; i < goods.size(); i++) {
                amount = amount + goods.get(i).getCount() * goods.get(i).getPrice();
            }
        }
        mCashText.setText(String.valueOf(amount));
        mOrderBean.setAmount(amount);
    }

    /**
     * <br> Description: 显示单选对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 15:47
     */
    public void showMemberDialog() {
        final String[] memberIds = {"Struts2", "Spring", "Hibernate", "Mybatis", "Spring MVC"};
        new AlertDialog.Builder(mContext)
                .setTitle("请选择会员")
                .setSingleChoiceItems(memberIds, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * <br> Description: 显示填写备注对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 16:04
     */
    private void showInputDialog() {
        final EditText et = new EditText(mContext);
        new AlertDialog.Builder(mContext).setTitle("请输入备注")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mRemarkBtn.setText(new StringBuilder("备注：").append(et.getText().toString()));
                        mOrderBean.setRemark(et.getText().toString());
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
     * <br> ClassName:   OrderDetailListView
     * <br> Description: 订单列表详情Adapter
     * <br>
     * <br> Author:      chenrunfnag
     * <br> Date:        2018/4/11 10:10
     */
    public static class OrderDetailAdapter extends RecyclerArrayAdapter<OrderGoodsBean> {

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
        class OrderViewHolder extends BaseViewHolder<OrderGoodsBean> {
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
            public void setData(OrderGoodsBean goodsBean) {
                super.setData(goodsBean);
                mNameText.setText(goodsBean.getName());
                mPriceText.setText("￥" + String.valueOf(goodsBean.getPrice()));
                mAccountText.setText(String.valueOf(goodsBean.getCount()));
                mDeleteBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "删除", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}

