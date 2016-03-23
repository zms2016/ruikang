package com.zms.hengjinsuo.rightaction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TRightActions;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.rightaction.services.RightActionService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.RightActionVo;
import com.zms.hengjinsuo.vo.SqlParams;

@Controller
@RequestMapping("/manager")
public class RightActionController {
 
	
private static Logger log = Logger.getLogger(RightActionController.class);

 @Resource
 private RightActionService rightActionService;
 
 
 // 显示 权限路径 管理界面
	@RequestMapping("/getRightActionList")
	public String  getRoleList(SqlParams sqlParams,Model model) throws Exception
	{
		
		log.debug("获取到的分页请求,currPage:"+sqlParams.getCurrPage()+"pageSize:"+sqlParams.getPageSize()+"title:"+sqlParams.getFiletitle()+"typeId:"+sqlParams.getTypeid());
		
		
		
		 //给前台返回 所有一级菜单
          model.addAttribute("typeList",rightActionService.getFirstMenus() );
          
      	/*为了保留 搜索后  下拉框的值保持，所以需要把二级目录也一起读回去*/
  		if (sqlParams.getParentId() !=null)
  		{
  			 if (sqlParams.getParentId()>0)
  			 {
  					List<TRights> childList=rightActionService.getSecMenus(sqlParams.getParentId());
  					model.addAttribute("childList", childList);
  			 }
  		}
  		
  		
           //获取列表 支持分页
  		/* 返回分页数据*/
		 PageBean<RightActionVo>  pages=rightActionService.getRightActionPage(sqlParams);
	
		 model.addAttribute("pages", pages);
		
		Gson gson=new Gson();
		log.debug("分页:"+gson.toJson(pages));
          
          
		return "manager_rightaction/getRightActionList";
		
	 
	}
	
	
	// 根据 action id 获取  rightactionvo 用来 填充 “编辑 action" 页面 
		@RequestMapping("/getRightActionVoById")
		@ResponseBody
		public RightActionVo getRightActionVoById(Integer id) throws Exception
		{
			return   rightActionService.getRightActionVoById(id);
		}
		
		
		    //修改 
		@RequestMapping("/updateRightActionAction")
		@ResponseBody
		public AjaxMsg updateRightActionAction(TRightActions rightAction) throws Exception
		{
			
			log.debug("需要修改的id:"+rightAction.getId()+"新的 rightid:"+rightAction.getRightid()+"新action:"+rightAction.getAction()+"新说明:"+rightAction.getDescription());
			AjaxMsg msg=new AjaxMsg();
			
			rightActionService.updateRightActionAction(rightAction, msg);
			
			return  msg;
		}
		
		
		
		
	
	   //根据 一级菜单 联动二级菜单
	
	@RequestMapping("/getSecMenuByFartherId")
	@ResponseBody
	public   List<TRights>  getSecMenuByFartherId(int parentId) throws Exception
	{
 
		return   rightActionService.getSecMenus(parentId);
		
	 
	}
	
	
	// 处理增加RightAction 的 action
	@RequestMapping("/addRightActionAction")
	@ResponseBody
	public  TRightActions  addRightActionAction( int rightId,String action,String description  ) throws Exception
	{
 
            return rightActionService.addRightAction(rightId,action,description);
             
	
	}
	
	
	
	
	
	//删除
	@RequestMapping("/deleteById")
	@ResponseBody
	public AjaxMsg  deleteById(Integer id) throws Exception
	{
	 
		
           
            AjaxMsg msg=new AjaxMsg();
            
            rightActionService.deleteById(id,msg);
            
            return msg;
             
	
	}
	
	
	
	
	
		
}
