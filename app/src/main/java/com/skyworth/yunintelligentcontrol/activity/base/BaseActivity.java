/**   
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 * 
 * @Title: BaseActivity.java 
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.ui.activity.base
 * @Description: Activity父类
 * @author: 包成
 * @date: 2016年8月12日 下午8:21:56
 * @version: V1.0   
 */
package com.skyworth.yunintelligentcontrol.activity.base;

import com.skyworth.yunintelligentcontrol.utils.ActivityUtils;
import com.skyworth.yunintelligentcontrol.utils.LogUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * 
 * @ClassName: BaseActivity
 * @Description: 定义一个所有Activity的父类，抽取Activity通用方法
 * @author: 包成
 * @date: 2016年8月13日 下午8:21:56
 */
public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.d("BaseActivity", getClass().getSimpleName());
		ActivityUtils.addActivity(this);

		init();
	}

	private void init() {
		setContentView();
		findViews();
		initView();
		getData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityUtils.removeActivity(this);
	}
	
	public void Intent2Activity(@NonNull Class<?> targetActivity) {
		startActivity(new Intent(this,targetActivity));
	}

	public abstract void setContentView();// 设置Activity布局

	public abstract void findViews();// findViewById操作
	
	public abstract void initView();//view初始化操作

	public abstract void getData();// 获取传递的数据
}
