package com.skyworth.yunintelligentcontrol.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class AirConditionSettingActivity extends BaseActivity {

    private LinearLayout mAirConditionSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_air_condition_setting);
    }

    @Override
    public void findViews() {
        mAirConditionSetting = (LinearLayout) findViewById(R.id.air_condition_setting);
    }

    @Override
    public void initView() {
        mAirConditionSetting.requestFocus();
        mAirConditionSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent2Activity(HomeActivity.class);
            }
        });
    }

    @Override
    public void getData() {

    }
}
