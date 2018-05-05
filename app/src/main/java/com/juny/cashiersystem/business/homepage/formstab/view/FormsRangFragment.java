package com.juny.cashiersystem.business.homepage.formstab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.business.homepage.formstab.model.bean.SaleRangBean;
import com.juny.cashiersystem.business.homepage.formstab.presenter.SaleRangAdapter;
import com.juny.cashiersystem.widget.DateSelectView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName:
 * <br> Description:  报表模块中的销售排行碎片
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 14:53
 */

public class FormsRangFragment extends Fragment {
    @BindView(R.id.dl_forms_date_view)
    DateSelectView mDateView;
    @BindView(R.id.et_forms_search)
    EditText mEtSearch;
    @BindView(R.id.tv_forms_row_one)
    TextView mTvRowOne;
    @BindView(R.id.tv_forms_row_two)
    TextView mTvRowTwo;
    @BindView(R.id.tv_forms_row_three)
    TextView mTvRowThree;
    @BindView(R.id.rv_forms_analyze_list)
    EasyRecyclerView mRvList;
    Unbinder unbinder;

    private SaleRangAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_analyze, null); // 服用运营分析列表的布局文件
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mAdapter = new SaleRangAdapter(getContext());
        mAdapter.addAll(generateList());
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);

        // 修改表格列名
        mTvRowOne.setText(R.string.forms_goods_name);
        mTvRowTwo.setText(R.string.forms_goods_sale_count);
        mTvRowThree.setText(R.string.forms_goods_sale_sum);
    }

    /**
     * 暂时生成列表数据
     */
    private ArrayList<SaleRangBean> generateList() {
        ArrayList<SaleRangBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SaleRangBean analyzeBean = new SaleRangBean();
            analyzeBean.setGoodsName("2018/0411");
            analyzeBean.setSaleCount(45);
            analyzeBean.setSaleSum(154202);
            list.add(analyzeBean);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
