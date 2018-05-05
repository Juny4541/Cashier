package com.juny.cashiersystem.business.homepage.formstab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.business.homepage.formstab.model.bean.AnalyzeBean;
import com.juny.cashiersystem.business.homepage.formstab.presenter.AnalyzeListAdapter;
import com.juny.cashiersystem.widget.DateSelectView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName:
 * <br> Description:  报表模块中的运营分析碎片
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 14:52
 */

public class FormsAnalyzeFragment extends Fragment {

    @BindView(R.id.dl_forms_date_view)
    DateSelectView mDateView;
    @BindView(R.id.et_forms_search)
    EditText mEtSearch;
    @BindView(R.id.rv_forms_analyze_list)
    EasyRecyclerView mRvList;
    Unbinder unbinder;

    private AnalyzeListAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.forms_fragment_analyze, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mListAdapter = new AnalyzeListAdapter(getContext());
        mListAdapter.addAll(generateList());
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mListAdapter);
    }


    /**
     * 暂时生成列表数据
     */
    private ArrayList<AnalyzeBean> generateList() {
        ArrayList<AnalyzeBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AnalyzeBean analyzeBean = new AnalyzeBean();
            analyzeBean.setDate("2018/0411");
            analyzeBean.setCount(45);
            analyzeBean.setSum(154202);
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
