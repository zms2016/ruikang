package com.zms.hengjinsuo.login.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.login.services.LoginService;
import com.zms.hengjinsuo.schedule.services.ScheduleService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.MenuVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ScheduleVo;

@Controller
@RequestMapping("/manager")
public class LoginController {

	private static Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

  @Autowired
  private LoginService loginService;
  

  
  @Autowired
  private ScheduleService scheduleService;
	

	// 跳转到登录界面
	@RequestMapping("/login")
	public String  login(HttpServletRequest request,HttpSession session ) throws Exception
	{
	 
		/*String pic_path = System.getProperty("catalina.home");
		log.debug("磁盘位置1:"+pic_path+"\\webapps\\userFacePic");*/
		  if (session.getAttribute("userid")!=null)
		  {
			  log.debug("userid:"+session.getAttribute("userid")+"已经登录了");
			  return "/manager_comm/main";
		  }
		  else
		  {
			  log.debug("需要去登录");
			  return "/manager_comm/login";
		  }
		
	
	}

	

	// 将对象 直接打印成字符串

	//处理登录事件
	@RequestMapping("/doLogin")
	@ResponseBody
	public AjaxMsg  doLogin(TUser user,String  kaptcha,HttpSession session,HttpServletRequest request) throws Exception
	{
 
		AjaxMsg msg=new AjaxMsg();
		loginService.doLogin(user, kaptcha,  msg, session,request);
		 return  msg;
 
	}
	
	
	
	//进入首页
	@RequestMapping("/main")
	public String  main(Model model,HttpSession session ) throws Exception
	{
		 int userid=0;
		 String username="";
		 if (session.getAttribute("userid")!=null)
		 {
			 userid=(int)session.getAttribute("userid");
			 
		 }
		 if (session.getAttribute("username")!=null)
		 {
			 username=(String)session.getAttribute("username");
			 
		 }
		 
	 
		   
		   TUser user=userService.findUserById(userid);
		   model.addAttribute("user", user);
		
		 List<MenuVo>  rightsList=loginService.getRightsMap(userid);
		
         model.addAttribute("rightsList", rightsList);
         
         
        String urlString= (String)session.getAttribute("nowUrl");
         
           //刷新首页的时候 ，子页面路径保留
            model.addAttribute("nowUrl",urlString);
 
		 
		 return "/manager_comm/main";
	
	}
	
	 //进入首页后 显示的默认页
	@RequestMapping("/defaultPage")
	public String  defaultPage(Model model,HttpSession session) throws ServicesException
	{
		PageVo pageVo=new PageVo();
		 //查询当前到期的 数据
		pageVo.setCurrPage(1);
		pageVo.setPageSize(10000);
		
	     	Date currentTime = new Date();
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = df.format(currentTime);
		  
		    Map<String,Object> conditions=new HashMap<String, Object>();
             conditions.put("beginHT", dateString);
		     conditions.put("endHT", dateString);
		      //0 未付款
		     conditions.put("flag", "0");
		     pageVo.setConditions(conditions);
		     PageBean<ScheduleVo>  pages=scheduleService.getScheduleVoPages(pageVo,0);
		  model.addAttribute("pages", pages);
		  String loginname=(String)session.getAttribute("loginname");
		   model.addAttribute("loginname", loginname);
 
		return "/manager_comm/main_default";
		
	}
	
	
	 // 退出
	@RequestMapping("/loginout")
	public String  loginOut(HttpSession session )
	{
	 
          session.removeAttribute("username");
          session.removeAttribute("userid");
          session.removeAttribute("cart");
		
           //这里的路径 是 相对于当前路径 /manager
     	 return "redirect:login.html";
	}

}
