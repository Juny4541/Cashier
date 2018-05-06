package com.juny.cashiersystem.bluetooh;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

import com.juny.cashiersystem.util.CSToast;

import java.io.IOException;

/**
 * <br> ClassName: ConnectBluetoothTask
 * <br> Description: 蓝牙连接异步任务
 * <br> Author: Juny
 * <br> Date:  2018/5/6 19:12
 */

public class ConnectBluetoothTask extends AsyncTask<BluetoothDevice, Integer, BluetoothSocket> {

    private BluetoothSocket mSocket;

    public ConnectBluetoothTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
        // 执行连接任务
        // mSocket不为空时，去除重复连接 // TODO 无法去重
        if (mSocket != null) {
            if (mSocket.getRemoteDevice() == bluetoothDevices[0] && mSocket.isConnected()) {
                return mSocket;
            } else {
                closeSocket();
            }
        }
        // 执行连接
        mSocket = BluetoothUtil.connectDevice(bluetoothDevices[0]);
        Log.d("ConnectBluetoothTask", String.valueOf(mSocket.getRemoteDevice()));
        return mSocket;
    }

    @Override
    protected void onPostExecute(BluetoothSocket socket) {
        super.onPostExecute(socket);
        if (mSocket == null || !socket.isConnected()) {
            CSToast.showToast("连接失败,你连接的是否是打印设备？");
        } else {
            CSToast.showToast("连接成功");
        }
    }

    /**
     * <br> Description:  close Socket
     * <br> Author: Juny
     * <br> Date:  2018/4/30  13:00
     */
    public void closeSocket() {
        if (mSocket != null) {
            try {
                mSocket.close();
            } catch (IOException e) {
                mSocket = null;
                e.printStackTrace();
            }
        }
    }

    /**
     * <br> Description:  执行打印任务
     * <br> Author: Juny
     * <br> Date:  2018/5/6  20:53
     */
    public void doPrint() {
        if (mSocket.isConnected()) {
            printTest(mSocket);
        }
    }

    public BluetoothSocket getSocket() {
        return mSocket;
    }


    /**
     * <br> Description:  测试打印
     * <br> Author: Juny
     * <br> Date:  2018/4/30  17:39
     */
    private boolean printTest(BluetoothSocket socket) {
        try {
            PrintUtils.setOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        PrintUtils.selectCommand(PrintUtils.RESET);
        PrintUtils.selectCommand(PrintUtils.LINE_SPACING_DEFAULT);
        PrintUtils.selectCommand(PrintUtils.ALIGN_CENTER);
        PrintUtils.printText("美食餐厅\n\n");
        PrintUtils.selectCommand(PrintUtils.DOUBLE_HEIGHT_WIDTH);
        PrintUtils.printText("桌号：1号桌\n\n");
        PrintUtils.selectCommand(PrintUtils.NORMAL);
        PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
        PrintUtils.printText(PrintUtils.printTwoData("订单编号", "201507161515\n"));
        PrintUtils.printText(PrintUtils.printTwoData("点菜时间", "2016-02-16 10:46\n"));
        PrintUtils.printText(PrintUtils.printTwoData("上菜时间", "2016-02-16 11:46\n"));
        PrintUtils.printText(PrintUtils.printTwoData("人数：2人", "收银员：张三\n"));

        PrintUtils.printText("--------------------------------\n");
        PrintUtils.selectCommand(PrintUtils.BOLD);
        PrintUtils.printText(PrintUtils.printThreeData("项目", "数量", "金额\n"));
        PrintUtils.printText("--------------------------------\n");
        PrintUtils.selectCommand(PrintUtils.BOLD_CANCEL);
        PrintUtils.printText(PrintUtils.printThreeData("面", "1", "0.00\n"));
        PrintUtils.printText(PrintUtils.printThreeData("米饭", "1", "6.00\n"));
        PrintUtils.printText(PrintUtils.printThreeData("铁板烧", "1", "26.00\n"));
        PrintUtils.printText(PrintUtils.printThreeData("一个测试", "1", "226.00\n"));
        PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊", "1", "2226.00\n"));
        PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊啊牛肉面啊啊啊", "888", "98886.00\n"));

        PrintUtils.printText("--------------------------------\n");
        PrintUtils.printText(PrintUtils.printTwoData("合计", "53.50\n"));
        PrintUtils.printText(PrintUtils.printTwoData("抹零", "3.50\n"));
        PrintUtils.printText("--------------------------------\n");
        PrintUtils.printText(PrintUtils.printTwoData("应收", "50.00\n"));
        PrintUtils.printText("--------------------------------\n");

        PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
        PrintUtils.printText("备注：不要辣、不要香菜");
        PrintUtils.printText("\n\n\n\n\n");
        return true;
    }
}
