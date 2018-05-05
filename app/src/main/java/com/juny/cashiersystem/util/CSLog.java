package com.juny.cashiersystem.util;

import android.text.TextUtils;
import android.util.Log;

import com.juny.cashiersystem.constant.AppConfig;

import java.util.Locale;

/**
 * 日志封装
 * Created by Junny on 2017/12/2.
 */

public class CSLog {

    private static final String TAG = CSLog.class.getSimpleName();

    private static String FILTER = "CashierSystem";

    public static void setLogFilter(String filter) {
        FILTER = filter;
    }

    /**
     * 一般的log
     */
    public static void log(String tag, String content) {
        if (content == null) {
            return;
        }

        if (AppConfig.DEBUG_ABLE) {
            if (TextUtils.isEmpty(tag)) {
                log(content);
            }
            Log.d(tag, buildMessageSafe(content));
        }
    }

    public static void log(String content) {
        if (AppConfig.DEBUG_ABLE) {
            Log.d(getTag(), buildMessageSafe(content));
        }
    }

    public static void i(String content) {
        Log.i(getTag(), buildMessageSafe(content));
    }

    public static void w(String content) {
        Log.w(getTag(), buildMessageSafe(content));
    }

    /**
     * 错误log
     */
    public static void logError(String tag, String content) {
        if (tag == null || content == null) {
            return;
        }
        if (AppConfig.DEBUG_ABLE) {
            Log.e(tag, buildMessageSafe(content));
        }
    }

    public static void logError(String content) {
        if (content == null) {
            return;
        }
        if (AppConfig.DEBUG_ABLE) {
            Log.e(getTag(), buildMessageSafe(content));
        }
    }

    public static void printError(Throwable throwable) {
        throwable.printStackTrace();
    }


    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String className = "";
        for (int i = 3; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(CSLog.class)) {
                className = trace[i].getClassName();
                className = className.substring(className.lastIndexOf('.') + 1);
                break;
            }
        }
        return className;
    }

    private static String buildMessageSafe(String msg) {
        try {
            return buildMessage(msg);
        } catch (Exception e) {
        }
        return msg;
    }

    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "";
        for (int i = 3; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(CSLog.class)) {
                caller = "(" + trace[i].getFileName() + ":" + trace[i].getLineNumber() + ") "
                        + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, FILTER + " [%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg == null ? "" : msg);
    }
}
