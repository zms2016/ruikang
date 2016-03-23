package com.zms.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.log.Log;
import com.zms.hengjinsuo.user.controller.UserController;


/**
 * 
 * 功能说明：权限拦截器
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月11日/下午5:04:27
 */
public class RoleIntercepter  implements  HandlerInterceptor {

	private static Logger log = Logger.getLogger(RoleIntercepter.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		  log.debug("准备进入权限拦截器");
		  
		  
		     if (request.getSession().getAttribute("username")!=null)
		     {
		    	 log.debug("已经登录了， 开始判断是否有权限!请求的地址:"+	 request.getRequestURI());
		    	
		    	  
		    	 
		    	 return true;
		     }
		     else {
		    	 
		    	 log.debug("还没登录，请先登录!");
		          request.getRequestDispatcher("manager/login.html").forward(request, response); 
		          
				return  false;
			}
		   
		   
 
		// return true;
		

		 //拦截 不向下执行
	//	return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	      
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		 
		
	}

}
