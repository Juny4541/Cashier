package com.juny.cashiersystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br> ClassName: TimeUtil
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/12 22:06
 */

public class TimeUtil {
    /**
     * <br> Description: 得到现在时间
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/12 22:16
     *
     * @return 字符串 yyyyMMddHHmmss
     */
    public static String getStringToday() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date d1 = new Date(time);
        return format.format(d1);
    }

    /**
     * <br> Description: 得到日期时间
     * <br> Author: chenrunfang
     * <br> Date: 2018/5/12 22:16
     *
     * @return 字符串 yyyyMMdd
     */
    public static String getStringDate() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date d1 = new Date(time);
        return format.format(d1);
    }
}
