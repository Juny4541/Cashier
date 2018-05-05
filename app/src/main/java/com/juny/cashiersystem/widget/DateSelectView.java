package com.juny.cashiersystem.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
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
    private TextView mBeginDate;
    private TextView mEndDate;
    private Context mContext;

    private DateSelectListener mDateSelectListener;

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
//        setOrientation(HORIZONTAL);
//        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UiUtil.dp2px(64)));
        mBeginDate = findViewById(R.id.tv_date_select_begin);
        mEndDate = findViewById(R.id.tv_date_select_end);

        mBeginDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(BEGIN_DATE);
            }
        });

        mEndDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(END_DATE);
            }
        });
    }

    /**
     * <br> Description: 设置监听
     * <br> Author: chenrunfang
     * <br> Date: 2018/4/9 18:25
     */
    public void setDateSelectListener(DateSelectListener listener) {
        this.mDateSelectListener = listener;
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
                            String beginDate = String.valueOf(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            mBeginDate.setText(beginDate);
                            if (mDateSelectListener != null) {
                                mDateSelectListener.beginDateSelected(beginDate);
                            }
                        } else if (id == END_DATE) {
                            String endDate = String.valueOf(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            mEndDate.setText(endDate);
                            if (mDateSelectListener != null) {
                                mDateSelectListener.endDateSelected(endDate);
                            }
                        }
                    }
                },
                //设置初始日期
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    public interface DateSelectListener {
        void beginDateSelected(String beginDate);

        void endDateSelected(String endDate);
    }
}
