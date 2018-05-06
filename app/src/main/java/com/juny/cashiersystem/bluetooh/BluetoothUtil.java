package com.juny.cashiersystem.bluetooh;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * <br> ClassName: BluetoothUtil
 * <br> Description:
 * <br> Author: Juny
 * <br> Date:  2018/4/29 16:45
 */

public class BluetoothUtil {

    public static void checkBluetoothState(Activity activity) {
        if (!isBluetoothOn()) {
            openBluetooth(activity);
        }
    }

    /**
     * <br> Description:  判断蓝牙是否开启
     * <br> Author: Juny
     * <br> Date:  2018/5/6  18:38
     */
    private static boolean isBluetoothOn() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    /**
     * <br> Description:  弹出系统对话框，请求打开蓝牙
     * <br> Author: Juny
     * <br> Date:  2018/5/6  18:38
     */
    private static void openBluetooth(Activity activity) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, 666);
    }

    /**
     * <br> Description: 打开蓝牙系统设置
     * <br> Author: Juny
     * <br> Date:  2018/5/6  18:38
     */
    public void openSetting(Activity activity, String setting) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * <br> Description: 获取所有已配对的蓝牙打印类设备DeviceBean  列表
     * <br> Author: Juny
     * <br> Date:  2018/4/30  11:47
     */
    public static List<BluetoothDevice> getPaireDevices() {
        List<BluetoothDevice> list = new ArrayList<>();
        Set<BluetoothDevice> paireDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (paireDevices.size() > 0) {
            for (BluetoothDevice device : paireDevices) {
                Log.d("getPaireDevices", String.valueOf(device.getBluetoothClass().getMajorDeviceClass()) + " " + device.getName());
                list.add(device);
            }
        }
        return list;
    }

    /**
     * <br> Description:  连接蓝牙设备
     * <br> Author: Juny
     * <br> Date:  2018/4/30  12:49
     */
    public static BluetoothSocket connectDevice(BluetoothDevice device) {
        BluetoothSocket socket = null;
        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();

            // 异常则关闭
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return socket;
    }
}
