package com.juny.cashiersystem.business.cashiertab.view.widget;

import android.app.Activity;
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
import com.juny.cashiersystem.bean.GoodsBean;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.bean.OrderGoodsBean;
import com.juny.cashiersystem.bluetooh.PrintEvent;
import com.juny.cashiersystem.business.cashiertab.presenter.CashierPresenter;
import com.juny.cashiersystem.util.CSLog;
import com.juny.cashiersystem.util.CSToast;
import com.juny.cashiersystem.util.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * 订单详情
 * Created by Junny on 2018/3/6.
 */

public class OrderDetailListView extends LinearLayout {

    public final static int OPEN_TYPE_CASHIER = 1;
    public final static int OPEN_TYPE_ORDER = 2;


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

    private CashierPresenter mCashierPresenter;

    /**
     * 订单视图所在页面，默认收银台页面
     */
    private int mOpenType = OPEN_TYPE_CASHIER;


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
        mCashierPresenter = new CashierPresenter();
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
                switch (mOpenType) {
                    case OPEN_TYPE_CASHIER:
                        if (mGoodsRealmList.size() > 0) {
                            mOrderBean.setGoods(mGoodsRealmList);
                            mOrderBean.setOrderNum(generateOrderNum());
                            showPayDialog((Activity) getContext(), "支付对话框");
                        } else {
                            CSToast.showToast("请先选购商品");
                        }
                        break;
                    case OPEN_TYPE_ORDER:
                        EventBus.getDefault().post(new PrintEvent(mOrderBean));
                        CSLog.log("打印");
                        break;
                }
            }
        });
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
    private void showMemberDialog() {
        final RealmResults<MemberBean> memberResults = mCashierPresenter.getMembers();
        final String[] memberIds = new String[memberResults.size()];
        for (int i = 0; i < memberResults.size(); i++) {
            memberIds[i] = memberResults.get(i).getName();
        }
        // 默认选中第一个会员
        if (memberResults.size() > 0) {
            mOrderBean.setMemberId(memberResults.get(0).getId());
        }
        new AlertDialog.Builder(mContext)
                .setTitle("请选择会员")
                .setSingleChoiceItems(memberIds, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int index) {
                        mOrderBean.setMemberId(memberResults.get(index).getId());
                        mMemberAddBtn.setText(new StringBuffer("会员：").append(memberResults.get(index).getName()));
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
     * <br> Description: 弹出支付会话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 10:33
     */
    private void showPayDialog(Activity activity, String tag) {
        // 初始化dialog View
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        if (mOrderBean.getMemberId() == 0) {
            payDialogFragment.setData(mOrderBean.getRemark(), String.valueOf(mOrderBean.getAmount()),
                    null, null, mOrderBean.getOrderNum());
        } else {
            MemberBean member = mCashierPresenter.searchMemberById(mOrderBean.getMemberId());
            if (member != null) {
                payDialogFragment.setData(mOrderBean.getRemark(), String.valueOf(mOrderBean.getAmount()),
                        member.getName(), String.valueOf(member.getBalance()), mOrderBean.getOrderNum());
            } else {
                CSLog.logError("会员为空，数据错误");
                return;
            }
        }
        payDialogFragment.show(activity.getFragmentManager(), tag);
        payDialogFragment.setOnCommitOrderListener(new PayDialogFragment.OnCommitOrderListener() {
            @Override
            public void onCommit(int payType, int payMoney) {

                mOrderBean.setPayType(payType);
                mOrderBean.setAmount(payMoney);
                mOrderBean.setDate(TimeUtil.getStringDate());
                mCashierPresenter.addOrder(mOrderBean);

                // 打印
                EventBus.getDefault().post(new PrintEvent(mOrderBean));
                CSLog.log(mOrderBean.getOrderNum());

                resetGoodsList();
            }
        });
    }

    /**
     * <br> Description: 产生4位随机数(0000-9999)
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 10:29
     */
    private String generateOrderNum() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return TimeUtil.getStringOrderDate() + fourRandom;
    }

    /**
     * <br> Description: 清空重置列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/17 17:44
     */
    private void resetGoodsList() {
        mOrderBean = new OrderBean();
        mGoodsRealmList.clear();
        mOrderDetailAdapter.clear();
        mMemberAddBtn.setText("会员 + ");
        mRemarkBtn.setText("备注 + ");
    }

    /**
     * <br> Description: 更新列表
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/13 10:31
     *
     * @param bean 商品信息
     */
    public void updateGoodsList(final GoodsBean bean) {
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
     * <br> Description:  设置订单详情
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:00
     */
    public void setGoodsDetail(OrderBean orderBean) {
        mOrderBean = orderBean;
        mOrderDetailAdapter.clear();
        mGoodsRealmList = orderBean.getGoods();
        if (mGoodsRealmList.size() > 0) {
            mOrderDetailAdapter.addAll(mGoodsRealmList);
        }
        mCashText.setText(String.valueOf(orderBean.getAmount()));
        if (orderBean.getMemberId() != 0) {
            MemberBean memberBean = mCashierPresenter.searchMemberById(orderBean.getMemberId());
            if (memberBean != null) {
                mMemberAddBtn.setText(new StringBuilder("会员：")
                        .append(memberBean.getName()));
            }
            mRemarkBtn.setText(new StringBuilder("备注：").append(orderBean.getRemark()));
        }
    }

    /**
     * 设置订单所在的页面类型
     */
    public void setOpenType(int openType) {
        mOpenType = openType;

        if (mOpenType == OPEN_TYPE_ORDER) {
            mRemarkBtn.setText("备注：");
            mRemarkBtn.setClickable(false);
            mMemberAddBtn.setText("会员：");
            mMemberAddBtn.setClickable(false);
            mCommitBtn.setText("打 印");
        } else {
            // 保持默认即可
        }
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

