package com.juny.cashiersystem.business.homepage.formstab.view;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;

import butterknife.BindView;

/**
 * 报表 fragment
 * Created by Junny on 2018/3/5.
 */

public class FormsFragmentMain extends AbstractCSFragment {

    @BindView(R.id.rg_forms)
    RadioGroup mRg;
    @BindView(R.id.rb_forms_summary)
    RadioButton mRbSummary;
    @BindView(R.id.rb_forms_analysis)
    RadioButton mRbAnalysis;
    @BindView(R.id.rb_forms_rang)
    RadioButton mRbRang;
    @BindView(R.id.fl_forms_fragment_container)
    FrameLayout mFlContainer;

    private FormsAnalyzeFragment mAnalyzeFragment;
    private FormsSumFragment mSumFragment;
    private FormsRangFragment mRangFragment;

    @Override
    protected int getContentRes() {
        return R.layout.forms_fragment_main;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        // 初始化fragment
        mAnalyzeFragment = new FormsAnalyzeFragment();
        mSumFragment = new FormsSumFragment();
        mRangFragment = new FormsRangFragment();

        // 初始默认显示收银汇总fragment
        replaceFragment(mSumFragment, R.id.fl_forms_fragment_container);


        // 设置选中监听
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_forms_summary:
                        replaceFragment(mSumFragment, R.id.fl_forms_fragment_container);
                        break;
                    case R.id.rb_forms_analysis:
                        replaceFragment(mAnalyzeFragment, R.id.fl_forms_fragment_container);
                        break;
                    case R.id.rb_forms_rang:
                        replaceFragment(mRangFragment, R.id.fl_forms_fragment_container);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    /**
     * <br> Description: 替换fragment
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 15:11
     *
     * @param newFragment   新fragment
     * @param containLayout fragment容器
     */
    private void replaceFragment(Fragment newFragment, int containLayout) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containLayout, newFragment);
        transaction.commit();
    }
}
