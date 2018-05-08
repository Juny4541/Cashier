package com.juny.cashiersystem.business.homepage.menbertab.view;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;
import com.juny.cashiersystem.business.homepage.menbertab.contract.IMemberContract;
import com.juny.cashiersystem.business.homepage.menbertab.presenter.MemberListAdapter;
import com.juny.cashiersystem.realm.bean.MemberBean;
import com.juny.cashiersystem.util.ResourceUtil;
import com.juny.cashiersystem.widget.FormsSummaryView;

import java.util.List;

import butterknife.BindView;

/**
 * 会员 fragment
 * Created by Junny on 2018/3/5.
 */

public class MemberFragment extends AbstractCSFragment implements IMemberContract.IView {

    @BindView(R.id.et_member_search)
    EditText mEtSearch;
    @BindView(R.id.iv_member_add)
    ImageView mIvAdd;
    @BindView(R.id.rv_member_list)
    EasyRecyclerView mRvList;
    @BindView(R.id.tv_member_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_member_balance_text)
    TextView mTvBalanceText;
    @BindView(R.id.tv_member_edit)
    TextView mTvEdit;
    @BindView(R.id.tv_member_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_member_card_num)
    TextView mTvCardNum;
    @BindView(R.id.tv_member_name)
    TextView mTvName;
    @BindView(R.id.view_member_total_pay)
    FormsSummaryView mViewTotalPay;
    @BindView(R.id.view_member_total_buy)
    FormsSummaryView mViewTotalBuy;
    @BindView(R.id.tv_member_record_buy)
    TextView mTvRecordBuy;
    @BindView(R.id.tv_member_record_order)
    TextView mTvRecordOrder;
    @BindView(R.id.tv_member_record_pay)
    TextView mTvRecordPay;

    private MemberListAdapter mAdapter;

    private int mSelectIndex;
    private List<MemberBean> mList;

    @Override
    protected int getContentRes() {
        return R.layout.member_fragment_main;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MemberListAdapter(mActivity);
//        mList = generateList();
//        mAdapter.addAll(generateList());
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                // 更新选中 的状态
//                mList.get(mSelectIndex).setSelected(false); // 将上次选中的设置为未选中
//                mAdapter.update(mList.get(mSelectIndex), mSelectIndex);
//
//                mList.get(position).setSelected(true); // 新点击的设置为选中状态
//                mAdapter.update(mList.get(position), position);
//                CSToast.showToast("select" + String.valueOf(position));
//
//                mSelectIndex = position; // 记录此次选中的位置
            }
        });

        // 设置文案
        mViewTotalPay.setExplainText(ResourceUtil.getString(R.string.member_pay_total));
        mViewTotalBuy.setExplainText(ResourceUtil.getString(R.string.member_buy_total));
    }

//    private ArrayList<MemberBean> generateList() {
//        ArrayList<MemberBean> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            MemberBean memberBean = new MemberBean();
//            memberBean.setMemberId(String.valueOf(i));
//            memberBean.setName("小芳");
//            memberBean.setCardNum("45454");
//            memberBean.setPhoneNum("124564234157");
//            // 默认选中第一个
//            if (i == 0) {
//                memberBean.setSelected(true);
//            }
//            memberBean.setSelected(false);
//            list.add(memberBean);
//        }
//        return list;
//    }
}
