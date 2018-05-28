package com.juny.cashiersystem.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juny.cashiersystem.R;

import java.util.Calendar;

/**
 * <br> ClassName: DateSelectView
 * <br> Description: 时间选择框
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/4/9 17:42
 */

public class DateSelectView extends LinearLayout {

    private final static int BEGIN_DATE = 0;
    private final static int END_DATE = 1;
    private TextView mTvBeginDate;
    private TextView mTvEndDate;
    private Context mContext;

    private OnDateSelectListener mOnDateSelectListener;

    /**
     * <br> Description: 设置监听
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/9 18:25
     */
    public void setOnDateSelectListener(OnDateSelectListener listener) {
        this.mOnDateSelectListener = listener;
    }

    public DateSelectView(Context context) {
        super(context);
        init(context);
    }

    public DateSelectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.common_date_select_view, this, true);
        mTvBeginDate = findViewById(R.id.tv_date_select_begin);
        mTvEndDate = findViewById(R.id.tv_date_select_end);

        mTvBeginDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(BEGIN_DATE);
            }
        });

        mTvEndDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(END_DATE);
            }
        });
    }

    /**
     * <br> Description: 显示时间选择对话框
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/9 18:25
     */
    private void showDialog(final int id) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        if (id == BEGIN_DATE) {
                            String beginDate;
                            if (monthOfYear < 9) {
                                beginDate = String.valueOf(year + "/" + "0" + (monthOfYear + 1) + "/" + dayOfMonth);
                            } else {
                                beginDate = String.valueOf(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                            mTvBeginDate.setText(beginDate);
                        } else if (id == END_DATE) {
                            String endDate;
                            if (monthOfYear < 9) {
                                endDate = String.valueOf(year + "/" + "0" + (monthOfYear + 1) + "/" + dayOfMonth);
                            } else {
                                endDate = String.valueOf(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                            mTvEndDate.setText(endDate);
                        }
                        if (mOnDateSelectListener != null) {
                            if (!TextUtils.isEmpty(mTvBeginDate.getText().toString()) && !TextUtils.isEmpty(mTvEndDate.getText().toString())) {
                                mOnDateSelectListener.endDateSelected(mTvBeginDate.getText().toString(),
                                        mTvEndDate.getText().toString());
                            }
                        }
                    }
                },
                //设置初始日期
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public interface OnDateSelectListener {
        void endDateSelected(String beginDate, String endDate);
    }
}
