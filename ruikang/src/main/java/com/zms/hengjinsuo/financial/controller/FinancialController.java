package com.zms.hengjinsuo.financial.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TFinanical;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.financial.services.FinanicalService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.SqlParams;


/**
 * 
 * 功能说明： 融资项目
 * 创建人：330140511@qq.com  
 * 创建时间：2015年10月24日/上午9:04:33
 */
@Controller
@RequestMapping("/manager")
public class FinancialController {
	
	private static Logger log = Logger.getLogger(FinancialController.class);
 
	@Autowired
	private  FinanicalService finanicalService;
	
	 //跳转到 新增页面
	@RequestMapping("/addFinancialInit")
	public String addFinancialInit()
	{
 
		
		return   "manager_financial/addFinancial";
		
		
	}
	
	 //处理新增事务
	@RequestMapping("/addFinancialAction")
	@ResponseBody
	public AjaxMsg addFinancialAction(TFinanical finanical,HttpServletRequest request)
	{
		
		AjaxMsg msg=new AjaxMsg();
		
		 int userid=(int)request.getSession().getAttribute("userid");
		 
		 finanical.setUserid(userid);
		 
		finanicalService.addFinancialAction(finanical,msg);
		return   msg;
		
		
	}
	
	
	
	 //跳转到修改页面
	@RequestMapping("/updateFinancialInit")
	public String updateFinancialInit(Integer id,Model model)
	{
 
        model.addAttribute("financial", finanicalService.getFinancialById(id));
		
		return   "manager_financial/updateFinancial";
 
	}
	
	 //响应修改事件
	@RequestMapping("/updateFinancialAction")
	@ResponseBody
	public AjaxMsg updateFinancialAction(TFinanical finanical)
	{
        AjaxMsg msg=new AjaxMsg();
        finanicalService.updateFinancial(finanical,msg);
		return   msg;

	}
	
	
	
	
	
	 // 获取尸体对象
	@RequestMapping("/getFinancialById")
	@ResponseBody
	public TFinanical getFinancialById(Integer id)
	{
        
         
		return   finanicalService.getFinancialById(id);

	}
	
	
	
	
	
	 //处理删除事务
	@RequestMapping("/deleteFinancialById")
	@ResponseBody
	public AjaxMsg deleteFinancialById(int id)
	{
		
		AjaxMsg msg=new AjaxMsg();
		
	 
		 
		finanicalService.deleteFinancialById(id,msg);
		return   msg;
		
		
	}
	
	
	//查询列表 支持分页
	@RequestMapping("/getFinancialList")
	public String getFinancialList (SqlParams sqlParams,Model model) throws Exception
	{
		
		   log.debug("当前页:"+sqlParams.getCurrPage()+"每页数量:"+sqlParams.getPageSize()+"开始编号:"+sqlParams.getFromNum() +"查询条件:"+sqlParams.getParams());
		   
			//  List<TRoles> roles=roleService.getRoles( sqlParams);
			  
			   PageBean<TFinanical>  pages=finanicalService.getFinanicalPage(sqlParams);
			
			     //测试 打印 pageBean对象
			   Gson gson=new Gson();
			   log.debug("分页对象:"+gson.toJson(pages));
			   
			   
			  model.addAttribute("pages", pages);
			
			 
		
		return   "manager_financial/getFinancialList";
		
		
	}
    
	 

}
