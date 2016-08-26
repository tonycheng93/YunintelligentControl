package com.skyworth.yunintelligentcontrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;

public class FreezerSettingActivity extends BaseActivity {

    private LinearLayout mFreezerSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_freezer);

    }

    @Override
    public void findViews() {
        mFreezerSetting = (LinearLayout) findViewById(R.id.freezer_setting);
    }

    @Override
    public void initView() {
        mFreezerSetting.requestFocus();
        mFreezerSetting.setOnClickListener(new View.OnClickListener() {
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
