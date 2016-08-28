package com.skyworth.yunintelligentcontrol.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class EquipManagmentCenterActivity extends BaseActivity implements View.OnKeyListener {

    private Button mElectricFan;
    private Button mFreezer;
    private Button mAirCondition;
    private Button mAirCleaner;
    private Button mBox;
    private RelativeLayout mLayout;

    private CircleProgressBar mProgressBar;

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
        mLayout = (RelativeLayout) findViewById(R.id.rl_equip_managment_background);
        mElectricFan = (Button) findViewById(R.id.electric_fan_channel);
        mFreezer = (Button) findViewById(R.id.freezer_channel);
        mAirCondition = (Button) findViewById(R.id.air_condition_channel);
        mAirCleaner = (Button) findViewById(R.id.air_cleaner_channel);
        mBox = (Button) findViewById(R.id.box_channel);
        mProgressBar = (CircleProgressBar) findViewById(R.id.progressbar_loading);
    }

    @Override
    public void initView() {
        mElectricFan.requestFocus();
        mElectricFan.setOnKeyListener(this);
        mFreezer.setOnKeyListener(this);
        mAirCondition.setOnKeyListener(this);
        mAirCleaner.setOnKeyListener(this);
        mBox.setOnKeyListener(this);
    }

    @Override
    public void getData() {

    }

    private void loadData(final int resId) {
        mProgressBar.setVisibility(View.VISIBLE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayout.setBackgroundResource(resId);
                mProgressBar.setVisibility(View.GONE);
            }
        }, 1000);

    }


    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (view == mElectricFan){
                    loadData(R.drawable.electric_fan_brand_choice);
                }else if (view == mFreezer){
                    loadData(R.drawable.freezer_brand_choice);
                }else if (view == mAirCondition){
                    loadData(R.drawable.air_condition_brand_choice);
                }else if (view == mAirCleaner){
                    loadData(R.drawable.air_cleaner_brand_choice);
                }else if (view == mBox){
                    loadData(R.drawable.box_brand_choice);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                if (view == mElectricFan){
                    loadData(R.drawable.electric_fan_brand_choice);
                }else if (view == mFreezer){
                    loadData(R.drawable.freezer_brand_choice);
                }else if (view == mAirCondition){
                    loadData(R.drawable.air_condition_brand_choice);
                }else if (view == mAirCleaner){
                    loadData(R.drawable.air_cleaner_brand_choice);
                }else if (view == mBox){
                    loadData(R.drawable.box_brand_choice);
                }
                break;
            default:
                break;
        }
        return false;
    }
}
