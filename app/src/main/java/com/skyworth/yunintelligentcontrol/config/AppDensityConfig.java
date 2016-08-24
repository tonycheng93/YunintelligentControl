package com.skyworth.yunintelligentcontrol.config;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.skyworth.yunintelligentcontrol.utils.LogUtils;

/**
 *
 * @ClassName: AppDensityConfig
 * @Description: 适配屏幕配置文件
 * @author: 包成
 * @date: 2016年8月22日 上午11:56:03
 */
public class AppDensityConfig {
    private static float cachedResolutionDiv = 1f;
    private static float cachedDpiDiv = 1f;

    /**
     *
     * @Title: getDisplayDensity
     * @Description: 得到屏幕密度
     * @param context
     * @return
     * @return: float
     */
    private static float getDisplayDensity(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        float density = dm.density;
        return density;
    }

    /**
     *
     * @Title: getDisplayWidth
     * @Description: 得到屏幕的宽度
     * @param context
     * @return
     * @return: int
     */
    public static int getDisplayWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if (display == null) {
            return 1920;
        }
        return display.getWidth();
    }

    /**
     *
     * @Title: setResolutionAndDpiDiv
     * @Description: 设置分辨率
     * @param context
     * @return: void
     */
    public static void setResolutionAndDpiDiv(Context context) {
        int width = getDisplayWidth(context);
        cachedResolutionDiv = 1.0f;
        cachedDpiDiv = 1.0f;
        if (width == 3840) {
            cachedResolutionDiv = 0.5f;
        } else if (width == 1920) {
            cachedResolutionDiv = 1;
        } else if (width == 1366) {
            cachedResolutionDiv = 1.4f;
        } else if (width == 1280) {
            cachedResolutionDiv = 1.5f;
        }

        cachedDpiDiv = cachedResolutionDiv * getDisplayDensity(context);

        LogUtils.d("set ResolutionDiv : " + cachedResolutionDiv);
        LogUtils.d("set DpiDiv : " + cachedDpiDiv);
        ;
    }

    /**
     * 获取自适应分辨率的布局大小
     *
     * @Title: getResolutionValue
     * @Description: TODO
     * @param value
     * @return
     * @return: int
     */
    public static int getResolutionValue(int value) {
        return (int) (value <= 0 ? value : (value / cachedResolutionDiv));
    }

    /**
     *
     * @Title: getDpiValue
     * @Description: 获取自适应屏幕密度的文本大小
     * @param value
     * @return
     * @return: int
     */
    public static int getDpiValue(int value) {
        return (int) (value <= 0 ? value : (value / cachedDpiDiv));
    }
}
