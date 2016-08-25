package com.skyworth.yunintelligentcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.config.Constant;
import com.skyworth.yunintelligentcontrol.view.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ElectricFanActivity
 * @Description: 电风扇操作页面
 * @author: 包成
 * @date: 2016年8月16日 下午9:15:56
 */
public class ElectricFanActivity extends BaseActivity
        implements OnItemSelectedListener, OnKeyListener, OnClickListener {

    public static final boolean EXTRA_FLAG = true;
    public String equipment_flag = "";

    private MaterialSpinner mElecFanBrand;
    private MaterialSpinner mElecFanRemember;
    private MaterialSpinner mElecFanMode;
    private Button mConfirm;

    private List<String> brands = new ArrayList<>();
    private List<String> remember = new ArrayList<>();
    private List<String> mode = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activtiy_electric_fan);
    }

    @Override
    public void findViews() {
        mElecFanBrand = (MaterialSpinner) findViewById(R.id.elec_fan_brand_choice);
        mElecFanRemember = (MaterialSpinner) findViewById(R.id.elec_fan_remember_or_not);
        mElecFanMode = (MaterialSpinner) findViewById(R.id.elec_fan_mode_choice);
        mConfirm = (Button) findViewById(R.id.elec_fan_btn_confirm);
    }

    @Override
    public void initView() {
        mElecFanBrand.requestFocus();

        brands.add("澳柯玛");
        brands.add("创维");
        brands.add("格力");
        brands.add("美的");
        brands.add("志高");
        mElecFanBrand.setItems(brands);
        mElecFanBrand.setOnKeyListener(this);

        remember.add("是");
        remember.add("否");
        mElecFanRemember.setItems(remember);
        mElecFanRemember.setOnKeyListener(this);

        mode.add("是");
        mode.add("否");
        mElecFanMode.setItems(mode);
        mElecFanMode.setOnKeyListener(this);

        mConfirm.setOnClickListener(this);
    }

    @Override
    public void getData() {
        Intent data = getIntent();
        equipment_flag = data.getStringExtra(Constant.EXTRA_NAME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:

                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                switch (view.getId()) {
                    case R.id.elec_fan_brand_choice:
                        mElecFanBrand.expand();
                        break;
                    case R.id.elec_fan_remember_or_not:
                        mElecFanRemember.expand();
                        break;
                    case R.id.elec_fan_mode_choice:
                        mElecFanMode.expand();
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
        switch (view.getId()) {
            case R.id.elec_fan_btn_confirm:
                if (HomeActivity.BOX_FLAG.equalsIgnoreCase(equipment_flag)) {
                    Intent2Activity(BoxSettingActivity.class);
                } else if (HomeActivity.AIR_CONDITION_FLAG.equalsIgnoreCase(equipment_flag)) {
                    Intent2Activity(AirConditionSettingActivity.class);
                } else if (HomeActivity.ELECTRIC_FAN_FLAG.equalsIgnoreCase(equipment_flag)) {
                    if ("否".equalsIgnoreCase(mElecFanMode.getText().toString())) {
                        Intent2Activity(ElectricFanSettingActivity.class);
                    } else {
                        Intent intent = new Intent(ElectricFanActivity.this, ElectricFanSettingActivity.class);
                        intent.putExtra("_health_mode", EXTRA_FLAG);
                        startActivity(intent);
                    }
                }else if (HomeActivity.FREEZER_FLAG.equalsIgnoreCase(equipment_flag)){
                    Intent2Activity(FreezerSettingActivity.class);
                }else if (HomeActivity.AIR_CLEANER_FLAG.equalsIgnoreCase(equipment_flag)){
                    Intent2Activity(AirCleanerSettingActivity.class);
                }
                break;

            default:
                break;
        }
    }
}
