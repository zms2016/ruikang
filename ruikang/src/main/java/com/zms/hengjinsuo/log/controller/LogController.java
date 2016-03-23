package com.zms.hengjinsuo.log.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.LogVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;

@Controller
@RequestMapping("/manager")
public class LogController {
	
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * 功能说明： 操作日志 分页查询   查询条件 操作时间  操作员  内容（模糊)
	 * 创建人：张木生 330140511@qq.com   
	 * 创建时间：2015年11月25日/上午11:20:58 
	 * @param pageVo
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getLogList")
	  
	public String  getLogList(PageVo pageVo,Model model,HttpSession session) throws Exception
	{
 
		 
		 List<TUser> users=new ArrayList<TUser>();
          users=userService.getAllUsers();
         
           model.addAttribute("users", users);
         
           
           PageBean<LogVo>  pages=logService.getLogPages(pageVo);
           
           model.addAttribute("pages", pages);
      
		    Gson gson=new Gson();
	 
		 
		 // System.out.println("数据:"+gson.toJson(pages));
		     

		return  "manager_comm/getLogList";
		 
	}
	 
	

}
