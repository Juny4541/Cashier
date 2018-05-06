package com.juny.cashiersystem.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.util.UiUtil;

/**
 * <br> ClassName: NumKeyBoard
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/5/5 23:32
 */

public class NumKeyBoardView extends GridView {

    private String[] keyNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", ""};
    private KeyboardListAdapter mAdapter;

    /**
     * 现价价格的TextView
     */
    private TextView mTextView;


    public NumKeyBoardView(@NonNull Context context) {
        super(context, null);
    }

    public NumKeyBoardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        setNumColumns(3); // 3列
        setColumnWidth(this.getWidth() / 3);
        setHorizontalSpacing(UiUtil.dp2px(2));
        setVerticalSpacing(UiUtil.dp2px(2));
        mAdapter = new KeyboardListAdapter();
        setAdapter(mAdapter);
        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mTextView == null) {
                    return;
                }
                StringBuilder priceStr = new StringBuilder(mTextView.getText().toString());
                switch (position) {
                    case 11:
                        if (priceStr.length() > 0) {
                            priceStr.deleteCharAt(priceStr.length() - 1);
                        }
                        break;
                    case 9:
                        if (priceStr.length() > 0) {
                            priceStr.append(keyNum[9]);
                        }
                        break;
                    default:
                        priceStr.append(keyNum[position]);
                        break;
                }
                mTextView.setText(priceStr);
            }
        });
    }

    /**
     * <br> Description:  与TextView 关联
     * <br> Author: Juny
     * <br> Date:  2018/5/6  16:09
     */
    public void attachTextView(TextView textView) {
        mTextView = textView;
    }

    /**
     * <br> ClassName: KeyboardListAdapter
     * <br> Description: GridView 适配器
     * <br> Author: Juny
     * <br> Date:  2018/5/5 23:32
     */
    private class KeyboardListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (keyNum == null) {
                return 0;
            }
            return keyNum.length;
        }

        @Override
        public Object getItem(int position) {
            return keyNum[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(parent.getContext());
                textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtil.dp2px(50)));
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(UiUtil.dp2px(20));

                // 最后一个为删除键
                if (position == keyNum.length - 1) {
                    Drawable drawableTop = getResources().getDrawable(R.mipmap.cashier_pay_delete);
                    textView.setCompoundDrawablesWithIntrinsicBounds(null,
                            drawableTop, null, null);
                    textView.setCompoundDrawablePadding(0);
                    textView.setPadding(0, UiUtil.dp2px(10), 0, 0);
                }


            } else {
                textView = (TextView) convertView;
            }
            textView.setText(keyNum[position]);
            return textView;
        }
    }
}
