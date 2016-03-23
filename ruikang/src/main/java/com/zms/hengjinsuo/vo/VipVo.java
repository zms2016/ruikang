package com.zms.hengjinsuo.vo;
 
import com.zms.hengjinsuo.bean.TVip;

/**
 * 
 * 功能说明：由于 理财客户列表需要显示 用户类型 客户经理名字，所以需要扩展 理财客户表
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月2日/下午4:06:10
 */
public class VipVo {

	
	private TVip vip;
	
	
 private String level;
	
	private String  managerName;
	
	
	
	public TVip getVip() {
		return vip;
	}

	public void setVip(TVip vip) {
		this.vip = vip;
	}

 

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


}
