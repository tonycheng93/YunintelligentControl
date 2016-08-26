/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: HomeActivity.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.ui.activity
 * @Description: App主页
 * @author: 包成
 * @date: 2016年8月12日 下午4:33:32
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.utils.ActivityUtils;
import com.skyworth.yunintelligentcontrol.utils.LogUtils;
import com.skyworth.yunintelligentcontrol.utils.NightModeUtils;
import com.skyworth.yunintelligentcontrol.utils.SharedPreferencesUtils;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.ImageWheelAdapter;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.RecycleWheelView;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.RecycleWheelView.OnSelectItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HomeActivity
 * @Description: App主页
 * @author: 包成
 * @date: 2016年8月12日 下午4:33:32
 */
public class HomeActivity extends BaseActivity implements OnClickListener, View.OnKeyListener {

    private ImageView mNight;//夜晚模式
    private ImageView mDayTime;// 白天模式
    private boolean mNightIsShown;//夜晚模式是否可见（默认可见）
    private ImageView mEquipmentCenter;// 设备管理中心

    private RecycleWheelView rv_wheel;
    private ImageWheelAdapter<Integer> mAdapter;

    /*具体进入哪个设备的标志*/
    public static final String BOX_FLAG = "box";
    public static final String AIR_CONDITION_FLAG = "air_condition";
    public static final String ELECTRIC_FAN_FLAG = "electric_fan";
    public static final String FREEZER_FLAG = "freezer";
    public static final String AIR_CLEANER_FLAG = "air_cleaner";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void findViews() {
        mDayTime = (ImageView) findViewById(R.id.daytime);
        mNight = (ImageView) findViewById(R.id.night);
        mEquipmentCenter = (ImageView) findViewById(R.id.equipment_management_center);

        mAdapter = new ImageWheelAdapter<>(this);
        rv_wheel = (RecycleWheelView) findViewById(R.id.rv_wheel);
        rv_wheel.setOnSelectListener(new OnSelectItemListener() {

            @Override
            public void onSelectChanged(int position) {

            }
        });
        setupWheel();
    }

    private void setupWheel() {
        rv_wheel.setAdapter(mAdapter);
        List<Integer> dataList = new ArrayList<>();
        dataList.add(R.drawable.box_focus);
        dataList.add(R.drawable.air_condition_focus);
        dataList.add(R.drawable.electric_fan_focus);
        dataList.add(R.drawable.freezer_focus);
        dataList.add(R.drawable.air_cleaner_focus);
        mAdapter.setData(dataList);
    }

    @Override
    public void initView() {

        rv_wheel.requestFocus();

        mNightIsShown = SharedPreferencesUtils.getBoolean("night_mode");
        if (mNightIsShown){
            mNight.setVisibility(View.VISIBLE);
            mDayTime.setVisibility(View.GONE);
        }else {
            mNight.setVisibility(View.GONE);
            mDayTime.setVisibility(View.VISIBLE);
        }

        mDayTime.setOnClickListener(this);
        mNight.setOnClickListener(this);
        mEquipmentCenter.setOnClickListener(this);
        rv_wheel.setOnKeyListener(this);

    }

    @Override
    public void getData() {
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            LogUtils.d("test", "onKeyDown : " + keyCode);
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (view == rv_wheel)
                        return changeRecyclePos(keyCode);
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    if (rv_wheel.getSelectPosition() == 0) {
                        Intent2Activity(BOX_FLAG, ElectricFanActivity.class);
                    } else if (rv_wheel.getSelectPosition() == 1) {
                        Intent2Activity(AIR_CONDITION_FLAG, ElectricFanActivity.class);
                    } else if (rv_wheel.getSelectPosition() == 2) {
                        Intent2Activity(ELECTRIC_FAN_FLAG, ElectricFanActivity.class);
                    } else if (rv_wheel.getSelectPosition() == 3) {
                        Intent2Activity(FREEZER_FLAG, ElectricFanActivity.class);
                    } else if (rv_wheel.getSelectPosition() == 4) {
                        Intent2Activity(AIR_CLEANER_FLAG, ElectricFanActivity.class);
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (view == rv_wheel) {
                        if (mNight.isShown()) {
                            mNight.requestFocus();
                        } else {
                            mDayTime.requestFocus();
                        }
                        return true;
                    }
                    return true;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (view == mNight || view == mEquipmentCenter || view == mDayTime) {
                        rv_wheel.requestFocus();
                        return true;
                    }
                    return true;
            }
        }
        return false;
    }

    private boolean changeRecyclePos(int keyCode) {
        int pos = rv_wheel.getSelectPosition();
        int count = rv_wheel.getAdapter().getItemCount();
        LogUtils.d("test", "pos=" + pos + ", count=" + count);
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (pos > 0)
                    pos--;
                rv_wheel.smoothScrollToPosition(pos);
                return true;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (pos < count - 1)
                    pos++;
                rv_wheel.smoothScrollToPosition(pos);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.night:
                if (mNight.isShown()) {
                    mNight.setVisibility(View.GONE);
                    mDayTime.setVisibility(View.VISIBLE);
                    SharedPreferencesUtils.putBoolean("night_mode",false);
                    mDayTime.requestFocus();
                    NightModeUtils.changeToNightMode(HomeActivity.this);
                }
                break;
            case R.id.daytime:
                if (mDayTime.isShown()) {
                    mNight.setVisibility(View.VISIBLE);
                    mDayTime.setVisibility(View.GONE);
                    SharedPreferencesUtils.putBoolean("night_mode",true);
                    mNight.requestFocus();
                    NightModeUtils.changeToDayMode(HomeActivity.this);
                }
                break;
            case R.id.equipment_management_center:
                Intent2Activity(EquipmentCenterActivity.class);
                break;
            default:
                break;
        }
    }



    // 检测蓝牙的连接状态
//    public void bluetoothDetection() {
//        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        mBluetoothAdapter = mBluetoothManager.getAdapter();
//        // 判断蓝牙是否打开
//        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(mBluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, 0);
//        }
//        // 获取已配对的蓝牙设备
//        Set<BluetoothDevice> pairdevice = mBluetoothAdapter.getBondedDevices();
//        if (pairdevice.size() > 0) {
//            for (BluetoothDevice device : pairdevice) {
//                if (device != null) {
//                    Log.i("1111111111", " " + device);
//                    mBluetoothDevice = device;
//                }
//            }
//        }
////         获取GATT服务
////         mBLEControl.getBluetoothGatt(HomeActivity.this,false);
////         mBluetoothGatt = App.mBluetoothDevice .connectGatt(BLEControl.this, false,
////         mCallback);
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.finishAll();
    }
}
