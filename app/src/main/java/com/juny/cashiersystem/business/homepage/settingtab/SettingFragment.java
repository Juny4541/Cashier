package com.juny.cashiersystem.business.homepage.settingtab;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.juny.cashiersystem.R;
import com.juny.cashiersystem.base.AbstractCSFragment;
import com.juny.cashiersystem.bluetooh.BluetoothUtil;
import com.juny.cashiersystem.bluetooh.ConnectBluetoothTask;
import com.juny.cashiersystem.util.CSToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设置 fragment
 * Created by Junny on 2018/3/5.
 */

public class SettingFragment extends AbstractCSFragment {

    @BindView(R.id.rv_device_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_setting_search)
    TextView mTvSearch;
    @BindView(R.id.tv_print_btn)
    TextView mTvPrintBtn;

    private DeviceListAdapter mAdapter;
    private ConnectBluetoothTask mAsyncTask;
    private List<BluetoothDevice> mList = new ArrayList<>();
    private boolean mIsConnected = false;


    @Override
    protected int getContentRes() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        BluetoothUtil.checkBluetoothState(mActivity);
        init(view);
    }

    private void init(View view) {
        // 初始化 RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DeviceListAdapter();

        mList = BluetoothUtil.getPaireDevices();
        mAdapter.BindData(mList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DeviceListAdapter.OnItemClickListener() {
            @Override
            public void itemClick(BluetoothDevice device) {
                connectDevice(device);
            }
        });

        mTvPrintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAsyncTask == null) {
                    CSToast.showToast("未连接打印设备！");
                } else {
                    mAsyncTask.doPrint();
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAsyncTask != null) {
            mAsyncTask.closeSocket();
            mIsConnected = false;
        }
        cancelAsyncTask();
    }

    /**
     * <br> Description:  取消异步操作
     * <br> Author: Juny
     * <br> Date:  2018/4/30  16:43
     */
    private void cancelAsyncTask() {
        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
            mAsyncTask = null;
        }
    }

    /**
     * <br> Description:  连接蓝牙设备
     * <br> Author: Juny
     * <br> Date:  2018/4/30  13:17
     */
    private void connectDevice(BluetoothDevice device) {
        if (device != null) {
            mAsyncTask = (ConnectBluetoothTask) new ConnectBluetoothTask().execute(device);
        }
    }
}
