package com.juny.cashiersystem.base;

/**
 * <br> ClassName: IMvpView
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/4 9:01
 */

public interface IMvpView {
    void showLoading(String tips);

    void dismissLoading();

    void showToast(String toast);

    void onFinish();
}
