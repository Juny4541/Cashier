package com.juny.cashiersystem.business.formstab.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.business.cashiertab.view.widget.PayDialogFragment;
import com.juny.cashiersystem.util.ResourceUtil;
import com.juny.cashiersystem.util.UiUtil;
import com.juny.cashiersystem.widget.FormsSummaryView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

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

    private Realm mRealm;
    private RealmResults<OrderBean> mOrderBeanResults;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_sum, null);
        unbinder = ButterKnife.bind(this, view);
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
        mOrderBeanResults = mRealm.where(OrderBean.class).findAll();
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

        // 设置数据
        setFlSumCount();
        setFlSumCash();
        setFlPayCash();
        setFlPayMemberCard();
        setFlPayWechat();
        setFlPayZfb();

    }

    /**
     * <br> Description:  设置订单数
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlSumCount() {
        mFlSumCount.setExplainText(ResourceUtil.getString(R.string.forms_order_sum));
        mFlSumCount.setSumText(String.valueOf(mOrderBeanResults.size()));
    }

    /**
     * <br> Description:  设置订单总额
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlSumCash() {
        mFlSumCash.setExplainText(ResourceUtil.getString(R.string.forms_total_business));
        int sum = mOrderBeanResults.sum("amount").intValue();
        mFlSumCash.setSumText(String.valueOf(sum));
    }

    /**
     * <br> Description:  设置现金收入
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlPayCash() {
        mFlPayCash.setExplainText(ResourceUtil.getString(R.string.forms_cash_earnings));
        int payCash = 0;
        for (int i = 0; i < mOrderBeanResults.size(); i++) {
            if (mOrderBeanResults.get(i).getPayType() == PayDialogFragment.PAY_TYPE_CASHIER) {
                payCash = payCash + mOrderBeanResults.get(i).getAmount();
            }
        }
        mFlPayCash.setSumText(String.valueOf(payCash));
    }

    /**
     * <br> Description:  设置会员卡消费总额
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlPayMemberCard() {
        mFlPayMemberCard.setExplainText(ResourceUtil.getString(R.string.forms_member_card));
        int payCard = 0;
        for (int i = 0; i < mOrderBeanResults.size(); i++) {
            if (mOrderBeanResults.get(i).getPayType() == PayDialogFragment.PAY_TYPE_CARD) {
                payCard = payCard + mOrderBeanResults.get(i).getAmount();
            }
        }
        mFlPayMemberCard.setSumText(String.valueOf(payCard));
    }

    /**
     * <br> Description:  设置支付宝消费总额
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlPayZfb() {
        int payZFB = 0;
        for (int i = 0; i < mOrderBeanResults.size(); i++) {
            if (mOrderBeanResults.get(i).getPayType() == PayDialogFragment.PAY_TYPE_ZFB) {
                payZFB = payZFB + mOrderBeanResults.get(i).getAmount();
            }
        }
        mFlPayZfb.setSumText(String.valueOf(payZFB));
    }

    /**
     * <br> Description:  设置会微信收入总额
     * <br> Author: Juny
     * <br> Date:  2018/5/18  17:42
     */
    private void setFlPayWechat() {
        mFlPayWechat.setExplainText(ResourceUtil.getString(R.string.forms_wehat_earnings));
        int payWechat = 0;
        for (int i = 0; i < mOrderBeanResults.size(); i++) {
            if (mOrderBeanResults.get(i).getPayType() == PayDialogFragment.PAY_TYPE_WECHAT) {
                payWechat = payWechat + mOrderBeanResults.get(i).getAmount();
            }
        }
        mFlPayWechat.setSumText(String.valueOf(payWechat));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
