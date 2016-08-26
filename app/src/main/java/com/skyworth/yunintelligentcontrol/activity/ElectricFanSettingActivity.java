package com.skyworth.yunintelligentcontrol.activity;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.TextView;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.app.App;
import com.skyworth.yunintelligentcontrol.utils.LogUtils;

import java.util.Set;
import java.util.UUID;

//需要将需要发送的码值存储到数组，在点击“完成设置”后，顺序发送指令
public class ElectricFanSettingActivity extends BaseActivity implements OnKeyListener, OnClickListener {

    private TextView mSwitch;
    private TextView mAirVolume;
    private TextView mAirDirection;
    private TextView mTime;
    private TextView mHealthMode;
    private Button mConfirm;
int test =0;

    private String switchTxt[] = {"关", "开"};
    private String airVolume[] = {"低", "中", "高"};
    private String airDirection[] = {"固定", "摆动"};
    private String time[] = {"关", "1H", "2H", "3H"};

    /*健康模式默认参数设置*/
    private boolean _health_mode;

    public int Data = 0;
    private String Settings[] = new String[5];
    private int datanum[] = new int[5];


    private String BLE = "BBBLLLEEE";

    //service ,characteristic读写相关的UUID
    private final UUID SERVICE_WRITE_UUID = UUID.fromString("00001812-0000-1000-8000-00805F9B34FB");
    private final UUID CHARACTER_READ_UUID = UUID.fromString("00002A01-0000-1000-8000-00805F9B34FB");
    private final UUID CHARACTER_WRITE_UUID = UUID.fromString("00002A4C-0000-1000-8000-00805f9b34fb");

    //蓝牙操作相关的变量
    private BluetoothManager mBluetoothManager = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothGatt mBluetoothGatt = null;
    private BluetoothGattCharacteristic mWriteCharacteristic = null;
    private BluetoothGattCharacteristic mReadCharacteristic = null;
    private BluetoothDevice mBluetoothDevice = null;

    //测试用
    private int order[]={2,2,4,3};
    //回调
    private final BluetoothGattCallback mCallback = new BluetoothGattCallback() {
        //当Characteristic为可读态时回调
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic characteristic, int status) {
            Log.d(BLE + "1111111111111111111111", "come in onCharacteristicRead");
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
        }

        ;

