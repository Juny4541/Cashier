package com.juny.cashiersystem.widget;


import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.util.ResourceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <br> ClassName: PayDialog
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/16 18:31
 */

public class PayDialog extends DialogFragment {
    /**
     * 支付方式
     */
    private final static int PAY_TYPE_CAISHER = 1;
    private final static int PAY_TYPE_ZFB = 2;
    private final static int PAY_TYPE_WECHAT = 3;
    private final static int PAY_TYPE_CARD = 4;
    private final static int PAY_TYPE_OTHER = 5;

    @BindView(R.id.tv_cashier_pay_order_num)
    TextView mTvOrderNum;
    @BindView(R.id.tv_cashier_pay_member)
    TextView mTvMember;
    @BindView(R.id.tv_cashier_pay_remaining_money)
    TextView mTvMoney;
    @BindView(R.id.tv_cashier_pay_sum)
    TextView mTvSum;
    @BindView(R.id.tv_cashier_pay_remark)
    TextView mTvRemark;
    @BindView(R.id.tv_cashier_pay_cashier)
    TextView mTvPayCashier;
    @BindView(R.id.tv_cashier_pay_zfb)
    TextView mTvPayZfb;
    @BindView(R.id.tv_cashier_pay_wechat)
    TextView mTvPayWechat;
    @BindView(R.id.tv_cashier_pay_card)
    TextView mTvPayCard;
    @BindView(R.id.tv_cashier_pay_other)
    TextView mTvPayOther;
    @BindView(R.id.tv_cashier_pay_sum_money)
    TextView mTvSumMoney;
    @BindView(R.id.keyboard_view)
    NumKeyBoardView mKeyboardView;
    @BindView(R.id.btn_cashier_pay_ok)
    Button mPayOk;

    /**
     * 支付方式类型，默认现金
     */
    private int mPayType = PAY_TYPE_CAISHER;

    public interface onCommitOrderListener {
        void onCommit(int payType, int payMoney);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.cashier_pay_dialog, container, false);
        ButterKnife.bind(view);
        initView();
        return view;
    }

    private void initView() {
        mKeyboardView.attachTextView(mTvSumMoney);
    }

    @OnClick({R.id.tv_cashier_pay_cashier, R.id.tv_cashier_pay_zfb, R.id.tv_cashier_pay_wechat,
            R.id.tv_cashier_pay_card, R.id.tv_cashier_pay_other, R.id.btn_cashier_pay_ok})
    public void onViewClicked(View view) {
        resetTextBackground();
        switch (view.getId()) {
            case R.id.tv_cashier_pay_cashier:
                mPayType = PAY_TYPE_CAISHER;
                mTvPayCashier.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv_cashier_pay_zfb:
                mPayType = PAY_TYPE_ZFB;
                mTvPayZfb.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv_cashier_pay_wechat:
                mPayType = PAY_TYPE_WECHAT;
                mTvPayWechat.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv_cashier_pay_card:
                mPayType = PAY_TYPE_CARD;
                mTvPayCard.setBackgroundColor(Color.WHITE);
                break;
            case R.id.tv_cashier_pay_other:
                mPayType = PAY_TYPE_OTHER;
                mTvPayOther.setBackgroundColor(Color.WHITE);
                break;
            case R.id.btn_cashier_pay_ok:

                break;
        }

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

    public void setOrderNum(String orderNum) {
        mTvOrderNum.setText(orderNum);
    }

    public void setRemark(String remark) {
        mTvRemark.setText(remark);
    }

    public void setMember(String name) {
        mTvMember.setVisibility(View.VISIBLE);
        mTvMember.setText(name);
    }

    /**
     * <br> Description: 设置会员余额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/16 18:54
     */
    public void setMemberBalance(String name) {
        mTvMoney.setVisibility(View.VISIBLE);
        mTvMoney.setText(name);
    }

    /**
     * <br> Description: 设置固定支付总额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/16 18:57
     */
    public void setSumMoney(String sum) {
        mTvSum.setText(sum);
    }

    /**
     * <br> Description: 设置可变支付总额
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/16 18:57
     */
    public void setEditSumMoney(String sum) {
        mTvSum.setText(sum);
    }

}
