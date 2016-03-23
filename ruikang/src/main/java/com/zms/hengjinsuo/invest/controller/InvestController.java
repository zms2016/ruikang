package com.zms.hengjinsuo.invest.controller;

import java.text.ParseException;
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
import com.zms.exception.ControllerException;
import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TReceive;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.rongzi.controller.RongziController;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.BankVo;
import com.zms.hengjinsuo.vo.InvestVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ReceiveVo;
import com.zms.hengjinsuo.vo.ScheduleVo;

/**
 * 
 * 功能说明：融资客户 银行账户 管理
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月3日/上午9:32:13
 */

@Controller
@RequestMapping("/manager")
public class InvestController {
	
	private static Logger log = Logger.getLogger(InvestController.class);
	
	
	@Autowired
	private InvestService  investService;
	
	@Autowired
	private RongziService rzService;
	
	
	
 
    
	
	
	//跳转到 新增融资项目界面，  客户名称 取出来 放到jsp,银行账户 需要在选择客户名称后 才会显示出来
 @RequestMapping("/addInvestInit")
 public String addRzBankInit(Model model)
 {
	 
	 List<TRongzi> rzs=new ArrayList<TRongzi>();
	 
	 rzs=rzService.getRongzis(true);
	 
	 model.addAttribute("rzs", rzs);
	 
 
	 
	 return   "manager_invest/addInvest";
 }
 
 /**
  * 
  *@throws ControllerException 
 * @throws ServicesException 
 * @throws ParseException 
 * @throws NumberFormatException 
 * @返回值:AjaxMsg
  *@时间: 2016年3月19日下午1:55:23
  *@作者:张木生 330140511@qq.com
  *@说明: 增加融资项目
  *@参数:@param invest
  *@参数:@param model
  *@参数:@return
  */
 @RequestMapping("/addInvestAction")
 @ResponseBody
 public AjaxMsg addInvestAction(TInvests invest,Model model,HttpSession session) throws  ServicesException, NumberFormatException, ParseException
 {
	 
	  AjaxMsg msg=new AjaxMsg();
	  
	  investService.addInvest(invest,msg,(String)session.getAttribute("username"));
 
	 return  msg;
 }
 
 
 
 
 
 
 
 @RequestMapping("/deleteInvestById")
 @ResponseBody
 public AjaxMsg deleteInvestById(Integer id)
 {
	 
	  AjaxMsg msg=new AjaxMsg();
	  
	  investService.deleteInvestById(id,msg);
 
	 return  msg;
 }
 
 
 
 //通过选择用户 过滤银行账户
 @RequestMapping("/getBankByUserId")
 @ResponseBody
 public List<TRzbank> getBankByUserId(Integer userId)
 {
 
	 return   investService.getBankByUserId(userId);
 }
 
 
 
 /**
  * 
  *@返回值:String
  *@时间: 2016年3月19日下午1:57:41
  *@作者:张木生 330140511@qq.com
  *@说明:融资项目列表
  *@参数:@param pageVo
  *@参数:@param model
  *@参数:@return
  */
 @RequestMapping("/getInvestList")
 public String getInvestList(PageVo pageVo,Model model)
 {
	 
	 PageBean<InvestVo>  pages=investService.getInvestListPages(pageVo);
	  model.addAttribute("pages", pages);
	 
 
	 
	 return   "manager_invest/getInvestList";
 }
 
 
//跳转到 新增融资项目界面，  客户名称 取出来 放到jsp,银行账户 需要在选择客户名称后 才会显示出来
@RequestMapping("/updateInvestInit")
public String updateInvestInit(Model model,Integer id)
{
	 
	 List<TRongzi> rzs=new ArrayList<TRongzi>();
	 rzs=rzService.getRongzis(true);
	 model.addAttribute("rzs", rzs);
	 InvestVo  investVo=new InvestVo();
	 investVo=investService.getInvestVoById(id);
	 model.addAttribute("investVo", investVo);
	 /*Gson gson=new Gson();
	 System.out.println("返回的;"+gson.toJson(investVo));*/
	 
	 return   "manager_invest/updateInvest";
}


@RequestMapping("/updateInvestAction")
@ResponseBody
public AjaxMsg updateInvestAction(TInvests invest)
{
	  AjaxMsg msg=new AjaxMsg();
	  investService.updateInvestAction(invest,msg);
	 return  msg;
}
 	
 /**
  * 
  *@返回值:String
  *@时间: 2016年3月19日下午4:10:18
  *@作者:张木生 330140511@qq.com
  *@说明:
  *@参数:@param pageVo
  *@参数:@param model
  *@参数:@return
  */
 @RequestMapping("/getReceiveList")
 public String getReceiveList(PageVo pageVo,Model model)
 {
	 PageBean<ReceiveVo>  pages=investService.getReceiveListPages(pageVo);
	  model.addAttribute("pages", pages);
	 /*  Gson gson=new Gson();
	   System.out.println(gson.toJson(pages));
	 */
	 return   "manager_invest/getReceiveList";
 }
 
 // 进入登记收款界面
 @RequestMapping("/updateReceiveInit")
 public String updateReceiveInit(int id,Model model)
 {
 
	 TReceive vo=investService.getReceiveById(id);
 
	 model.addAttribute("vo", vo);
	 
	
	 
	 return   "manager_invest/updateReceive";
 }
 
 
 //登记收款
 @RequestMapping("/updateReceiveAction")
 @ResponseBody
 public AjaxMsg updateReceiveAction(TReceive receive,HttpSession session)
 {
 	  AjaxMsg msg=new AjaxMsg();
 	  investService.updateReceiveAction(receive,msg,(String)session.getAttribute("username"));
 	/*  Gson gson=new Gson();
 	 System.out.println("sm::::"+gson.toJson(msg)); */
 	  
 	 return  msg;
 }
	

 
 
}
