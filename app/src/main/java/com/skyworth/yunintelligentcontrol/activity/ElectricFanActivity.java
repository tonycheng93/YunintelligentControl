package com.skyworth.yunintelligentcontrol.activity;

import java.util.ArrayList;
import java.util.List;

import com.skyworth.yunintelligentcontrol.R;
import com.skyworth.yunintelligentcontrol.activity.base.BaseActivity;
import com.skyworth.yunintelligentcontrol.view.materialspinner.MaterialSpinner;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * 
 * @ClassName: ElectricFanActivity
 * @Description: 电风扇操作页面
 * @author: 包成
 * @date: 2016年8月16日 下午9:15:56
 */
public class ElectricFanActivity extends BaseActivity
		implements OnItemSelectedListener, OnKeyListener, OnClickListener {

	private MaterialSpinner mElecFanBrand;
	private MaterialSpinner mElecFanRemember;

	private Button mConfirm;

	private List<String> brands = new ArrayList<String>();
	private List<String> remember = new ArrayList<>();

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
		mConfirm = (Button) findViewById(R.id.elec_fan_btn_confirm);
	}

	@Override
	public void initView() {
		mElecFanBrand.setFocusable(true);
		mElecFanBrand.setFocusableInTouchMode(true);
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

		mConfirm.setOnClickListener(this);
	}

	@Override
	public void getData() {

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
				if (view.getId() == R.id.elec_fan_brand_choice) {
					mElecFanBrand.expand();
				}
				if (view.getId() == R.id.elec_fan_remember_or_not) {
					mElecFanRemember.expand();
				}
			}
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.elec_fan_btn_confirm:
			Intent2Activity(ElectricFanSettingActivity.class);
			break;

		default:
			break;
		}
	}
}
