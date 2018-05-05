package com.juny.cashiersystem.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.juny.cashiersystem.util.CSToast;

import java.util.List;

/**
 * <br> ClassName: AbstractMvpFragment
 * <br> Description: Mvp 架构 Fragment 基类
 * <br> Author: Juny
 * <br> Date:  2018/4/7 10:37
 */

public abstract class AbstractMvpFragment<T extends BasePresenter>
        extends Fragment implements FragmentUserVisibleController.UserVisibleCallback, IMvpView {

    protected Activity mActivity;

    /***  是否显示当前页*/
    protected boolean mIsVisible = false;

    private FragmentUserVisibleController mUserVisibleController;

    protected List<T> mPresenterList;

    /**
     * <br> Description: 创建presenter
     * <br> Author: Juny
     * <br> Date:  2018/4/7  10:46
     *
     * @return List presenterList
     */
    protected abstract List<T> createPresenter();

    /**
     * <br> Description: 构造方法
     * <br> Author: Juny
     * <br> Date:  2018/4/7  10:49
     */
    public AbstractMvpFragment() {
        mUserVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mPresenterList = createPresenter();
        if (mPresenterList != null && mPresenterList.size() > 0) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.attachView(this);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserVisibleController.activityCreated();
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserVisibleController.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mUserVisibleController.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenterList != null && !mPresenterList.isEmpty()) {
            for (BasePresenter presenter : mPresenterList) {
                presenter.detachView();
            }
        }
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        mUserVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return mUserVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return mUserVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onVisibleToUserChange(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        mUserVisibleController.setUserVisibleHint(isVisibleToUser);
        mIsVisible = isVisibleToUser && isVisible();
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void showLoading(String tips) {
        dismissLoading();
    }

    @Override
    public void dismissLoading() {}

    @Override
    public void showToast(String toast) {
        if (!TextUtils.isEmpty(toast)){
            CSToast.showToast(toast);
        }
    }

    @Override
    public void onFinish() {
        if (mActivity != null && !mActivity.isFinishing()) {
            mActivity.finish();
        }
    }
}
