
package com.juny.cashiersystem.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.juny.cashiersystem.constant.AppConfig;

/**
 * 通用的Toast
 * PS:原则上我们程序中使用Toast都使用这个定制类
 *
 * @author ls
 */
public class CSToast {
    private static Handler sHandler;

    /**
     * 简易函数：显示toast
     */
    public static void showToast(String msg) {
        showToast(msg, null, false);
    }

    /**
     * 简易函数：显示toast
     */
    public static void showToast(String msg, boolean isDebug) {
        showToast(msg, null, isDebug);
    }

    /**
     * 简易函数：显示toast
     */
    public static void showToast(int resid) {
        String msg = ResourceUtil.getString(resid);
        showToast(msg, null, false);
    }

    /**
     * 简易函数：显示toast
     */
    public static void showToast(int resid, Handler uiHandler) {
        String msg = ResourceUtil.getString(resid);
        showToast(msg, uiHandler, false);
    }

    public static void showToast(final CharSequence msg, Handler uiHandler) {
        showToast(msg, uiHandler, false);
    }


    private static void showToast(final CharSequence msg, Handler uiHandler, boolean isDebug) {
        CSLog.log("msg=" + msg);
        if (!AppConfig.DEBUG_ABLE) { //在测试环境提示信息
            CSLog.log("debug msg won't show, msg=" + msg);
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            CSLog.log(String.valueOf(Env.getContext() == null));   // true
            Toast.makeText(Env.getContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (uiHandler != null) {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Env.getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                if (sHandler == null) {
                    sHandler = new Handler(Looper.getMainLooper());
                }
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Env.getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

}