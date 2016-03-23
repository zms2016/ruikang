package com.zms.hengjinsuo.vo;

import java.util.List;

import com.zms.hengjinsuo.bean.TSchedule;

/**
 * 
 * 功能说明：理财工具
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年12月23日/上午11:16:32
 */
public class MoneyToolInfo {
	
	private String  totalApr;
	private List<MySchedule> schedules;
	public String getTotalApr() {
		return totalApr;
	}
	public void setTotalApr(String totalApr) {
		this.totalApr = totalApr;
	}
	public List<MySchedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<MySchedule> schedules) {
		this.schedules = schedules;
	}
 
	
	

}
