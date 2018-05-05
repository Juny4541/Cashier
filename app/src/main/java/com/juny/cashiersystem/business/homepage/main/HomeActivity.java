package com.juny.cashiersystem.business.homepage.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSActivity;
import com.juny.cashiersystem.business.homepage.cashiertab.view.CashierFragment;
import com.juny.cashiersystem.business.homepage.formstab.view.FormsFragmentMain;
import com.juny.cashiersystem.business.homepage.menbertab.view.MemberFragment;
import com.juny.cashiersystem.business.homepage.ordertab.view.OrderFragment;
import com.juny.cashiersystem.business.homepage.settingtab.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 * Created by Junny on 2018/3/5.
 */

public class HomeActivity extends AbstractCSActivity {

    private static final int CASHIER = 0;
    private static final int ORDER = 1;
    private static final int FORMS = 2;
    private static final int MEMBER = 3;
    private static final int SETTING = 4;

    @BindView(R.id.tab_cashier)
    TextView mCashier;
    @BindView(R.id.tab_order)
    TextView mOrder;
    @BindView(R.id.tab_forms)
    TextView mForms;
    @BindView(R.id.tab_member)
    TextView mMember;
    @BindView(R.id.tab_settings)
    TextView mSettings;
    @BindView(R.id.home_fragment_layout)
    FrameLayout mFragmentLayout;

    private Fragment mCashierFrag;
    private Fragment mOrderFrag;
    private Fragment mFormsFrag;
    private Fragment mMemberFrag;
    private Fragment mSettingFrag;


    public static void startActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        // 初始化UI
        setSelect(CASHIER);

        // 初始化监听
        mCashier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(CASHIER);
            }
        });
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(ORDER);
            }
        });
        mForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(FORMS);
            }
        });
        mMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(MEMBER);
            }
        });
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect(SETTING);
            }
        });
    }

    /**
     * 设置选中的页卡
     */
    private void setSelect(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 先隐藏fragment，再显示新的
        hideFragment(transaction);
        // 重置tab图片为默认图
        resetImage();
        switch (i) {
            case CASHIER:
                changeImage(mCashier, R.mipmap.ic_home_cash);
                if (mCashierFrag == null) {
                    mCashierFrag = new CashierFragment();
                    transaction.add(R.id.home_fragment_layout, mCashierFrag);
                } else {
                    transaction.show(mCashierFrag);
                }
                break;
            case ORDER:
                changeImage(mOrder, R.mipmap.ic_home_order);
                if (mOrderFrag == null) {
                    mOrderFrag = new OrderFragment();
                    transaction.add(R.id.home_fragment_layout, mOrderFrag);
                } else {
                    transaction.show(mOrderFrag);
                }
                break;
            case FORMS:
                changeImage(mForms, R.mipmap.ic_home_forms);
                if (mFormsFrag == null) {
                    mFormsFrag = new FormsFragmentMain();
                    transaction.add(R.id.home_fragment_layout, mFormsFrag);
                } else {
                    transaction.show(mFormsFrag);
                }
                break;
            case MEMBER:
                changeImage(mMember, R.mipmap.ic_home_menbers);
                if (mMemberFrag == null) {
                    mMemberFrag = new MemberFragment();
                    transaction.add(R.id.home_fragment_layout, mMemberFrag);
                } else {
                    transaction.show(mMemberFrag);
                }
                break;
            case SETTING:
                changeImage(mSettings, R.mipmap.ic_home_setting);
                if (mSettingFrag == null) {
                    mSettingFrag = new SettingFragment();
                    transaction.add(R.id.home_fragment_layout, mSettingFrag);
                } else {
                    transaction.show(mSettingFrag);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏fragment
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mCashierFrag != null) {
            transaction.hide(mCashierFrag);
        }
        if (mOrderFrag != null) {
            transaction.hide(mOrderFrag);
        }
        if (mFormsFrag != null) {
            transaction.hide(mFormsFrag);
        }
        if (mMemberFrag != null) {
            transaction.hide(mMemberFrag);
        }
        if (mSettingFrag != null) {
            transaction.hide(mSettingFrag);
        }
    }

    /**
     * 重置tab的图片为默认状态
     */
    private void resetImage() {
        changeImage(mCashier, R.mipmap.ic_home_cash_n);
        changeImage(mOrder, R.mipmap.ic_home_order_n);
        changeImage(mForms, R.mipmap.ic_home_forms_n);
        changeImage(mMember, R.mipmap.ic_home_menbers_n);
        changeImage(mSettings, R.mipmap.ic_home_setting_n);
    }

    /**
     * 更换tab的图片
     */
    private void changeImage(TextView textView, int drawableID) {
        Drawable drawable = getResources().getDrawable(drawableID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }


}
