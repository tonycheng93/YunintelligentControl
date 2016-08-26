package com.skyworth.yunintelligentcontrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class AirCleanerSettingActivity extends BaseActivity {

    private LinearLayout mAirCleanerSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_air_cleaner_setting);
    }

    @Override
    public void findViews() {
        mAirCleanerSetting = (LinearLayout) findViewById(R.id.air_cleaner_setting);
    }

    @Override
    public void initView() {
        mAirCleanerSetting.requestFocus();

        mAirCleanerSetting.setOnClickListener(new View.OnClickListener() {
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
