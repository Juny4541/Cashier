package com.juny.cashiersystem.util;

/**
 * <br> ClassName:   UiUtil
 * <br> Description: UI工具类
 * <br>
 * <br> Author:      chenrunfang
 * <br> Date:        2017/8/1 10:57
 */
public class UiUtil {
    /**
     * <br> Description: dp转px数值
     * <br> Author:      zhangweiqiang
     * <br> Date:        2017/8/2 11:26
     *
     * @param dp 要转换的dp值
     * @return 返回转换后的px值
     */
    public static int dp2px(float dp) {
        float scale = Env.getContext().getResources()
                .getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
