package com.juny.cashiersystem;

/**
 * <br> ClassName: ICommonModelCallback
 * <br> Description:  TODO 写说明
 * <br>
 * <br> Author:  chenrunfang
 * <br> Date:  2018/5/10 15:46
 */

public interface ICommonModelCallback<T> {
    void onSuccess(T t);

    void onFailed(T t);
}
