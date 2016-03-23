package com.zms.hengjinsuo.rongzi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;

/**
 * 
 * 功能说明：融资客户  管理
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月3日/上午9:32:13
 */

@Controller
@RequestMapping("/manager")
public class RongziController {
	
	
	
	private static Logger log = Logger.getLogger(RongziController.class);

	
	@Autowired
	private RongziService rongziService;;
	
	
 @RequestMapping("/addRongziInit")
 public String addRongziInit()
 {
	 
	 
	 return   "manager_rongzi/addRongzi";
 }
	
 
 //处理增加融资客户 
	 @RequestMapping("/addRongziAction")
	 @ResponseBody
	public AjaxMsg  addRongziAction(TRongzi rz) throws Exception
	
	{
	 
            AjaxMsg msg=new AjaxMsg();
     
            rongziService.addRongzi(rz,msg);
            
            return msg;
		 
	}
	 
	
	 
	 // 融资客户 列表 分页
		@RequestMapping("/getRongziList")
		public String getRongziList(Model model,PageVo pageVo)
		{
			 
			
			  PageBean<TRongzi>  pages=rongziService.getRongziPages(pageVo);

	       
			  model.addAttribute("pages", pages);
			 
			   Gson gson=new Gson();
			   
			  log.debug(gson.toJson(pages));
		     
		    
			return "manager_rongzi/getRongziList";
		}
		
		
		
		 //跳转到 编辑页面
		 @RequestMapping("/updateRongziInit")
		 public String updateRongziInit(Integer id,Model model)
		 {
			 
			 TRongzi rongzi=rongziService.getRongziById(id);
			 
			 model.addAttribute("rongzi", rongzi);
			 
			 return   "manager_rongzi/updateRongzi";
		 }
		 
		 
		 
		 // 修改
			@RequestMapping("/updateRongziAction")
			@ResponseBody
			public AjaxMsg updateRongziAction(TRongzi  rongzi    )
			{
				 
		            AjaxMsg msg=new AjaxMsg();
		          
		          rongziService.updateRongziAction(rongzi,msg);

				 return  msg;
				 
			}
			
			
		 
		 
		
		@RequestMapping("/deleteRongziById")
		@ResponseBody
		public AjaxMsg deleteRongziById(Integer id) throws Exception
		{
			 
			 
	       AjaxMsg msg=new AjaxMsg();
	       
	       rongziService.deleteRongziById(id,msg);

	       return msg;
		}
		
		

}
