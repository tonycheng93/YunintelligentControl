package com.skyworth.yunintelligentcontrol.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class EquipManagmentCenterActivity extends BaseActivity implements View.OnClickListener {

    private Button mElectricFan;
    private Button mFreezer;
    private Button mAirCondition;
    private Button mAirCleaner;
    private Button mBox;

    private LinearLayout mLinearLayout;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_equip_managment_center);
    }

    @Override
    public void findViews() {
        mElectricFan = (Button) findViewById(R.id.electric_fan_channel);
        mFreezer = (Button) findViewById(R.id.freezer_channel);
        mAirCondition = (Button) findViewById(R.id.air_condition_channel);
        mAirCleaner = (Button) findViewById(R.id.air_cleaner_channel);
        mBox = (Button) findViewById(R.id.box_channel);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_background);
    }

    @Override
    public void initView() {
        mElectricFan.setOnClickListener(this);
        mFreezer.setOnClickListener(this);
        mAirCondition.setOnClickListener(this);
        mAirCleaner.setOnClickListener(this);
        mBox.setOnClickListener(this);
    }

    @Override
    public void getData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.electric_fan_channel:
                loadData(R.drawable.electric_fan_brand_choice);
                break;
            case R.id.freezer_channel:
                loadData(R.drawable.electric_fan_brand_choice);
                break;
            case R.id.air_condition_channel:
                loadData(R.drawable.electric_fan_brand_choice);
                break;
            case R.id.air_cleaner_channel:
                loadData(R.drawable.electric_fan_brand_choice);
                break;
            case R.id.box_channel:
                loadData(R.drawable.electric_fan_brand_choice);
                break;
            default:
                break;

        }
    }

    private void loadData(final int resId) {
        final ProgressDialog dialog = new ProgressDialog(this);
        SwipeRefreshLayout layout = new SwipeRefreshLayout(this);
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLinearLayout.setBackgroundResource(resId);
                dialog.dismiss();
            }
        }, 1000);
    }
}
