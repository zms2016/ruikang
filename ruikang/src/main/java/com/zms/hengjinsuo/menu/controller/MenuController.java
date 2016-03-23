package com.zms.hengjinsuo.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TContentTypes;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.menu.services.MenuService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.RightVo;

/**
 * 
 * 功能说明： 配置菜单
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年10月31日/上午9:22:49
 */

@Controller
@RequestMapping("/manager")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	private static Logger log = Logger.getLogger(MenuController.class);

	
	
	 //跳转到现在一级菜单界面
	@RequestMapping("/addSuperMenuInit")
	public String addSuperMenuInit( )
	{
 
		return "manager_menu/addSuperMenu";
	}
	
	 //处理增加菜单事项
		@RequestMapping("/addSuperMenuAction")
		@ResponseBody
		public AjaxMsg addSuperMenuAction(TRightTypes rightTypes)
		{
			 
	             AjaxMsg msg=new AjaxMsg();
	           
	           menuService.addSuperMenuAction(rightTypes,msg);
	 
			 return  msg;
			 
		}
		
		
		
		 //跳转到更新 页面
		@RequestMapping("/updateSuperMenuInit")
		public String updateSuperMenuInit(Integer id,Model model )
		{
			
			if (id!=null)
			{
				TRightTypes rightType=new TRightTypes();
				rightType=menuService.getSuperMenuById(id);
				if (rightType!=null)
				{
					model.addAttribute("menu", rightType);
					
					return "manager_menu/updateSuperMenu";
				}
			}
		
			
			   return "redirect:getSuperMenuList.html";
	   
		
		}
		
		 //处理更新菜单事项
			@RequestMapping("/updateSuperMenuAction")
			@ResponseBody
			public AjaxMsg updateSuperMenuAction(TRightTypes rightTypes)
			{
				 
		             AjaxMsg msg=new AjaxMsg();
		           
		           menuService.updateSuperMenuAction(rightTypes,msg);
		 
				 return  msg;
				 
			}
			
			
	
	
	
	 //一级菜单 列表 分页
	@RequestMapping("/getSuperMenuList")
	public String getSuperMenuList(Model model,PageVo pageVo)
	{
		 
		
		  PageBean<TRightTypes>  pages=menuService.getRightTypesPages(pageVo);

       
		  model.addAttribute("pages", pages);
		 
		   Gson gson=new Gson();
		   
		  log.debug(gson.toJson(pages));
	     
	    
		return "manager_menu/getSuperMenuList";
	}
	
	
	
	 //更改 一级菜单的 显示 或者隐藏标识
	@RequestMapping("/updateSuperMenuFlag")
	@ResponseBody
	public AjaxMsg updateSuperMenuFlag(Integer id,Integer flag)
	{
		 
             AjaxMsg msg=new AjaxMsg();
           
           menuService.updateSuperMenuFlag(id,flag,msg);
 
		 return  msg;
		 
	}
	
	
	 //删除 一级菜单
	@RequestMapping("/deleteSuperMenuById")
	@ResponseBody
	public AjaxMsg deleteSuperMenuById(Integer id) throws Exception
	{
		 
		 
        AjaxMsg msg=new AjaxMsg();
        
        menuService.deleteSuperMenuById(id,msg);
 
        return msg;
	}
	
	
	
	
	
	 //跳转到新增二级菜单界面
	@RequestMapping("/addSecondMenuInit")
	public String addSecondMenuInit( Model model)
	{
		
		List<TRightTypes> superMenuList=menuService.getRightTypes();
		
		  model.addAttribute("superMenuList", superMenuList);

		return "manager_menu/addSecondMenu";
	}
	
	
	 //处理增加菜单事项
		@RequestMapping("/addSecondMenuAction")
		@ResponseBody
		public AjaxMsg addSecondMenuAction(TRights rights)
		{
			 
	             AjaxMsg msg=new AjaxMsg();
	           
	             menuService.addSecondMenuAction(rights,msg);
	 
			 return  msg;
			 
		}
	
	
	
	  //二级菜单列表 支持按条件查询分页
	@RequestMapping("/getSecondMenuList")
	public String getSecondMenuList(Model model,PageVo pageVo) throws Exception
	{
		 
		List<TRightTypes> superMenuList=menuService.getRightTypes();
		
		  model.addAttribute("superMenuList", superMenuList);
		  
		  Gson gson=new Gson();
		  
		  System.out.println("前台条件："+gson.toJson(pageVo));
			/*为了保留 搜索后  二级下拉框的值保持，所以需要把二级目录也一起读回去*/
		  if (pageVo.getConditions()!=null)
		  {
				if (pageVo.getConditions().get("superMenuId")!=null & pageVo.getConditions().get("superMenuId")!="")
				{
					 //如果 一级菜单不为0 那么 把二级菜单 返回到前台
					 if ( Integer.valueOf((String)pageVo.getConditions().get("superMenuId"))>0 )
					 {
							 List<TRights> rights=new ArrayList<TRights>();
							 rights=menuService.getSecondMenuListBySuperId(Integer.valueOf((String)pageVo.getConditions().get("superMenuId")));
							 model.addAttribute("secondMenuList", rights);
					 }
				}
			  
		  }
 
		  PageBean<RightVo>  pages=menuService.getRightsPages(pageVo);
		  model.addAttribute("pages", pages);
		   Gson gson1=new Gson();
	      System.out.println(gson1.toJson(pages));
	 
		return "manager_menu/getSecondMenuList";
	}
	
	
	 //根据 一级菜单 获取二级菜单列表
	@RequestMapping("/getSecondMenuListBySuperId")
	@ResponseBody
	public List<TRights>  getSecondMenuListBySuperId(Integer superMenuId)
	{
		 
	     return  menuService.getSecondMenuListBySuperId(superMenuId);
	}
	
	

	
	 //更改 二级菜单的 显示 或者隐藏标识
	@RequestMapping("/updateSecondMenuFlag")
	@ResponseBody
	public AjaxMsg updateSecondMenuFlag(Integer id,Integer flag)
	{
		 
            AjaxMsg msg=new AjaxMsg();
          
          menuService.updateSecondMenuFlag(id,flag,msg);

		 return  msg;
		 
	}
	
	
	
	
	
	 //删除二级菜单
	@RequestMapping("/deleteSecondMenuById")
	@ResponseBody
	public AjaxMsg deleteSecondMenuById(Integer id) throws Exception
	{
		 
		 
       AjaxMsg msg=new AjaxMsg();
       
       menuService.deleteSecondMenuById(id,msg);

       return msg;
	}
	
	
	 //跳转到更新 2级菜单页面
	@RequestMapping("/updateSecondMenuInit")
	public String updateSecondMenuInit(Integer id,Model model )
	{
		
		if (id!=null)
		{
			TRights secondMenu=new TRights();
			secondMenu=menuService.getSecondMenuById(id);
			if (secondMenu!=null)
			{
				List<TRightTypes> superMenuList=menuService.getRightTypes();
				
				  model.addAttribute("superMenuList", superMenuList);

				  
				model.addAttribute("secondMenu", secondMenu);
				
			/*	Gson gson=new Gson();
				
				System.out.println(gson.toJson(secondMenu));*/
				
				return "manager_menu/updateSecondMenu";
			}
		}
	
		
		   return "redirect:getSecondMenuList.html";
   
	
	}
	
	 //处理更新2级菜单事项
		@RequestMapping("/updateSecondMenuAction")
		@ResponseBody
		public AjaxMsg updateSecondMenuAction(TRights right)
		{
			 
	             AjaxMsg msg=new AjaxMsg();
	           
	           menuService.updateSecondMenuAction(right,msg);
	 
			 return  msg;
			 
		}
		
		

		
	
	
}
