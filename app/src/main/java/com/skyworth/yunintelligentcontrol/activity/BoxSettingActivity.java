package com.skyworth.yunintelligentcontrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class BoxSettingActivity extends BaseActivity {

    private LinearLayout mBoxSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_box_setting);

    }

    @Override
    public void findViews() {
        mBoxSetting = (LinearLayout) findViewById(R.id.box_setting);
    }

    @Override
    public void initView() {
mBoxSetting.requestFocus();
        mBoxSetting.setOnClickListener(new View.OnClickListener() {
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
