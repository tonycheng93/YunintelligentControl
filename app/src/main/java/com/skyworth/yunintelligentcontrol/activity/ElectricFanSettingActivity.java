package com.skyworth.yunintelligentcontrol.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.TextView;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.utils.LogUtils;

public class ElectricFanSettingActivity extends BaseActivity implements OnKeyListener, OnClickListener {

    private TextView mSwitch;
    private TextView mAirVolume;
    private TextView mAirDirection;
    private TextView mTime;
    private Button mConfirm;

    private String switchTxt[] = {"关", "开"};
    private String airVolume[] = {"低", "中", "高"};
    private String airDirection[] = {"固定", "摆动"};
    private String time[] = {"关", "1H", "2H", "3H"};

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
        mConfirm = (Button) findViewById(R.id.equipment_setting_confirm);
    }

    @Override
    public void initView() {

        mSwitch.setText("关");
        mAirVolume.setText("低");
        mAirDirection.setText("固定");
        mTime.setText("关");

        mSwitch.setOnKeyListener(this);
        mAirVolume.setOnKeyListener(this);
        mAirDirection.setOnKeyListener(this);
        mTime.setOnKeyListener(this);
        mConfirm.setOnClickListener(this);

    }

    @Override
    public void getData() {

    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                switch (view.getId()) {
                    case R.id.equipment_setting_switch:
                        setSettingText(mSwitch, switchTxt);
                        break;
                    case R.id.equipment_setting_air_volume:
                        setSettingText(mAirVolume, airVolume);
                        break;
                    case R.id.equipment_setting_air_direction:
                        setSettingText(mAirDirection, airDirection);
                        break;
                    case R.id.equipment_setting_time:
                        setSettingText(mTime, time);
                        break;
                    default:
                        break;
                }
            }
            if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                switch (view.getId()) {
                    case R.id.equipment_setting_switch:
                        setSettingText(mSwitch, switchTxt);
                        break;
                    case R.id.equipment_setting_air_volume:
                        setSettingText(mAirVolume, airVolume);
                        break;
                    case R.id.equipment_setting_air_direction:
                        setSettingText(mAirDirection, airDirection);
                        break;
                    case R.id.equipment_setting_time:
                        setSettingText(mTime, time);
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
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
}
