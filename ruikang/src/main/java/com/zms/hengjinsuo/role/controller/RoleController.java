package com.zms.hengjinsuo.role.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.role.services.RoleService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.RoleHasUserVo;
import com.zms.hengjinsuo.vo.SqlParams;

@Controller
@RequestMapping("/manager")
public class RoleController {
 
	private static Logger log = Logger.getLogger(RoleController.class);

	
 @Autowired
 private RoleService  roleService;
 
 
 //获取 角色列表 支持分页
	@RequestMapping("/getRoleList")
	public String  getRoleList(PageVo pageVo,Model model) throws Exception
	{
 
		  
		   
		//  List<TRoles> roles=roleService.getRoles( sqlParams);
		  
		   PageBean<TRoles>  pages=roleService.getRolesPage(pageVo);
		
		     //测试 打印 pageBean对象
	    Gson gson=new Gson();
		   log.debug("分页对象:"+gson.toJson(pages)); 
		   
		   
		  model.addAttribute("pages", pages);
		
		return "manager_role/getRoleList";
		
	 
	}
	
	
	//处理 点 编辑按钮 事件  弹出 边界界面，界面和 新增一样，不过 编辑框是需要填充 checkbox的
	@RequestMapping("/updateRoleInit")
	public String  editroleinit(Integer id,Model model ) throws Exception
	{
		 
		   model.addAttribute("role", roleService.getRoleById(id));
		   
		   model.addAttribute("types", roleService.getRightTypes(-1));
		 
		return "manager_role/updateRole";
		
	}
	
	
	//修改角色
			@RequestMapping("/updateRoleAction")
			@ResponseBody
			public AjaxMsg  updateRoleAction(TRoles role ) throws Exception
			{
				
				 log.debug(" 页面" +role.getName()+role.getDescription()+role.getRightids());
				  
				 AjaxMsg msg=new AjaxMsg();
				 
				 roleService.updateRole(role, msg);
				 
				 return msg;
		 
			}
			
			
	
	//处理 点击 增加角色 按钮后的事件，从数据库中读取所有大栏目表，然后把 增加页面返回回去。
	@RequestMapping("/addRoleInit")
	public String  addRoleInit(Model model ) throws Exception
	{
		 
		model.addAttribute("types", roleService.getRightTypes(-1));
		 
		return "manager_role/addRole";
		
	}
	
   //保存角色
	@RequestMapping("/addRoleAction")
	@ResponseBody
	public AjaxMsg  addRoleAction(TRoles role ) throws Exception
	{
		
		 
		
		 log.debug(" 需要增加的参数，名字:" +role.getName()+"角色："+role.getDescription()+"ids:"+role.getRightids());
		  
		 AjaxMsg msg=new AjaxMsg();
		 
		 roleService.insertRoles(role, msg);
		 
		 return msg;
 
	}
	
	
	   
		
		
	
	   //删除角色
		@RequestMapping("/deleteRoleById")
		@ResponseBody
		public AjaxMsg  deleteRoleById(int  id ) throws Exception
		{
	 
			 AjaxMsg msg=new AjaxMsg();
			 
			 roleService.deleteRoleById(id, msg);
			 
			   
			 return msg;
	 
		}
		
		
		
		
		
		//处理 点设置权限按钮 事件  
		@RequestMapping("/setRoleRightsInit")
		public String  setRoleRightsInit(Integer roleid,Model model ) throws Exception
		{
			
			
			  //获取角色名称
			   model.addAttribute("role", roleService.getRoleById(roleid));
			   
			   //显示 角色下面的 所有2级 和 2级下面的所有三级模块
			   model.addAttribute("rightMap",roleService.getRightsMap(roleid));
			   
			   //获取 角色对应的所有权限，用在把页面上的checkbox打勾
			   model.addAttribute("rightIds", roleService.getRightsByRoleId(roleid));
			 
			return "manager_role/setRoleRights";
			
		}
		
	    //处理 权限设置事件
		@RequestMapping("/setRoleRightsAction")
		@ResponseBody
		public  AjaxMsg  setRoleRightsAction(Integer roleid, String ids,Model model ) throws Exception
		{
			
			 AjaxMsg msg=new AjaxMsg();
            roleService.setRoleWithIds(roleid, ids,msg);
            
            return msg;
			
		}
		
		
		 //根据 RoleId 获取用户列表 ，用来 管理 绑定到该角色的用户   
		@RequestMapping("/getUserListByRoleId")
		public String getUserListByRoleId(  PageVo pageVo,Model model,Integer roleId) throws Exception

		{
 
			  TRoles role=new TRoles();
			  
			  role=roleService.getRoleById(roleId);
			
				 PageBean<RoleHasUserVo>  pages=roleService.getUsersPageByRoleId(pageVo,roleId);
	 
				  Gson gson=new Gson();
				 
			//	System.out.println(gson.toJson(pages)); 
				 
				 model.addAttribute("roleId", roleId);
				 
				 model.addAttribute("role", role);
				 
				 
			  	  model.addAttribute("pages", pages);
				
	           
			
			
			return  "manager_role/getUserListByRoleId" ;
		}
		
		 
		  //解除 用户 和 角色 关系
		@RequestMapping("/unBindRole")
		@ResponseBody
		public  AjaxMsg unBindRole(Integer userId,Integer roleId)
		{
			AjaxMsg msg=new AjaxMsg();
			roleService.unBindRole(  userId,  roleId,msg);
			
			return msg;
	 
		}
		
		
		  //绑定 用户 和 角色 关系
		@RequestMapping("/bindRole")
		@ResponseBody
		public  AjaxMsg bindRole(Integer userId,Integer roleId)
		{
			AjaxMsg msg=new AjaxMsg();
			roleService.bindRole(  userId,  roleId,msg);
			return msg;
	 
		}
		
		
		 
		
}
