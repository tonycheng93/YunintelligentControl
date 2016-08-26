package com.skyworth.yunintelligentcontrol.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by 包成 on 2016/8/26.
 * 主题切换的帮助类
 */
public class NightModeUtils {

    public static void changeToNightMode(AppCompatActivity activity) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity.recreate();
    }

    public static void changeToDayMode(AppCompatActivity activity) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        activity.recreate();
    }
}
