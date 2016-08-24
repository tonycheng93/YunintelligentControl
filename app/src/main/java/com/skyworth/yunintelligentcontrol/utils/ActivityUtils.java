/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: ActivityUtils.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.utils
 * @Description: 封装的Activity工具类
 * @author: 包成
 * @date: 2016年8月12日 下午7:59:48
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 *
 * @ClassName: ActivityUtils
 * @Description: Activity管理类，添加、移除
 * @author: 包成
 * @date: 2016年8月13日 下午7:59:48
 */
public class ActivityUtils {

    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加Activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 清除所有Activity
     */
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}