package com.zms.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zms.hengjinsuo.user.controller.UserController;

/**
 * 
 * 功能说明：全局异常 处理
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月11日/上午10:55:08
 */
public class GobalExceptionResolver   implements  HandlerExceptionResolver {

	private static Logger log = Logger.getLogger( GobalExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		 
		     ModelAndView  modelAndView=new ModelAndView();
		     
		     
		      
		     
		     String  errorMsg="系统错误";
		
		     if ( ex instanceof BaseException)
		     {
		    	 BaseException cException=(BaseException)ex;
		    	 
		    	 errorMsg=cException.getErrorLayer()+":"+ex.getMessage();
		    	 
		     }
		     else {
		    	   log.error("自定义异常，服务器出错:"+ex.getMessage());
				ex.printStackTrace();
		    	 errorMsg="访问资源出错！";
			}
		
		     modelAndView.addObject("message", errorMsg);
		     modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
