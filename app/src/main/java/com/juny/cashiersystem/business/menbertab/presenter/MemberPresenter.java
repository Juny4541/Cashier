package com.juny.cashiersystem.business.menbertab.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.juny.cashiersystem.base.BasePresenter;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.bean.RechargeBean;
import com.juny.cashiersystem.business.menbertab.contract.IMemberContract;
import com.juny.cashiersystem.business.menbertab.model.MemberRepository;
import com.juny.cashiersystem.util.TimeUtil;
import com.juny.cashiersystem.widget.AddDialog;

/**
 * <br> ClassName: MemberPresenter
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 22:07
 */

public class MemberPresenter extends BasePresenter<IMemberContract.IView>
        implements IMemberContract.IPresenter {
    private final static int ACTION_TYPE_ADD = 1;
    public final static int ACTION_TYPE_EDIT = 2;
    private MemberRepository mMemberRepository;

    public MemberPresenter() {
        mMemberRepository = new MemberRepository();
    }

    @Override
    public void getMemberData() {
        if (isViewAttached()) {
            getView().showMemberData(mMemberRepository.searchMemberData());
        }
    }


    public void showDeleteDialog(Activity activity, final int id, String content) {
        new AlertDialog.Builder(activity)
                .setMessage(content)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMemberRepository.deleteMember(id);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * <br> Description: 显示增加会员的对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 10:42
     */
    public void showAddDialog(Activity activity, String tag, String cardNum) {
        showMemberDialog(activity, tag, 0, ACTION_TYPE_ADD, cardNum);
    }

    /**
     * <br> Description: 显示编辑会员的对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 10:42
     */
    public void showEditDialog(Activity activity, String tag, int memberId) {
        showMemberDialog(activity, tag, memberId, ACTION_TYPE_EDIT, null);
    }

    /**
     * <br> Description: 显示会员的对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 10:42
     */
    public void showMemberDialog(Activity activity, String tag, final int memberId, final int actionType, final String cardNum) {
        AddDialog addDialog = new AddDialog();
        addDialog.setDialogType(AddDialog.DIALOG_TYPE_MEMBER);
        addDialog.show(activity.getFragmentManager(), tag);
        addDialog.setOnMemberAddListener(new AddDialog.OnMemberAddListener() {
            @Override
            public void onMemberAdd(String name, String phone) {
                // 新增会员
                if (ACTION_TYPE_ADD == actionType) {
                    MemberBean memberBean = new MemberBean();
                    memberBean.setName(name);
                    memberBean.setPhone(phone);
                    memberBean.setCardNum(cardNum); // 前缀 + 序号
                    mMemberRepository.addMember(memberBean);
                }

                // 会员电话或昵称修改
                if (ACTION_TYPE_EDIT == actionType && memberId != 0) {
                    MemberBean bean = mMemberRepository.updateMember(memberId, name, phone);
                    if (bean != null && isViewAttached()) {
                        getView().updateMemberData(bean);
                    }
                }
            }

            @Override
            public void onRecharge(int money, String remark) {
            }
        });
    }

    /**
     * <br> Description: 更新会员的选中状态
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 14:34
     */
    public MemberBean updateMember(int memberId, String isSelect) {
        return mMemberRepository.updateMember(memberId, isSelect);
    }

    /**
     * <br> Description: 显示充值对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 17:26
     */
    public void showRechargeDialog(Activity activity, String tag, final MemberBean memberBean) {
        AddDialog addDialog = new AddDialog();
        addDialog.setDialogType(AddDialog.DIALOG_TYPE_RECHARGE);
        addDialog.show(activity.getFragmentManager(), tag);
        addDialog.setOnMemberAddListener(new AddDialog.OnMemberAddListener() {
            @Override
            public void onMemberAdd(String name, String phone) {}

            @Override
            public void onRecharge(int money, String remark) {
                // 更新充值总额
                int rechargeSum = memberBean.getRechargeSum() + money;
                MemberBean bean = mMemberRepository.updateMember(memberBean.getId(), rechargeSum);
                if (bean != null && isViewAttached()) {
                    getView().updateMemberData(bean);
                }

                // 添加充值记录
                RechargeBean rechargeBean = new RechargeBean();
                rechargeBean.setMoeny(money);
                rechargeBean.setRemark(remark);
                rechargeBean.setTime(TimeUtil.getStringDate());
                mMemberRepository.addRecharge(rechargeBean);
            }
        });
    }

    /**
     * <br> Description: 关闭数据库
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/15 10:42
     */
    public void closeRealm() {
        mMemberRepository.closeRealm();
    }
}
