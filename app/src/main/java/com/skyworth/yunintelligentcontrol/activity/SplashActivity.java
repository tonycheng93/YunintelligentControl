/**   
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 * 
 * @Title: SplashActivity.java 
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.ui.activity 
 * @Description: 闪屏页
 * @author: 包成
 * @date: 2016年8月12日 下午4:37:26
 * @version: V1.0   
 */
package com.skyworth.yunintelligentcontrol.activity;

import com.skyworth.yunintelligentcontrol.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * @ClassName: SplashActivity
 * @Description: 闪屏页
 * @author: 包成
 * @date: 2016年8月12日 下午4:37:26
 */
public class SplashActivity extends Activity {

	private static final int SPLASH_TIME = 3 * 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
				SplashActivity.this.finish();
			}
		}, SPLASH_TIME);
	}
}
