package com.juny.cashiersystem.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName: AbstractCSActivity
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/7 16:34
 */

public class AbstractCSActivity<T extends BasePresenter> extends AbstractMvpActivity {
    /***无数据**/
    private final int RESULT_TYPE_NO_DATA = 1;

    private View mMainContent;
    private Unbinder mUnbinder;

    /**
     * <br> Description: 是否初始化ButterKnife
     * <br> Author: Juny
     * <br> Date:  2018/4/7  16:52
     */
    protected boolean isAutoBindView() {
        return true;
    }

    /**
     * <br> Description:  是否需要EventBus ,默认false
     * <br> Author: Juny
     * <br> Date:  2018/4/7  16:53
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerEventBus(true);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mMainContent = getLayoutInflater().inflate(layoutResID,null);
        getMainLayout().addView(mMainContent,new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        bindView(true);
    }

    @Override
    protected List createPresenter() {
        return null;
    }

    /**
     *<br> Description:  绑定/取消绑定
     *<br> Author: Juny
     *<br> Date:  2018/4/7  12:32
     *
     */
    protected void bindView(boolean bind) {
        if (!bind && mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
            return;
        }
        if (!isAutoBindView()) {
            return;
        }
        if (bind) {
            mUnbinder = ButterKnife.bind(this);
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

}
