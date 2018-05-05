package com.juny.cashiersystem.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * <br> ClassName: BasePresenter
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/3 15:01
 */

public class BasePresenter<T> {

    /*** View接口类型的弱引用***/
    protected Reference<T> mViewRef;

    /**
     * <br> Description:  与view 建立关联
     * <br> Author: Juny
     * <br> Date:  2018/4/3  17:13
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    /**
     * <br> Description:  在presenter中使用getView之前注意判断是否仍与View关联
     * <br> Author: Juny
     * <br> Date:  2018/4/3  17:16
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     *<br> Description: 解除关联
     *<br> Author: Juny
     *<br> Date:  2018/4/3  17:24
     *
    */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
