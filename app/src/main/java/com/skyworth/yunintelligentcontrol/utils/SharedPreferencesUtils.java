/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: SharedPreferencesUtils.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.utils
 * @Description: SharedPreferences工具类
 * @author: 包成
 * @date: 2016年8月12日 下午4:33:32
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.utils;

import com.skyworth.yunintelligentcontrol.app.App;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @ClassName: SharedPreferencesUtils
 * @Description: SharedPreferences工具类，用来保存简单数据。
 * @author: 包成
 * @date: 2016年8月12日 下午5:53:19
 */
public class SharedPreferencesUtils {
    private static SharedPreferences mSharedPreferences;

    private static synchronized SharedPreferences getPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.mContext);
        }
        return mSharedPreferences;
    }

    // 打印所有
    public static void print() {
        System.out.println(getPreferences().getAll());
    }

    // 清空保存在SharePreferences下的数据
    public static void clearAll() {
        getPreferences().edit().clear().commit();
    }

    // 保存字符串
    public static void putString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    // 读取字符串
    public static String getString(String key) {
        return getPreferences().getString(key, null);
    }

    // 移除字段
    public static void removeString(String key) {
        getPreferences().edit().remove(key).commit();
    }

    //保存布尔值
    public static void putBoolean(String key,boolean value){
        getPreferences().edit().putBoolean(key,value).commit();
    }

    //读取布尔值
    public static boolean getBoolean(String key){
        return getPreferences().getBoolean(key,true);
    }
}
