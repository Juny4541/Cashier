package com.juny.cashiersystem.business.homepage.settingtab;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juny.cashiersystem.R;

import java.util.ArrayList;
import java.util.List;


/**
 * <br> ClassName: DeviceListAdapter
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/29 16:21
 */

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {
    private List<BluetoothDevice> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void itemClick(BluetoothDevice device);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public void BindData(List<BluetoothDevice> list) {
        mList = new ArrayList<>();
        if (list != null) {
            mList = list;
        }
    }

    public void addData(BluetoothDevice device) {
        if (device == null) {
            return;
        }

        // 去重
        for (BluetoothDevice list : mList) {
            if (device.getAddress().equals(list.getAddress())) {
                return;
            }
            if (TextUtils.isEmpty(list.getName()) || TextUtils.isEmpty(list.getAddress())) {
                return;
            }
        }

        mList.add(0, device);
        notifyDataSetChanged();
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_blt_device_list_item, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        final BluetoothDevice device = mList.get(position);
        holder.mName.setText(device.getName());
        if (mListener != null) {
            holder.mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.itemClick(device);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_device_name);
        }
    }
}
