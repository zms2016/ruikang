package com.zms.hengjinsuo.rzbank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.rongzi.controller.RongziController;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.BankVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;

/**
 * 
 * 功能说明：融资客户 银行账户 管理
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月3日/上午9:32:13
 */

@Controller
@RequestMapping("/manager")
public class RzBankController {
	
	private static Logger log = Logger.getLogger(RongziController.class);
	
	
	@Autowired
	private RzBankService rzBankService;
	
	@Autowired
	private RongziService rongziService;
	
 @RequestMapping("/addRzBankInit")
 public String addRzBankInit(Model model)
 {
	 
	 List<TRongzi> rzs=new ArrayList<TRongzi>();
	 
	 rzs=rongziService.getRongzis(true);
	 
	 model.addAttribute("rzs", rzs);
	 
	 return   "manager_rzbank/addRzBank";
 }
	
 
 //处理增加融资客户银行账号
	 @RequestMapping("/addRzBankAction")
	 @ResponseBody
	public AjaxMsg  addRzBankAction(TRzbank bank,HttpSession session) throws Exception
	
	{
	 
            AjaxMsg msg=new AjaxMsg();
     
            rzBankService.addRzBankAction(bank,msg,session);
            
            return msg;
		 
	}
	 
	 /**
	  * 
	  *@返回值:AjaxMsg
	  *@时间: 2016年3月20日上午11:54:27
	  *@作者:张木生 330140511@qq.com
	  *@说明: 根据融资人员ID动态获取 银行列表
	  *@参数:@param rongziId
	  *@参数:@return
	  *@参数:@throws Exception
	  */
	 @RequestMapping("/getBanksByRongziId")
	 @ResponseBody
	public List<TRzbank>  getBanksByRongziId(int rongziId) throws Exception
	
	{
	 
		  List<TRzbank> banks=new ArrayList<>();
   
         banks=rzBankService.getBanksByRongziId(rongziId);
 
      /*   Gson gson=new Gson();
		   
		System.out.println(gson.toJson(banks));*/
		  
            return banks;
		 
	}
	 
	 
	 
	 
	// 融资客户银行账号 列表 分页
			@RequestMapping("/getRzBankList")
			public String getRzBankList(Model model,PageVo pageVo)
			{
				 
				
				  PageBean<BankVo>  pages=rzBankService.getRzBankPages(pageVo);

		       
				  model.addAttribute("pages", pages);
				 
				/*   Gson gson=new Gson();
				   
				  log.debug(gson.toJson(pages));*/
			     
			    
				return "manager_rzbank/getRzBankList";
			}
			
	 
			
			
			 //跳转到 编辑页面
			                                  
			 @RequestMapping("/updateRzBankInit")
			 public String updateRzBankiInit(Integer id,Model model)
			 {
				 
				 TRzbank bank=rzBankService.getBankById(id);
				 
				 model.addAttribute("bank", bank);
				 
				 
				 List<TRongzi> rzs=new ArrayList<TRongzi>();
				 
				 rzs=rongziService.getRongzis(true);
				 
				 model.addAttribute("rzs", rzs);
				 
				 
				 
				 
				 return   "manager_rzbank/updateRzBank";
			 }
			 
			 
			 
			 // 修改
				@RequestMapping("/updateRzBankAction")
				@ResponseBody
				public AjaxMsg updateRzBankAction(TRzbank  bank    )
				{
					 
			            AjaxMsg msg=new AjaxMsg();
			          
			          rzBankService.updateRzBankAction(bank,msg);

					 return  msg;
					 
				}
				
				
				
			
			@RequestMapping("/deleteRzBankById")
			@ResponseBody
			public AjaxMsg deleteRzBankById(Integer id) throws Exception
			{
				 
				 
		       AjaxMsg msg=new AjaxMsg();
		       
		       rzBankService.deleteRzBankById(id,msg);

		       return msg;
			}
			

}
