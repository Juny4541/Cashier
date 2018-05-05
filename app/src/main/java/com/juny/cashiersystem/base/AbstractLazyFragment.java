package com.juny.cashiersystem.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.juny.cashiersystem.R;

/**
 * <br> ClassName: AbstractLazyFragment
 * <br> Description: 懒加载Fragment
 * <br> Author: Juny
 * <br> Date:  2018/4/7 12:38
 */

public abstract class AbstractLazyFragment<T extends BasePresenter> extends AbstractCSFragment<T> {
    protected ViewStub mVsContent;
    protected boolean mIsLoadUi = false;
    protected Bundle mSaveInstanceState;
    protected FrameLayout mRootFrameLayout;

    /**
     * <br> Description:  延迟加载UI
     * <br> Author: Juny
     * <br> Date:  2018/4/7  13:09
     *
     * @return 是否延迟加载
     */
    protected abstract boolean onLoadUi();

    @Override
    protected int getContentRes() {
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mSaveInstanceState = savedInstanceState;
        View view = inflater.inflate(R.layout.lazy_fragment, null);
        mRootFrameLayout = view.findViewById(R.id.frameLayout);
        mVsContent = view.findViewById(R.id.vs_content);
        return view;
    }

    /**
     * <br> Description: 初始化UI
     * <br> Author: Juny
     * <br> Date:  2018/4/7  13:22
     */
    private void setupUI() {
        mIsLoadUi = true;

        mVsContent.setLayoutResource(getContentRes());
        View view = mVsContent.inflate();
        bindView(true, view);
        initView(view);

        boolean delayAnimFlag = onLoadUi();
        if (delayAnimFlag) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setDuration(350);
            view.startAnimation(alphaAnimation);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mIsLoadUi){
            onLazyResume();
        }
    }

    /**
     *<br> Description: onResume
     *<br> Author: Juny
     *<br> Date:  2018/4/7  13:23
    */
    private void onLazyResume() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mIsLoadUi){
            onLazyPause();
        }
    }

    /**
     *<br> Description:  onPause
     *<br> Author: Juny
     *<br> Date:  2018/4/7  13:24
    */
    private void onLazyPause() {
    }

    @Override
    public final void onVisibleToUserChange(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        super.onVisibleToUserChange(isVisibleToUser, invokeInResumeOrPause);
        if (mIsLoadUi) {
             onVisibilityChangedToUser(isVisibleToUser,invokeInResumeOrPause);
        }else if(isVisibleToUser && isVisible()){
            setupUI();
        }
    }

     /**
      *<br> Description:  对用户是否可见
      *<br> Author: Juny
      *<br> Date:  2018/4/7  13:26
      *
      *  @param isVisibleToUser       isVisibleToUser
      * @param invokeInResumeOrPause invokeInResumeOrPause
     */
    protected void onVisibilityChangedToUser(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }


}
