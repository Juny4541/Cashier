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
import com.juny.cashiersystem.business.homepage.menbertab.presenter.MemberPresenter;
import com.juny.cashiersystem.realm.bean.MemberBean;
import com.juny.cashiersystem.util.ResourceUtil;
import com.juny.cashiersystem.widget.FormsSummaryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

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
    @BindView(R.id.tv_member_recharge_btn)
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

    private MemberPresenter mMemberPresenter;
    private MemberListAdapter mAdapter;

    /**
     * 默认选中第一个会员
     */
    private int mSelectIndex = 0;
    /**
     *  单签选中得会员id
     */
    private int mCurrentMemberId;

    /**
     * 缓存会员列表
     */
    private RealmResults<MemberBean> mMenberResults;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMemberPresenter.closeRealm();
    }

    @Override
    protected List createPresenter() {
        List<MemberPresenter> presenters = new ArrayList<>();
        mMemberPresenter = new MemberPresenter();
        presenters.add(mMemberPresenter);
        return presenters;
    }


    @Override
    protected int getContentRes() {
        return R.layout.member_fragment_main;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MemberListAdapter(mActivity);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 将上次选中的设置为未选中
                mAdapter.update(mMemberPresenter.updateMember(mMenberResults.get(mSelectIndex).getId(), "false"), mSelectIndex);

                // 新点击的设置为选中状态
                MemberBean memberBean = mMemberPresenter.updateMember(mMenberResults.get(position).getId(), "true");
                if (memberBean != null){
                    mAdapter.update(memberBean, position);
                    updateMemberData(memberBean);
                    // 记录此次选中的位置
                    mSelectIndex = position;
                    mCurrentMemberId = memberBean.getId();
                }
            }
        });
        mAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                // 长按删除
                mMemberPresenter.showDeleteDialog(mActivity, mMenberResults.get(position).getId(), "确定删除该会员？");
                return false;
            }
        });

        // 设置文案
        mViewTotalPay.setExplainText(ResourceUtil.getString(R.string.member_pay_total));
        mViewTotalBuy.setExplainText(ResourceUtil.getString(R.string.member_buy_total));

        // 查询并显示会员列表信息
        mMemberPresenter.getMemberData();
    }

    @Override
    public void showMemberData(RealmResults<MemberBean> memberList) {
        mMenberResults = memberList;
        // 初次打开显示选中状态的会员信息
        for (int i = 0; i < mMenberResults.size(); i++) {
            if ("true".equals(mMenberResults.get(i).getSelect())) {
                updateMemberData(mMenberResults.get(i));
                mCurrentMemberId = mMenberResults.get(i).getId();
            }
        }

        mAdapter.addAll(mMenberResults);
        memberList.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<MemberBean>>() {
            @Override
            public void onChange(RealmResults<MemberBean> collection, OrderedCollectionChangeSet changeSet) {
                int[] insertIndexes = changeSet.getInsertions();
                int[] deleteIndexes = changeSet.getDeletions();
                // 插入
                if (insertIndexes.length > 0) {
                    for (int i = 0; i < insertIndexes.length; i++) {
                        mAdapter.insert(collection.get(insertIndexes[i]), 0);
                    }
                }
                // 删除
                if (deleteIndexes.length > 0) {
                    for (int i = 0; i < deleteIndexes.length; i++) {
                        mAdapter.remove(deleteIndexes[i]);
                    }
                }
            }
        });
    }

    /**
     * <br> Description: 更新选被选中会员的信息
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 15:16
     */
    @Override
    public void updateMemberData(MemberBean memberBean) {
        mTvBalance.setText(String.valueOf(memberBean.getRechargeSum()));
        mTvCardNum.setText(memberBean.getCardNum());
        mTvName.setText(memberBean.getName());
        mTvPhone.setText(memberBean.getPhone());
        mViewTotalPay.setSumText(String.valueOf(memberBean.getRechargeSum()));
    }

    @OnClick({R.id.iv_member_add, R.id.tv_member_edit, R.id.tv_member_record_buy,
            R.id.tv_member_record_order, R.id.tv_member_record_pay, R.id.tv_member_recharge_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_member_add:
                String cardNum = "CS888" + (mMenberResults.size() + 1); // 生成卡号：前缀+后缀
                mMemberPresenter.showAddDialog(mActivity, "添加会员", cardNum);
                break;
            case R.id.tv_member_edit:
                mMemberPresenter.showEditDialog(mActivity, "编辑会员", mCurrentMemberId);
                break;
            case R.id.tv_member_record_buy:
                break;
            case R.id.tv_member_record_order:
                break;
            case R.id.tv_member_record_pay:
                break;
            case R.id.tv_member_recharge_btn:
                mMemberPresenter.showRechargeDialog(mActivity, "充值", mMenberResults.get(mSelectIndex));
                break;
        }
    }
}
