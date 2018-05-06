package com.juny.cashiersystem.business.homepage.cashiertab.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.widget.NumKeyBoardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <br> ClassName: CashierPayActivity
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/5/5 22:22
 */

public class CashierPayActivity extends Activity{
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
    @BindView(R.id.tv_cashier_pay_type)
    TextView mTvType;
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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cashier_pay_dialog);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mKeyboardView.attachTextView(mTvSumMoney);
    }

    @OnClick({R.id.tv_cashier_pay_cashier, R.id.tv_cashier_pay_zfb, R.id.tv_cashier_pay_wechat, R.id.tv_cashier_pay_card, R.id.tv_cashier_pay_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cashier_pay_cashier:
                break;
            case R.id.tv_cashier_pay_zfb:
                break;
            case R.id.tv_cashier_pay_wechat:
                break;
            case R.id.tv_cashier_pay_card:
                break;
            case R.id.tv_cashier_pay_other:
                break;
        }
    }
}
