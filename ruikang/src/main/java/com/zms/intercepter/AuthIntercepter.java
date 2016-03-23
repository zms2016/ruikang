package com.zms.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * 功能说明：权限拦截器
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月11日/下午5:04:27
 */
public class AuthIntercepter  implements  HandlerInterceptor {

	
	private  static  Logger log=Logger.getLogger(AuthIntercepter.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		 //进入controller之前  拥有 身份认证 授权
		
		/*表示放行*/
		  log.debug("准备进入第2个拦截器");
		  
		 return true;
		

		 //拦截 不向下执行
	//	return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	        //进入 controller之后 返回 视图之前
		
		  log.debug("第2个拦截器中");
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		 log.debug("第2个拦截器之后");
		
	}

}
