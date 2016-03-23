package com.zms.hengjinsuo.vo;


/**
 * 
 * 功能说明：  显示日志列表
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月25日/上午11:10:09
 */
public class LogVo {
	
	
	private String userName;
	
	private String modiTime;
	
	
	private String sourceIp;
	
	private String content;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

 

	public String getModiTime() {
		return modiTime;
	}

	public void setModiTime(String modiTime) {
		this.modiTime = modiTime;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
