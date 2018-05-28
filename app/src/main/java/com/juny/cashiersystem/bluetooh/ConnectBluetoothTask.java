package com.juny.cashiersystem.bluetooh;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;

import com.juny.cashiersystem.bean.OrderBean;
import com.juny.cashiersystem.bean.OrderGoodsBean;
import com.juny.cashiersystem.util.CSToast;

import java.io.IOException;

import io.realm.RealmList;

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
    public void doPrint(OrderBean orderBean) {
        if (mSocket.isConnected()) {
            printTest(mSocket, orderBean);
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
    private boolean printTest(BluetoothSocket socket, OrderBean orderBean) {
        try {
            PrintUtils.setOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        PrintUtils.selectCommand(PrintUtils.RESET);
        PrintUtils.selectCommand(PrintUtils.LINE_SPACING_DEFAULT);
        PrintUtils.selectCommand(PrintUtils.ALIGN_CENTER);
        PrintUtils.selectCommand(PrintUtils.DOUBLE_HEIGHT_WIDTH);
        PrintUtils.printText("Juny美茶\n\n");
        PrintUtils.selectCommand(PrintUtils.NORMAL);
        PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
        PrintUtils.printText(PrintUtils.printTwoData("订单编号", orderBean.getOrderNum() + "\n"));
        PrintUtils.printText(PrintUtils.printTwoData("下单时间", orderBean.getDate() + "\n"));

        PrintUtils.printText("--------------------------------\n");
        PrintUtils.selectCommand(PrintUtils.BOLD);
        PrintUtils.printText(PrintUtils.printThreeData("品名", "数量", "金额\n"));
        PrintUtils.printText("--------------------------------\n");
        PrintUtils.selectCommand(PrintUtils.BOLD_CANCEL);

        // 商品列表
        RealmList<OrderGoodsBean> goodsList = orderBean.getGoods();
        for (int i = 0; i < goodsList.size(); i++) {
            PrintUtils.printText(PrintUtils.printThreeData(goodsList.get(i).getName(), String.valueOf(goodsList.get(i).getCount()),
                    String.valueOf(goodsList.get(i).getPrice()) + "\n"));
        }

        PrintUtils.printText("--------------------------------\n");
        PrintUtils.printText(PrintUtils.printTwoData("合计", String.valueOf(orderBean.getAmount()) + "\n"));
        PrintUtils.printText("--------------------------------\n");
        PrintUtils.printText(PrintUtils.printTwoData("应收", String.valueOf(orderBean.getAmount()) + "\n"));
        PrintUtils.printText("--------------------------------\n");
        PrintUtils.printText(PrintUtils.printTwoData("备注", orderBean.getRemark() + "\n"));

        PrintUtils.printText("\n\n\n\n\n");
        return true;
    }
}
