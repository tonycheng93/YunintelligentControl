/**   
 * Copyright © 2016 二组《云智控遥控器》. All rights reserved.
 * 
 * @Title: Airconditioning.java 
 * @Prject: YunIntelligentControl
 * @Package: com.skyworth.yunintelligentcontrol.model 
 * @Description: 实例化空调对象，获取空调独有的特性
 * @author: 李晓萌 
 * @date: 2016年8月12日 下午4:28:35 
 * @version: V1.0   
 */
package com.skyworth.yunintelligentcontrol.model;
/**
 * @ClassName: Airconditioning 
 * @Description: TODO
 * @author: 李晓萌
 * @date: 2016年8月12日 下午4:28:35
 */
public class Airconditioning extends Equipment {
	private int temperature = 0;

	public Airconditioning(int status, Airconditioning airconditioning, int temperature) {
		this.setStatus(status);
		this.setType(airconditioning);
		this.temperature = temperature;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
}
