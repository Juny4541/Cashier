package com.juny.cashiersystem.business.formstab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.business.formstab.bean.AnalyzeBean;
import com.juny.cashiersystem.business.formstab.presenter.AnalyzeListAdapter;
import com.juny.cashiersystem.widget.DateSelectView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * <br> ClassName:
 * <br> Description:  报表模块中的日运营分析碎片
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 14:52
 */

public class FormsAnalyzeFragment extends Fragment {

    @BindView(R.id.dl_forms_date_view)
    DateSelectView mDateView;
    @BindView(R.id.rv_forms_analyze_list)
    EasyRecyclerView mRvList;
    Unbinder unbinder;

    private AnalyzeListAdapter mListAdapter;
    private Realm mRealm;
    private RealmResults<OrderBean> mOrderResults;

    /**
     * 所有日期的运营列表
     */
    private ArrayList<AnalyzeBean> mAllAnalyzeBeansList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_analyze, null);
        unbinder = ButterKnife.bind(this, view);
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
        mOrderResults = mRealm.where(OrderBean.class).findAll();
        init();
        return view;
    }

    private void init() {
        mListAdapter = new AnalyzeListAdapter(getContext());
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mListAdapter);
        // 初次加载全部数据
        mListAdapter.addAll(generateList());

        mDateView.setOnDateSelectListener(new DateSelectView.OnDateSelectListener() {
            @Override
            public void endDateSelected(String beginDate, String endDate) {
                mListAdapter.clear();
                mListAdapter.addAll(generateList(beginDate, endDate));
            }
        });
    }


    /**
     * <br> Description:  显示全部日汇总
     * <br> Author: Juny
     * <br> Date:  2018/5/18  19:15
     */
    private ArrayList<AnalyzeBean> generateList() {
        if (mAllAnalyzeBeansList == null) {
            mAllAnalyzeBeansList = new ArrayList<>();
        } else {
            mAllAnalyzeBeansList.clear();
        }

        if (mOrderResults.size() == 0){
            return null;
        }

        // 根据订单数据集合查询
        ArrayList<String> dates = new ArrayList<>();
        dates.add(mOrderResults.get(0).getDate());  // 初始化
        for (int i = 0; i < dates.size(); i++) {
            int sumMoney = 0;
            int count = 0;
            // 去重
            for (int j = 0; j < mOrderResults.size(); j++) {
                if (dates.get(i).equals(mOrderResults.get(j).getDate())) {
                    count++;
                    sumMoney = sumMoney + mOrderResults.get(i).getAmount();
                } else {
                    dates.add(mOrderResults.get(i).getDate());
                }
            }
            // 添加数据
            AnalyzeBean analyzeBean = new AnalyzeBean();
            analyzeBean.setDate(dates.get(i));
            analyzeBean.setCount(count);
            analyzeBean.setSum(sumMoney);
            mAllAnalyzeBeansList.add(analyzeBean);
        }
        return mAllAnalyzeBeansList;
    }

    /**
     * <br> Description:  根据日期查询营业情况
     * <br> Author: Juny
     * <br> Date:  2018/5/18  19:14
     */
    private ArrayList<AnalyzeBean> generateList(String beginDate, String endDate) {
        ArrayList<AnalyzeBean> list = new ArrayList<>();
        int beginIndex = 0;
        int endIndex = 0;

        // 确定始末索引
        for (int i = 0; i < mAllAnalyzeBeansList.size(); i++) {
            if (beginDate.equals(mAllAnalyzeBeansList.get(i).getDate())) {
                beginIndex = i;
            }
            if (endDate.equals(mAllAnalyzeBeansList.get(i).getDate())) {
                endIndex = i;
            }
        }

        // 添加到列表
        for (int i = beginIndex; i < endIndex + 1; i++) {
            list.add(i, mAllAnalyzeBeansList.get(i));
        }
        return list;
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
