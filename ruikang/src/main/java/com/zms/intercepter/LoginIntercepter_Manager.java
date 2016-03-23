package com.zms.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * 功能说明： 拦截器
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月11日/下午5:04:27
 */
public class LoginIntercepter_Manager  implements  HandlerInterceptor {

	
	private static Logger log = Logger.getLogger(LoginIntercepter_Manager.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		 //进入controller之前  拥有 身份认证 授权
		
		/*表示放行*/
         //System.out.println("进入登录拦截器");
		   if (request.getSession().getAttribute("username")!=null)
		     {
			//   System.out.println("当前用户:"+request.getSession().getAttribute("username"));
			//   System.out.println("拦截器获取到的路径:"+request.getRequestURI());	 
		    	   if ( ! request.getRequestURI().contains("manager/main.html"))
		    	   {      
		    	           request.getSession().setAttribute("nowUrl", request.getRequestURI());
		    	   }
		    	 return true;
		     }
		     else {
		    	 
		       //   System.out.println("没登录！");
		          request.getRequestDispatcher("/manager/login.html").forward(request, response); 
		    	 
		    	
		    	 
				return  false;
			}
		     
	 
	 
		

		 //拦截 不向下执行
	//	return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	        //进入 controller之后 返回 视图之前
		
		  
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
 
	}

}
