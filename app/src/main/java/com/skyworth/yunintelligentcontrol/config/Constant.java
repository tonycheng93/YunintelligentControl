/**
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 *
 * @Title: Constant.java
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.config
 * @Description: 常量类，用来配置一些系统常量
 * @author: 包成
 * @date: 2016年8月12日 下午7:36:41
 * @version: V1.0
 */
package com.skyworth.yunintelligentcontrol.config;

/**
 *
 * @ClassName: Constant
 * @Description: 常量类，用来配置一些系统常量
 * @author: 包成
 * @date: 2016年8月12日 下午8:16:04
 */
public class Constant {
    // 开发阶段设置输出log日志，发布后关闭log日志输出
    public static final boolean ISDEBUG = true;
    //Activity之间参数传递标志
    public static final String EXTRA_NAME = "extra_flag";
    //保存设备设置界面的参数值
    public static final String SWITCH_FLAG="switch";
    public static final String AIR_VOLUME_FLAG="air_volume";
    public static final String AIR_DIRECTION_FLAG="air_direction";
    public static final String TIME_FLAG="time";
    //判断是否第一次进入设置界面
    public static final String IS_FIRST_IN = "is_first_in";
}
