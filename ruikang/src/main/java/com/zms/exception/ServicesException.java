package com.zms.exception;


 /**
  * 
  * 功能说明： service的异常
  * 创建人：@author 330140511@qq.com  
  * 创建时间：2015年9月11日/上午10:48:07
  */
public class ServicesException extends BaseException{
	private static final long serialVersionUID = 1L;

	 public ServicesException(Throwable cause, String exceptionMsg  ){
		 super(cause, exceptionMsg);
	 }

	@Override
	public String getErrorLayer() {
		return ExpCodeConstant.ERROR_LAYER_SERVICE;
	}
	
	
}
