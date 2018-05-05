package com.juny.cashiersystem.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * <br> ClassName: AbstractCSFragment
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/7 12:11
 */

public abstract class AbstractCSFragment<T extends BasePresenter>
        extends AbstractMvpFragment implements IBaseView{

    private Unbinder mUnbinder;
    /**
     *<br> Description: 是否需要绑定EventBus 默认false
     *<br> Author: Juny
     *<br> Date:  2018/4/7  12:20
     *
    */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     *<br> Description:  是否初始话ButterKnife
     *<br> Author: Juny
     *<br> Date:  2018/4/7  12:21
     *
    */
    protected boolean isAutoBindView() {
        return true;
    }

    protected abstract @LayoutRes int getContentRes();

    protected void initView(View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentRes = getContentRes();
        if (contentRes > 0) {
            View mRootView = inflater.inflate(contentRes,null);
            bindView(true,mRootView);
            initView(mRootView);
            return mRootView;
        }
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerEventBus(true);
    }


    @Override
    protected List createPresenter() {
        return null;
    }

    @Override
    public void showLoading(String tips) {
        super.showLoading(tips);
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bindView(false,null);
        registerEventBus(false);
    }

    /**
     *<br> Description:  绑定/取消绑定
     *<br> Author: Juny
     *<br> Date:  2018/4/7  12:32
     *
    */
    protected void bindView(boolean bind, View view) {
        if (!bind && mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
            return;
        }
        if (!isAutoBindView()) {
            return;
        }
        if (bind && view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
    }

    /**
     *<br> Description:  注册和取消注册EventBus
     *<br> Author: Juny
     *<br> Date:  2018/4/7  12:36
     *
    */
    private void registerEventBus(boolean register) {
        if (!isRegisterEventBus()) {
            return;
        }
        if (register) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        } else {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        }
    }



    @Override
    public void displayRequestFailure(String taskId) {

    }


}