        //连接状态发生更在时调用
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
           Log.i(BLE+"111111","COME IN ONCONNECTIONSTATATECHANGE"+status);
            if (gatt.GATT_SUCCESS == status) {
                Log.i(BLE+"111111","  cone in if");
                boolean is = mBluetoothGatt.discoverServices();
            } else if (newState == BluetoothGatt.GATT_FAILURE) {
            }
        };

        //远端设备中的服务可用时回调
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.i(BLE+"111111","COME IN onServicesDiscovered");
            if (status == gatt.GATT_SUCCESS) {
                BluetoothGattService mWriteService = mBluetoothGatt.getService(SERVICE_WRITE_UUID);

                if (mWriteService != null) {
                    Log.i(BLE+"11111111111","mWriteService is not null!");
                    mWriteCharacteristic = mWriteService.getCharacteristic(CHARACTER_WRITE_UUID);
                    Log.i(BLE+"1111111111回调",""+mWriteCharacteristic);
                } else {
                    Log.i(BLE + "1111111111", " mWriterCharaccteristic is null");
                }
            } else {
            }
            Log.i(BLE + "11111111111:servicesize", gatt.getServices().size() + "");
          //  super.onServicesDiscovered(gatt, status);
        }

        ;

        //当远端character发生变化时，回调此方法
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            ReadCharacterValue(characteristic);
        }



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
    public void sendData(int data) {
        if(mWriteCharacteristic == null){
            Log.i(BLE+"1111111111111","mWriteCharacteristic is null");
        }
       else if(mWriteCharacteristic.setValue(data, BluetoothGattCharacteristic.FORMAT_UINT8, 0)
                && mBluetoothGatt.writeCharacteristic(mWriteCharacteristic)){
            Log.d(BLE, "send data OK");
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_electric_fan_setting);
    }

    @Override
    public void findViews() {
        mSwitch = (TextView) findViewById(R.id.equipment_setting_switch);
        mAirVolume = (TextView) findViewById(R.id.equipment_setting_air_volume);
        mAirDirection = (TextView) findViewById(R.id.equipment_setting_air_direction);
        mTime = (TextView) findViewById(R.id.equipment_setting_time);
        mHealthMode = (TextView) findViewById(R.id.elec_fan_health_mode_txt);
        mConfirm = (Button) findViewById(R.id.equipment_setting_confirm);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        if (_health_mode) {
            mSwitch.setText("开");
            mAirVolume.setText("中");
            mAirDirection.setText("摆动");
            mTime.setText("1H");
            mHealthMode.setText(R.string.electric_fan_setting_health_mode);
            mConfirm.requestFocus();
            mConfirm.setElevation(10);
        } else {
            mSwitch.setText("关");
            mAirVolume.setText("低");
            mAirDirection.setText("固定");
            mTime.setText("关");
            mHealthMode.setText("");
        }
//        if (App.mBluetoothDevice != null) {
//            mBluetoothGatt = App.mBluetoothDevice.connectGatt(ElectricFanSettingActivity.this, false, mCallback);
//        }
        mSwitch.setOnKeyListener(this);
        mAirVolume.setOnKeyListener(this);
        mAirDirection.setOnKeyListener(this);
        mTime.setOnKeyListener(this);
        mConfirm.setOnClickListener(this);
        bluetoothDetection();
    }

    @Override
    public void getData() {
        Intent data = getIntent();
        _health_mode = data.getBooleanExtra("_health_mode", false);
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {//左键
                switch (view.getId()) {
                    case R.id.equipment_setting_switch://风扇开关
                        setSettingText(mSwitch, switchTxt);
                        if (mSwitch.getText().toString() == "开") {
                            datanum[0] = 128;
                            test = 2;
                        } else {
                            datanum[0] = 0;
                            test =1;
                        }
                        break;
                    case R.id.equipment_setting_air_volume://风量
                        setSettingText(mAirVolume, airVolume);
                        test = 2;
                        switch (mAirVolume.getText().toString()) {
                           // test =2;
                            case "低":
                                datanum[1] = 0;
                                break;
                            case "中":
                                datanum[1] = 32;
                                break;
                            case "高":
                                datanum[1] = 64;
                                break;
                        }
                        break;
                    case R.id.equipment_setting_air_direction://风向
                        setSettingText(mAirDirection, airDirection);
                        test = 4;
                        switch (mAirDirection.getText().toString()) {
                            case "固定":
                                datanum[2] =0;
                                break;
                            case "摆动":
                                datanum[2] =16;
                                break;
                        }
                        break;
                    case R.id.equipment_setting_time://定时
                        setSettingText(mTime, time);
                        test = 3;
                        switch (mTime.getText().toString()) {
                            case "关":
                                datanum[3] = 0;
                                break;
                            case "1H":
                                datanum[3] = 4;
                                break;
                            case "2H":
                                datanum[3] = 8;
                                break;
                            case "3H":
                                datanum[3] = 12;
                                break;
                        }
                        break;
                    default:
                        break;
                }

            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {//右键
                switch (view.getId()) {
                    case R.id.equipment_setting_switch:
                        setSettingText(mSwitch, switchTxt);
                        if (mSwitch.getText().toString() == "开") {
                            test = 2;
                            datanum[0] = 128;
                        } else {
                            test = 1;
                            datanum[0] = 0;
                        }
                        break;
                    case R.id.equipment_setting_air_volume:
                        setSettingText(mAirVolume, airVolume);
                            test = 2;
                        switch (mAirVolume.getText().toString()) {
                            case "低":

                                datanum[1] = 0;
                                break;
                            case "中":

                                datanum[1] = 32;
                                break;
                            case "高":

                                datanum[1] = 64;
                                break;
                        }
                        break;
                    case R.id.equipment_setting_air_direction:
                        setSettingText(mAirDirection, airDirection);
                        test = 4;
                        switch (mAirDirection.getText().toString()) {
                            case "固定":

                                datanum[2] = 0;
                                break;
                            case "摆动":

                                datanum[2] = 16;
                                break;
                        }
                        break;
                    case R.id.equipment_setting_time:
                        setSettingText(mTime, time);
                        Settings[3] = mTime.getText().toString();
                        test = 3;
                        switch (mTime.getText().toString()) {
                            case "关":

                                datanum[3] = 0;
                                break;
                            case "1H":

                                datanum[3] = 4;
                                break;
                            case "2H":

                                datanum[3] = 8;
                                break;
                            case "3H":

                                datanum[3] = 12;
                                break;
                        }
                        break;
                    default:
                        break;
                }
                //发送左键的码值
                //		mBleControl.sendData(SharedPreferencesUtils.getString("0x4F").getBytes());
            }
        }
     new SendListener().start();
        return false;
    }

    private class SendListener extends Thread {
        @Override
        public void run() {
            for(int i = 0; i <4; i++){
                Data+=datanum[i];
            }

            try {

                if(_health_mode){
                    Log.i(BLE+"1111111111111", "180");
                    for(int i = 0; i < order.length;i++)
                    {
                        Log.i(BLE+"1111111111111", order[i]+"");
                        sendData(order[i]);
                        Thread.sleep(1000);
                    }

                   // sendData(180);
                }else {
                    Log.i(BLE+"1111111111111",test+"");
                    sendData(test);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Data=0;
        }
    }

    @Override
    public void onClick(View view) {
        if(_health_mode) {
            new SendListener().start();
        }
        Intent2Activity(HomeActivity.class);
    }

    int i = 0;

    private void setSettingText(TextView textView, String str[]) {
        if (i >= str.length) {
            i = 0;
        }
        textView.setText(str[i]);
        i++;

        LogUtils.d("TAG", i + "");
    }




    // 检测蓝牙的连接状态
    public void bluetoothDetection() {
        Log.i(BLE+"111111111111111","come in bluetoothdetection");
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        // 判断蓝牙是否打开
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Log.i(BLE+"1111111111"," adapter is null");
            Intent enableIntent = new Intent(mBluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 0);
        }

        // 获取已配对的蓝牙设备
        Set<BluetoothDevice> pairdevice = mBluetoothAdapter.getBondedDevices();
        if (pairdevice.size() > 0) {
            for (BluetoothDevice device : pairdevice) {
                if (device != null) {
                    Log.i("1111111111", "配对 " + device);
                     mBluetoothDevice = device;
                }
            }
        }
        else{
            Log.i(BLE+"1111111111111","pairdevice is null");
        }
         mBluetoothGatt =  mBluetoothDevice .connectGatt(ElectricFanSettingActivity.this, false,
         mCallback);

    }

}
