package com.juny.cashiersystem.widget;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.juny.cashiersystem.R;

/**
 * <br> ClassName: AddDialog
 * <br> Description:  自定义视图对话框的封装
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/10 10:01
 */

public class AddDialog extends DialogFragment {

    /**
     * 对话框类型：添加 -> CATEGORY 分类，GOODS 商品，MEMBER 会员
     */
    public final static int DIALOG_TYPE_CATEGORY = 1;
    public final static int DIALOG_TYPE_GOODS = 2;
    public final static int DIALOG_TYPE_MEMBER = 3;

    private Context mContext;
    /**
     * 对话框类型，默认为 CATEGORY
     */
    private int mDialogType = 1;

    private OnCashierAddListener mCategoryListener;
    private OnMemberAddListener mMemberListener;

    public interface OnCashierAddListener {
        void onCategoryAdd(String name);

        void onGoodsAdd(String name, int price, int repertory);
    }

    public interface OnMemberAddListener {
        void onMemberAdd(String name, String phone);
    }

    public void setOnCashierAddListener(OnCashierAddListener listener) {
        mCategoryListener = listener;
    }

    public void setOnMemberAddListener(OnMemberAddListener listener) {
        mMemberListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view;
        switch (mDialogType) {
            case DIALOG_TYPE_CATEGORY:
                view = inflater.inflate(R.layout.cashier_category_add_dialog, container, false);
                initCategoryDialog(view);
                break;
            case DIALOG_TYPE_GOODS:
                view = inflater.inflate(R.layout.cashier_goods_add_dialog, container, false);
                initGoodsDialog(view);
                break;
            case DIALOG_TYPE_MEMBER:
                view = inflater.inflate(R.layout.member_add_dialog, container, false);
                initMemberDialog(view);
                break;
            default:
                view = null;
                break;
        }
        return view;
    }

    /**
     * <br> Description: 设置dialog 的类型，调用init之前需要先调用setDialogType
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 10:49
     */
    public AddDialog setDialogType(int dialogType) {
        mDialogType = dialogType;
        return this;
    }

    /**
     * <br> Description: 初始化添加分类对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 10:30
     */
    private void initCategoryDialog(View view) {
        final EditText name = view.findViewById(R.id.et_cashier_category_name);
        TextView ok = view.findViewById(R.id.tv_ok);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCategoryListener != null) {
                    String nameStr = name.getText().toString();
                    mCategoryListener.onCategoryAdd(nameStr);
                }
                dismiss();
            }
        });
    }

    /**
     * <br> Description: 初始化添加商品对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 10:30
     */
    private void initGoodsDialog(View view) {
        final EditText name = view.findViewById(R.id.et_cashier_goods_add_name);
        final EditText price = view.findViewById(R.id.et_cashier_goods_price);
        final EditText repertory = view.findViewById(R.id.et_cashier_goods_repertory);
        TextView ok = view.findViewById(R.id.tv_ok);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCategoryListener != null) {
                    String nameStr = name.getText().toString();
                    String priceStr = price.getText().toString();
                    String repertoryStr = repertory.getText().toString();
                    mCategoryListener.onGoodsAdd(nameStr, Integer.parseInt(priceStr), Integer.parseInt(repertoryStr));
                }
                dismiss();
            }
        });
    }

    /**
     * <br> Description: 初始化添加会员对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/10 10:30
     */
    private void initMemberDialog(View view) {
        final EditText name = view.findViewById(R.id.et_member_add_name);
        final EditText phone = view.findViewById(R.id.et_member_phone);
        TextView ok = view.findViewById(R.id.tv_ok);
        TextView cancel = view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMemberListener != null) {
                    String nameStr = name.getText().toString();
                    String phoneStr = phone.getText().toString();
                    mMemberListener.onMemberAdd(nameStr, phoneStr);
                }
            }
        });

    }
}
