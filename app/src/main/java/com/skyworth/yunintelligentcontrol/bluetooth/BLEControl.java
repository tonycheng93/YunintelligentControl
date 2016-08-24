/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: BLEControl.java
 * @Prject: YunintelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.bluetooth
 * @Description: TODO
 * @author: Sky000
 * @date: 2016年8月23日 上午9:07:57
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.bluetooth;

import android.R.bool;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.util.Log;

import java.util.UUID;

/**
 * @ClassName: BLEControl
 * @Description: TODO
 * @author: 李晓萌
 * @date: 2016年8月16日 上午9:07:57  
 */
public class BLEControl {
    private BluetoothAdapter bluetoothAdapter;
    private String BLE = "BBBLLLEEE";
    private BluetoothDevice bluetoothDevice;
    private BluetoothManager bluetoothManager;
    //service ,characteristic读写相关的UUID
    private final UUID SERVICE_READ_UUID = UUID.fromString("00001800-0000-1000-8000-00805F9B34FB");
    private final UUID SERVICE_WRITE_UUID = UUID.fromString("0000190f-0000-1000-8000-00805F9B34FB");
    private final UUID CHARACTER_READ_UUID = UUID.fromString("00002A01-0000-1000-8000-00805F9B34FB");
    private final UUID CHARACTER_WRITE_UUID = UUID.fromString("0000ffe9-0000-1000-8000-00805f9b34fb");

    int count = 0;
    private final int CONNECTED = 0X01;
    private final int DISCONNECTED = 0x02;

    private BluetoothGatt mBluetoothGatt = null;
    private BluetoothGattCharacteristic mWriteCharacteristic = null;
    private BluetoothGattCharacteristic mReadCharacteristic = null;

    //获取GATT服务
    public BluetoothGatt getBluetoothGatt(Context context, bool is) {
        mBluetoothGatt = bluetoothDevice.connectGatt(context, false, mCallback);
        return mBluetoothGatt;

    }

    //回调
    private final BluetoothGattCallback mCallback = new BluetoothGattCallback() {
        //当Characteristic为可读态时回调
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic, int status) {
            Log.i(BLE + "1111111111111111111111", "come in onCharacteristicRead");
            if (status == mBluetoothGatt.GATT_SUCCESS) {
                ReadCharacterValue(characteristic);
                mBluetoothGatt.setCharacteristicNotification(characteristic, true);
                BluetoothGattDescriptor descriptor = mReadCharacteristic.getDescriptor(CHARACTER_READ_UUID);
                if (descriptor != null) {
                    byte[] val = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
                    descriptor.setValue(val);
                    mBluetoothGatt.writeDescriptor(descriptor);
                } else {
                }

            }
            Log.i(BLE, characteristic.getUuid() + "");

        }

        ;

        //写入成功与否的回调
        public void onCharacteristicWrite(BluetoothGatt gatt,
                                          BluetoothGattCharacteristic characteristic, int status) {
            switch (status) {
                case BluetoothGatt.GATT_SUCCESS:
                    Log.i(BLE, "写入成功");
                    break;
                case BluetoothGatt.GATT_FAILURE:
                    Log.i(BLE, "写入失败");
                    break;
                case BluetoothGatt.GATT_WRITE_NOT_PERMITTED:
                    Log.i(BLE, "没有写入权限");
            }
        };

        //连接状态发生更在时调用
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (mBluetoothGatt.GATT_SUCCESS == status) {
                //	mState = CONNECTED;
                boolean is = mBluetoothGatt.discoverServices();
            } else if (newState == BluetoothGatt.GATT_FAILURE) {
                //	mState = DISCONNECTED;
            }
//			gatt.discoverServices();
//			super.onConnectionStateChange(gatt, status, newState);
        }

        ;

        //远端设备中的服务可用时回调
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == mBluetoothGatt.GATT_SUCCESS) {
                BluetoothGattService mWriteService = mBluetoothGatt.getService(SERVICE_WRITE_UUID);
                BluetoothGattService mReadService = mBluetoothGatt.getService(SERVICE_READ_UUID);
                //BluetoothGattService  services = mBluetoothGatt.
                if (mWriteService != null) {
                    mWriteCharacteristic = mWriteService.getCharacteristic(CHARACTER_WRITE_UUID);
                } else {
                    Log.i(BLE + "11111111", " mWriterCharaccteristic is null");
                }
                if (mReadService != null) {
                    mReadCharacteristic = mReadService.getCharacteristic(CHARACTER_READ_UUID);
                    if (mReadCharacteristic != null) {
                        boolean is = mBluetoothGatt.readCharacteristic(mReadCharacteristic);
                    } else {
                    }
                } else {
                }
            } else {
            }
            Log.i(BLE + ":servicesize", gatt.getServices().size() + "");
            super.onServicesDiscovered(gatt, status);
        }

        ;

        //当远端character发生变化时，回调此方法
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            ReadCharacterValue(characteristic);
        }

        ;

    };

    //读取“特性”中的值
    private void ReadCharacterValue(BluetoothGattCharacteristic characteristic) {
        byte[] data = characteristic.getValue();
        StringBuffer buffer = new StringBuffer("0x");
        int i;
        for (byte b : data) {
            i = b & 0xff;
            buffer.append(Integer.toHexString(i));
        }
    }

    //发送数据
    public void sendData(byte[] data) {

        if (data != null && data.length > 0 && data.length < 21) {
            if (mWriteCharacteristic.setValue(data)
                    && mBluetoothGatt.writeCharacteristic(mWriteCharacteristic)) {
                Log.d(BLE, "send data OK");
            }
        }
    }
}
