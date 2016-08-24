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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.utils.ToastUtils;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.ImageWheelAdapter;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.RecycleWheelView;
import com.skyworth.yunintelligentcontrol.view.recyclewheelview.RecycleWheelView.OnSelectItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: HomeActivity
 * @Description: App主页
 * @author: 包成
 * @date: 2016年8月12日 下午4:33:32
 */
public class HomeActivity extends BaseActivity implements OnClickListener, View.OnKeyListener {

    private ImageView mMode;// 白天or夜晚模式
    private ImageView mEquipmentCenter;// 设备管理中心

    private RecycleWheelView rv_wheel;
    private ImageWheelAdapter<Integer> mAdapter;

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
        mMode = (ImageView) findViewById(R.id.night);
        mEquipmentCenter = (ImageView) findViewById(R.id.equipment_management_center);

        mAdapter = new ImageWheelAdapter<>(this);
        rv_wheel = (RecycleWheelView) findViewById(R.id.rv_wheel);
        rv_wheel.setOnSelectListener(new OnSelectItemListener() {

            @Override
            public void onSelectChanged(int position) {
                ToastUtils.showShortToast(getApplicationContext(), String.format("select at: %d", position));
            }
        });
        setupWheel();
    }

    private void setupWheel() {
        rv_wheel.setAdapter(mAdapter);
        List<Integer> dataList = new ArrayList<>();
        dataList.add(R.drawable.electric_fan_normal);
        dataList.add(R.drawable.electric_fan_normal);
        dataList.add(R.drawable.electric_fan_normal);
        dataList.add(R.drawable.electric_fan_normal);
        dataList.add(R.drawable.electric_fan_normal);
        mAdapter.setData(dataList);
    }

    @Override
    public void initView() {

        rv_wheel.requestFocus();
        mMode.setOnClickListener(this);
        mEquipmentCenter.setOnClickListener(this);
        rv_wheel.setOnKeyListener(this);

    }

    @Override
    public void getData() {
        Intent data = getIntent();
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
//                Intent2Activity(ElectricFanActivity.class);
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_wheel:
                Intent2Activity(ElectricFanActivity.class);
                break;
            case R.id.night:
                // TODO 跳转到夜晚模式
                break;
            case R.id.equipment_management_center:
                Intent2Activity(EquipmentCenterActivity.class);
                break;
            default:
                break;
        }
    }

}
