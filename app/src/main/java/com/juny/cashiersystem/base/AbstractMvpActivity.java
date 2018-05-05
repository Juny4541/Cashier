package com.juny.cashiersystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.juny.cashiersystem.util.CSToast;

import java.util.List;

/**
 * <br> ClassName: AbstractMvpActivity
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/7 14:18
 */

public abstract class AbstractMvpActivity<T extends BasePresenter> extends AppCompatActivity implements IMvpView {

    private LinearLayout mLayoutMain;

    /***兼容多个Presenter*/
    protected List<T> mPresenterList;

    protected abstract List<T> createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutMain = new LinearLayout(this);
        mLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLayoutMain.setOrientation(LinearLayout.VERTICAL);
        setContentView(mLayoutMain);

        mPresenterList = createPresenter();
        if (mPresenterList != null && !mPresenterList.isEmpty()) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.attachView(this);
            }
        }
    }

    public LinearLayout getMainLayout() {
        return mLayoutMain;
    }

    @Override
    public void showLoading(String tips) {
        dismissLoading();
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showToast(String toast) {
        if (!TextUtils.isEmpty(toast)) {
            CSToast.showToast(toast);
        }
    }

    @Override
    public void onFinish() {
        if (!isFinishing()) {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenterList != null && !mPresenterList.isEmpty()){
            for (BasePresenter presenter : mPresenterList){
                presenter.detachView();
            }
        }
    }
}
