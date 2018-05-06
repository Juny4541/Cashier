package com.juny.cashiersystem.base;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.juny.cashiersystem.bluetooh.BluetoothUtil;
import com.juny.cashiersystem.util.CSToast;

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

@SuppressLint("Registered")
public class AbstractCSActivity<T extends BasePresenter> extends AbstractMvpActivity {

    private View mMainContent;
    private Unbinder mUnbinder;

    /***广播接收服务**/
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onBluetoothStateChanged(intent);
        }
    };

    /**
     *<br> Description:  蓝牙状态发生变化时回调，子类可重写
     *<br> Author: Juny
     *<br> Date:  2018/5/6  18:48
     *
     */
    public void onBluetoothStateChanged(Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
        switch (state) {
            case BluetoothAdapter.STATE_TURNING_ON:
                CSToast.showToast("蓝牙已开启");
                break;

            case BluetoothAdapter.STATE_TURNING_OFF:
                BluetoothUtil.checkBluetoothState(this);
                break;
        }
    }

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
        // 注册服务
        registerReceiver();
        // 检测蓝牙的是否开启
        BluetoothUtil.checkBluetoothState(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除注册
        unregisterReceiver(mReceiver);
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
    private void bindView(boolean bind) {
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

    /**
     * <br> Description:  注册广播服务
     * <br> Author: Juny
     * <br> Date:  2018/4/30  13:06
     */
    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter1);
    }
}
