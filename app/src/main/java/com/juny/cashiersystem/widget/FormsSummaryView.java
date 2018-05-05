package com.juny.cashiersystem.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juny.cashiersystem.R;

/**
 * <br> ClassName: FormsSummaryView
 * <br> Description:
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/10 15:26
 */

public class FormsSummaryView extends LinearLayout {
    private TextView mTvSumMoney;
    private TextView mTvExplain;

    public FormsSummaryView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FormsSummaryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.forms_summary_show_view, this, true);
        mTvSumMoney = findViewById(R.id.tv_sum_money);
        mTvExplain = findViewById(R.id.tv_explain);
        setOrientation(VERTICAL);
    }

    /**
     * <br> Description: 设置说明文本的左侧图标
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 15:39
     *
     * @param drawableId 图标资源Id
     */
    public void setTextDrawableLeft(int drawableId) {
        Drawable left = getResources().getDrawable(drawableId);
        left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());// 一定要设置setBounds();
        mTvExplain.setCompoundDrawables(left, null, null, null);
    }

    /**
     * <br> Description: 设置总额文本的字体大小
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 15:40
     */
    public void setSumTextSize(int size) {
        mTvSumMoney.setTextSize(size);
    }

    /**
     * <br> Description: 设置总额文本颜色
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/10 15:42
     */
    public void setSumTextColor(int color) {
        mTvSumMoney.setTextColor(color);
    }

    public void setSumText(String sum) {
        mTvSumMoney.setText(sum);
    }

    public void setExplainText(String explain) {
        mTvExplain.setText(explain);
    }
}
