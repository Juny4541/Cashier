package com.juny.cashiersystem.business.formstab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.juny.cashiersystem.CSApplication;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.bean.GoodsBean;
import com.juny.cashiersystem.bean.OrderGoodsBean;
import com.juny.cashiersystem.business.formstab.bean.SaleRangBean;
import com.juny.cashiersystem.business.formstab.presenter.AnalyzeListAdapter;
import com.juny.cashiersystem.business.formstab.presenter.SaleRangAdapter;
import com.juny.cashiersystem.widget.DateSelectView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * <br> ClassName:
 * <br> Description:  报表模块中的销售排行
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 14:53
 */

public class FormsRangFragment extends Fragment {
    @BindView(R.id.dl_forms_date_view)
    DateSelectView mDateView;
    @BindView(R.id.tv_forms_row_one)
    TextView mTvRowOne;
    @BindView(R.id.tv_forms_row_two)
    TextView mTvRowTwo;
    @BindView(R.id.tv_forms_row_three)
    TextView mTvRowThree;
    @BindView(R.id.rv_forms_analyze_list)
    EasyRecyclerView mRvList;
    Unbinder unbinder;

    private com.juny.cashiersystem.business.formstab.presenter.SaleRangAdapter mAdapter;

    private AnalyzeListAdapter mListAdapter;
    private Realm mRealm;
    private RealmResults<OrderGoodsBean> mOrderResults;
    private RealmResults<GoodsBean> mGoodsResults;

    /**
     * 所有商品的销售列表
     */
    private ArrayList<SaleRangBean> mAllSaleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_analyze, null); // 服用运营分析列表的布局文件
        unbinder = ButterKnife.bind(this, view);
        mRealm = Realm.getInstance(CSApplication.getRealmConfiguration());
        mOrderResults = mRealm.where(OrderGoodsBean.class).findAll();
        mGoodsResults = mRealm.where(GoodsBean.class).findAll();
        init();
        return view;
    }

    private void init() {
        mAdapter = new SaleRangAdapter(getContext());
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);
        mAdapter.addAll(generateList());

        // 修改表格列名
        mTvRowOne.setText(R.string.forms_goods_name);
        mTvRowTwo.setText(R.string.forms_goods_sale_count);
        mTvRowThree.setText(R.string.forms_goods_sale_sum);

        mDateView.setOnDateSelectListener(new DateSelectView.OnDateSelectListener() {
            @Override
            public void endDateSelected(String beginDate, String endDate) {
                mAdapter.clear();
                mAdapter.addAll(generateList(beginDate, endDate));
            }
        });
    }

    /**
     * <br> Description:  显示全部汇总
     * <br> Author: Juny
     * <br> Date:  2018/5/18  19:15
     */
    private ArrayList<SaleRangBean> generateList() {
        if (mAllSaleList == null) {
            mAllSaleList = new ArrayList<>();
        }else {
            mAllSaleList.clear();
        }
        for (int i = 0; i < mGoodsResults.size(); i++) {
            String name = mOrderResults.get(i).getName();
            int sumMoney = 0;
            int count = 0;
            for (int j = 0; j < mOrderResults.size(); j++) {
                if (name.equals(mOrderResults.get(i).getName())) {
                    count = count + mOrderResults.get(i).getCount();
                }
            }
            SaleRangBean bean = new SaleRangBean();
            bean.setGoodsName(name);
            sumMoney = sumMoney + mOrderResults.get(i).getPrice() * count;
            bean.setSaleCount(count);
            bean.setSaleSum(sumMoney);
            mAllSaleList.add(bean);
        }
        return mAllSaleList;
    }

    /**
     * <br> Description:  根据日期查询营业情况
     * <br> Author: Juny
     * <br> Date:  2018/5/18  19:14
     */
    private ArrayList<SaleRangBean> generateList(String beginDate, String endDate) {
        ArrayList<SaleRangBean> list = new ArrayList<>();
        int beginIndex = 0;
        int endIndex = 0;

        // 确定始末索引
        for (int i = 0; i < mAllSaleList.size(); i++) {
            if (beginDate.equals(mAllSaleList.get(i).getGoodsName())) {
                beginIndex = i;
            }
            if (endDate.equals(mAllSaleList.get(i).getGoodsName())) {
                endIndex = i;
            }
        }
        // 添加到列表
        for (int i = beginIndex; i < endIndex + 1; i++) {
            list.add(i, mAllSaleList.get(i));
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
