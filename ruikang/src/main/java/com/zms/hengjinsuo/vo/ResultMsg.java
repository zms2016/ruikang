package com.zms.hengjinsuo.vo;


/**
 * 
 * 功能说明：用来封装  提交事务 的反馈页面 消息体
 * 创建人：330140511@qq.com  
 * 创建时间：2015年10月10日/上午11:34:04
 */
public class ResultMsg {
	
	
	  // 0 失败，  1 成功， 2 异常
	private int code=0;
	
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
