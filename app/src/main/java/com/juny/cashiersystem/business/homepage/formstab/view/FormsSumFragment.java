package com.juny.cashiersystem.business.homepage.formstab.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.util.ResourceUtil;
import com.juny.cashiersystem.util.UiUtil;
import com.juny.cashiersystem.widget.FormsSummaryView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName:
 * <br> Description:  报表模块中的收银汇总碎片
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 14:52
 */

public class FormsSumFragment extends Fragment {
    @BindView(R.id.fl_sum_count)
    FormsSummaryView mFlSumCount;
    @BindView(R.id.fl_sum_cash)
    FormsSummaryView mFlSumCash;
    @BindView(R.id.fl_pay_cash)
    FormsSummaryView mFlPayCash;
    @BindView(R.id.fl_pay_member_card)
    FormsSummaryView mFlPayMemberCard;
    @BindView(R.id.fl_pay_zfb)
    FormsSummaryView mFlPayZfb;
    @BindView(R.id.fl_pay_wechat)
    FormsSummaryView mFlPayWechat;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_sum, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        // 设置图标
        mFlPayCash.setTextDrawableLeft(R.mipmap.forms_ic_cash);
        mFlPayMemberCard.setTextDrawableLeft(R.mipmap.forms_ic_member_card);
        mFlPayZfb.setTextDrawableLeft(R.mipmap.forms_ic_zfb);
        mFlPayWechat.setTextDrawableLeft(R.mipmap.forms_ic_wechat);

        // 设置字体
        mFlSumCash.setSumTextSize(UiUtil.dp2px(30));
        mFlSumCount.setSumTextSize(UiUtil.dp2px(30));
        mFlSumCash.setSumTextColor(Color.RED);
        mFlSumCount.setSumTextColor(Color.RED);

        mFlSumCount.setExplainText(ResourceUtil.getString(R.string.forms_order_sum));
        mFlSumCash.setExplainText(ResourceUtil.getString(R.string.forms_total_business));
        mFlPayCash.setExplainText(ResourceUtil.getString(R.string.forms_cash_earnings));
        mFlPayMemberCard.setExplainText(ResourceUtil.getString(R.string.forms_member_card));
        mFlPayWechat.setExplainText(ResourceUtil.getString(R.string.forms_wehat_earnings));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
