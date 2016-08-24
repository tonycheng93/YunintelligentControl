/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: ToastUtils.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.utils
 * @Description: 封装的Toast工具类
 * @author: 包成
 * @date: 2016年8月12日 下午7:36:41
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.utils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * @ClassName: ToastUtils
 * @Description: 封装的Toast工具类
 * @author: 包成
 * @date: 2016年8月12日 下午8:47:38
 */
public class ToastUtils {
    /**
     * 避免多次点击，创建多个Toast实例
     */
    private static Toast mToast;

    private ToastUtils() {
        // 私有化构造函数
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 长时间显示Toast
     */
    public static void showLongToast(Context context, int message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     */
    public static void showShortToast(Context context, int message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showShortToast(Context context, CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
