/**   
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 * 
 * @Title: Equipment.java 
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.model 
 * @Description: 设备的基类
 * @author: 李晓萌   
 * @date: 2016年8月12日 下午4:20:28 
 * @version: V1.0   
 */
package com.skyworth.yunintelligentcontrol.model;

/**
 * @ClassName: Equipment
 * @Description: 设备的基类
 * @author: 李晓萌
 * @date: 2016年8月12日 下午4:20:28
 */
public class Equipment {
	private int status = 0;;
	private Object equipment = null;

	public int getStatus() {// 0代表设备未开启，1代表设备为开启状态
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getType() {
		return equipment;
	}

	public void setType(Object equipment) {
		this.equipment = equipment;
	}
}
