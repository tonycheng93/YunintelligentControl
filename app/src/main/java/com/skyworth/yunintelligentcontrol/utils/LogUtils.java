/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: LogUtils.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.utils
 * @Description: 封装的log日志工具类
 * @author: 包成
 * @date: 2016年8月12日 下午7:36:41
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.utils;

import com.skyworth.yunintelligentcontrol.config.Constant;

import android.util.Log;

/**
 *
 * @ClassName: LogUtils
 * @Description: 日志工具类
 * @author: 包成
 * @date: 2016年8月12日 下午8:12:23
 */
public class LogUtils {
    private static final String TAG = "yunintelligentcontrol";

    private LogUtils() {
        // 私有化构造函数
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 四个默认定义的TAG函数
     */
    public static void i(String msg) {
        if (Constant.ISDEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (Constant.ISDEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (Constant.ISDEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (Constant.ISDEBUG) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 四个可以自定义TAG的函数
     */
    public static void i(String tag, String msg) {
        if (Constant.ISDEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Constant.ISDEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Constant.ISDEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Constant.ISDEBUG) {
            Log.v(tag, msg);
        }
    }
}
