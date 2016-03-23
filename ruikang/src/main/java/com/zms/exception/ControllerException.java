package com.zms.exception;


 /**
  * 
  * 功能说明： controller层的异常
  * 创建人：@author 330140511@qq.com  
  * 创建时间：2015年9月11日/上午10:48:07
  */
public class ControllerException extends BaseException{
	private static final long serialVersionUID = 1L;

	 public ControllerException(Throwable cause, String exceptionMsg  ){
		 super(cause, exceptionMsg);
	 }

	@Override
	public String getErrorLayer() {
		return ExpCodeConstant.ERROR_LAYER_CONTROLLER;
	}
}
