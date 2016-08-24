/**   
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 * 
 * @Title: TV.java 
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.model 
 * @Description: 电视机类，用于获取电视机设备的状态以及相应属性，获取电视机对象
 * @author: 李晓萌 
 * @date: 2016年8月12日 下午2:29:24 
 * @version: V1.0   
 */
package com.skyworth.yunintelligentcontrol.model;

/**
 * @ClassName: TV
 * @Description: TODO
 * @author: 李晓萌
 * @date: 2016年8月12日 下午2:29:24
 */
public class TV extends Equipment {
	public TV(int status, TV tv) {
		this.setStatus(status);
		this.setType(tv);
	}
}
