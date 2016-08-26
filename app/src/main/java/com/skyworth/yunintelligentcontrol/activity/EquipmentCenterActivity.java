package com.skyworth.yunintelligentcontrol.activity;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.control.EquipmentManager;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class EquipmentCenterActivity extends BaseActivity {

	private ImageView mElectricFan;

	private ImageView mAddEquipment;

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_equipment_center);
	}

	@Override
	public void findViews() {
		mElectricFan = (ImageView) findViewById(R.id.electric_fan_center);
		mAddEquipment = (ImageView) findViewById(R.id.add_equipment);
	}

	@Override
	public void initView() {
		mElectricFan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent2Activity(ElectricFanSettingActivity.class);
			}
		});
		mAddEquipment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent2Activity(EquipManagmentCenterActivity.class);
			}
		});
	}

	@Override
	public void getData() {

	}

}
