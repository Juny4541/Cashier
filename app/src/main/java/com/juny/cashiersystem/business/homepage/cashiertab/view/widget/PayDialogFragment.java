package com.juny.cashiersystem.business.homepage.cashiertab.view.widget;


import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.util.ResourceUtil;
import com.juny.cashiersystem.widget.NumKeyBoardView;

/**
 * <br> ClassName: PayDialogFragment
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/16 18:31
 */

public class PayDialogFragment extends DialogFragment {
    /**
     * 支付方式
     */
    private final static int PAY_TYPE_CAISHER = 1;
    private final static int PAY_TYPE_ZFB = 2;
    private final static int PAY_TYPE_WECHAT = 3;
    private final static int PAY_TYPE_CARD = 4;
    private final static int PAY_TYPE_OTHER = 5;

    private TextView mTvOrderNum;
    private TextView mTvMember;
    private TextView mTvMoney;
    private TextView mTvSum;
    private TextView mTvRemark;
    private TextView mTvPayCashier;
    private TextView mTvPayZfb;
    private TextView mTvPayWechat;
    private TextView mTvPayCard;
    private TextView mTvPayOther;
    private TextView mTvSumMoney;
    private NumKeyBoardView mKeyboardView;
    private Button mPayOk;
    private TextView mTvPayType;

    private String mRemark = "";
    private String mSumMoney = "";
    private String mMemberName = "";
    private String mBalance = "";
    private String mOrderNum = "";
    /**
     * 支付方式类型，默认现金
     */
    private int mPayType = PAY_TYPE_CAISHER;
    private OnCommitOrderListener mListener;

    public interface OnCommitOrderListener {
        void onCommit(int payType, int payMoney);
    }

    public void setOnCommitOrderListener(OnCommitOrderListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.cashier_pay_dialog, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void setData(String remark, String sumMoney, String memberName,
                        String balance, String orderNum) {
        mRemark = remark;
        mSumMoney = sumMoney;
        mMemberName = memberName;
        mBalance = balance;
        mOrderNum = orderNum;
    }

    private void initView(View view) {
        mTvOrderNum = view.findViewById(R.id.tv_cashier_pay_order_num);
        mTvMember = view.findViewById(R.id.tv_cashier_pay_member);
        mTvMoney = view.findViewById(R.id.tv_cashier_pay_remaining_money);
        mTvSum = view.findViewById(R.id.tv_cashier_pay_sum);
        mTvRemark = view.findViewById(R.id.tv_cashier_pay_remark);
        mTvPayCashier = view.findViewById(R.id.tv_cashier_pay_cashier);
        mTvPayZfb = view.findViewById(R.id.tv_cashier_pay_zfb);
        mTvPayWechat = view.findViewById(R.id.tv_cashier_pay_wechat);
        mTvPayCard = view.findViewById(R.id.tv_cashier_pay_card);
        mTvPayOther = view.findViewById(R.id.tv_cashier_pay_other);
        mTvSumMoney = view.findViewById(R.id.tv_cashier_pay_sum_money);
        mKeyboardView = view.findViewById(R.id.keyboard_view);
        mTvPayType = view.findViewById(R.id.tv_cashier_pay_type);
        mPayOk = view.findViewById(R.id.btn_cashier_pay_ok);

        // 自定义键盘关联textView
        mKeyboardView.attachTextView(mTvSumMoney);

        // 默认选中现金支付方式
        mTvPayCashier.setBackgroundColor(Color.WHITE);
        mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_cashier));


        mTvOrderNum.setText(new StringBuilder("订单号：").append(mOrderNum));
        mTvRemark.setText(new StringBuilder("备注：").append(mRemark));

        if (!TextUtils.isEmpty(mMemberName)) {
            mTvMember.setVisibility(View.VISIBLE);
            mTvMember.setText(new StringBuilder("会员：").append(mMemberName));
            mTvMoney.setVisibility(View.VISIBLE);
            mTvMoney.setText(new StringBuilder("会员卡余额：").append(mBalance));
        }

        mTvSum.setText(new StringBuilder("收款合计：").append(mSumMoney));
        mTvSumMoney.setText(mSumMoney);
    }

    private void initListener() {
        mTvPayCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextBackground();
                mPayType = PAY_TYPE_CAISHER;
                mTvPayCashier.setBackgroundColor(Color.WHITE);
                mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_cashier));
            }
        });
        mTvPayZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextBackground();
                mPayType = PAY_TYPE_ZFB;
                mTvPayZfb.setBackgroundColor(Color.WHITE);
                mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_zfb));
            }
        });
        mTvPayWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextBackground();
                mPayType = PAY_TYPE_WECHAT;
                mTvPayWechat.setBackgroundColor(Color.WHITE);
                mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_wechat));
            }
        });
        mTvPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextBackground();
                mPayType = PAY_TYPE_CARD;
                mTvPayCard.setBackgroundColor(Color.WHITE);
                mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_card));
            }
        });
        mTvPayOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTextBackground();
                mPayType = PAY_TYPE_OTHER;
                mTvPayOther.setBackgroundColor(Color.WHITE);
                mTvPayType.setText(ResourceUtil.getString(R.string.cashier_pay_type_other));
            }
        });

        mPayOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCommit(mPayType, Integer.parseInt(mTvSumMoney.getText().toString()));
                }
                dismiss();
            }
        });

    }

    /**
     * <br> Description:重设按钮背景
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/16 18:50
     */
    private void resetTextBackground() {
        mTvPayCashier.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
        mTvPayZfb.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
        mTvPayWechat.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
        mTvPayCard.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
        mTvPayOther.setBackgroundColor(ResourceUtil.getColor(R.color.gray_head));
    }
}
