package com.juny.cashiersystem.business.menbertab.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.juny.cashiersystem.bean.MemberBean;
import com.juny.cashiersystem.business.menbertab.view.MemberViewHolder;

/**
 * <br> ClassName: MemberListAdapter
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/11 22:13
 */

public class MemberListAdapter extends RecyclerArrayAdapter<MemberBean> {
    public MemberListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberViewHolder(parent);
    }
}
