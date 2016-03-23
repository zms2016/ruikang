package com.zms.exception;

import org.springframework.util.StringUtils;

/**
 * 
 * 功能说明：异常基础类  定义 3个参数  1  异常发生的层级 （controller  service dao)   2    ,  message  
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月11日/上午10:43:40
 */
public abstract class BaseException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private  String  errorLayer;   //异常的层级  对应 ExpCodeConstant.java
	private String exceptionMsg;  //异常消息文本
	private Object[] values;
	
	public BaseException(Throwable ex,String exceptionMsg ){
	    super(StringUtils.isEmpty(exceptionMsg) ? ex.getMessage() : exceptionMsg, ex);
	    this.errorLayer="默认的基本异常";
	    this.setExceptionMsg(exceptionMsg);
	    
	}
 
	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
 

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}




	public String getErrorLayer() {
		return errorLayer;
	}




	public void setErrorLayer(String errorLayer) {
		this.errorLayer = errorLayer;
	}

 
	
}
