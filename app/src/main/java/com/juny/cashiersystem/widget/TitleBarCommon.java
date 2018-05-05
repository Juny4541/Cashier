
package com.juny.cashiersystem.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.juny.cashiersystem.R;


/**
 * 通用的标题栏
 */
public class TitleBarCommon extends FrameLayout {

    /**
     * UI元素
     */
    private TextView mLeftText;
    private TextView mRightText;
    private TextView mTitleText;

    public TitleBarCommon(Context context) {
        super(context);
        initUI(context);
    }

    public TitleBarCommon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context ctx) {
        LayoutInflater.from(ctx).inflate(R.layout.common_titlebar, this);

        mLeftText = (TextView) findViewById(R.id.left_text);
        mRightText = (TextView) findViewById(R.id.right_text);
        mTitleText = (TextView) findViewById(R.id.title_text);

    }

    /**
     * 左边按钮点击
     *
     * @param l
     */
    public void setLeftListener(OnClickListener l) {
        mLeftText.setOnClickListener(l);
    }

    /**
     * 设置左边按钮的显示
     * 默认是显示
     */
    public void setLeftImageVisible(int visible) {
        mLeftText.setVisibility(visible);
    }

    /**
     * 设置左边按钮图标
     * 默认是返回键
     */
    public void setLeftImageResource(int resourceID) {
        mLeftText.setCompoundDrawablesWithIntrinsicBounds(resourceID, 0, 0, 0);
    }

    /**
     * 设置左边按钮文字
     * 默认是返回键
     */
    public void setLeftTextResource(int resourceID) {
        mLeftText.setText(resourceID);
    }

    /**
     * 设置左边文字的字体颜色
     */
    public void setLeftTextColor(int color) {
        mLeftText.setTextColor(color);
    }


    /**
     * 右边按钮点击
     */
    public void setRightListener(OnClickListener l) {
        mRightText.setOnClickListener(l);
    }

    /**
     * 设置左边按钮的显示
     * 默认不显示
     */
    public void setRightImageVisible(int visible) {
        mRightText.setVisibility(visible);
    }

    /**
     * 设置右边按钮图标
     * 没有默认图标，需要自己设置
     *
     * @param resourceID
     */
    public void setRightImageResource(int resourceID) {
        mRightText.setCompoundDrawablesWithIntrinsicBounds(resourceID, 0, 0, 0);
    }

    /**
     * 设置右边按钮enable
     */
    public void setRightTextEnable(boolean enabled) {
        mRightText.setEnabled(enabled);
    }

    /**
     * 设置右边按钮文字
     * 默认为空
     */
    public void setRightTextResource(int resourceID) {
        mRightText.setText(resourceID);
    }

    /**
     * 设置右边文字字体颜色
     */
    public void setRightTextColor(int color) {
        mRightText.setTextColor(color);
    }

    /**
     * 设置右边按钮文字
     * 默认为空
     */
    public void setRightText(String name) {
        mRightText.setText(name);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    /**
     * 设置标题
     */
    public void setTitleResource(int resourceID) {
        mTitleText.setText(resourceID);
    }

    /**
     * 设置title是否显示
     * 默认显示title，没有文字
     */
    public void setTitleVisible(int visible) {
        mTitleText.setVisibility(visible);
    }

    /**
     * 设置title 颜色
     */
    public void setTitleTextColor(int color) {
        mTitleText.setTextColor(color);
    }

    /**
     * 获取右边按钮
     */
    public TextView getRightButton() {
        return mRightText;
    }

    /**
     * 获取左边按钮
     */
    public TextView getLeftButton() {
        return mLeftText;
    }
}
