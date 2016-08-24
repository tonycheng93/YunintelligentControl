/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: App.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.app
 * @Description: 用来处理整个App的相关初始化操作
 * @author: 包成
 * @date: 2016年8月12日 下午7:36:41
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.app;

import com.skyworth.yunintelligentcontrol.config.AppDensityConfig;

import android.app.Application;
import android.content.Context;

/**
 *
 * @ClassName: App
 * @Description: 用来处理整个App的相关初始化操作。
 * @author: 包成
 * @date: 2016年8月12日 下午7:36:41
 */
public class App extends Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        AppDensityConfig.setResolutionAndDpiDiv(mContext);
    }
}
