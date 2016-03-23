package com.zms.hengjinsuo.bill.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TBill;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bill.services.BillService;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.BillInfoVo;
import com.zms.hengjinsuo.vo.BillVo;
import com.zms.hengjinsuo.vo.ContractBillVo;
import com.zms.hengjinsuo.vo.ExcelBillVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.util.ExcelUtil;

 
/**
 * 
 * 功能说明：合同
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月4日/下午4:42:54
 */

@Controller
@RequestMapping("/manager")
public class BillController {
	
	private static Logger log = Logger.getLogger(BillController.class);
	
	
	@Autowired
	private BillService billService;
 
	@Autowired 
	private  InvestService investService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VipService vipService;
	
	//跳转到录入单据页面 ，带参数id=1的是 续签 不带参数是新增
	  @RequestMapping("/addBillInit")
	 public String addBillInit(Model model,Integer billFlag)
	 {
		 
		 List<TContract> contracts=new ArrayList<TContract>();
 
			  if (billFlag==1)
				 {
				  //获取状态为  已还款结束的 4
					 contracts=contractService.getContractList(4);
					 model.addAttribute("contracts", contracts);
					 model.addAttribute("billFlag", billFlag);
				 }
		 
		 
		 else {
			 //获取状态为 已 领取的 合同编号
			 
			 contracts=contractService.getContractList(2);
			 
			 model.addAttribute("contracts", contracts);
			 model.addAttribute("billFlag", 0);
		}
 
			  
				 List<TInvests> invests=new ArrayList<TInvests>();
				 //获取未满标的
				 invests=investService.getInvests(1);
				 model.addAttribute("invests", invests);
 
		 return   "manager_bill/addBill";
	 
	 }
	  
	  
	  
		 @RequestMapping("/addBillAction")
		 @ResponseBody
		public AjaxMsg  addBillAction(TBill bill,Integer billFlag,HttpServletRequest request) throws Exception
		{
		 
	            AjaxMsg msg=new AjaxMsg();
	     
	            billService.addBillAction(bill,billFlag,msg,request);
	            
	            return msg;
			 
		}
		 
		 
		  // 财务录入单据的时候 根据选择 合同id  自动获取 合同对应 融资客户
		 @RequestMapping("/getDateByContractId")
		 @ResponseBody
		public ContractBillVo  getDateByContractId(Integer contractid) 
		{
		 
			       ContractBillVo vo=new ContractBillVo();
	     
	              vo=billService.getDateByContractId(contractid);
	            
	           
	              
	            return vo;
			 
		}
		 
		 
		 //统计项目当前 融资了多少钱
		@RequestMapping("/getSumOfInvest")
		@ResponseBody
		public AjaxMsg getSumOfInvest(Integer investid)
		{
			 
			  AjaxMsg msg=new AjaxMsg();
			  
			  billService.getSumOfInvest(investid,msg);
			  
			   Gson gson=new Gson();
	              System.out.println("合同信息:"+gson.toJson(msg));  

			 return  msg;
		}
		 
		 
		 
		// 财务单据列表 分页
		@RequestMapping("/getBillList")
		public String getBillList(Model model,PageVo pageVo) throws ServicesException
		{
			 
			
			  PageBean<BillVo>  pages=billService.getBillVoPages(pageVo);

	       
			  model.addAttribute("pages", pages);
			  
			  
			   //理财经理
/*		         List<TUser> managers=new ArrayList<TUser>();
		         managers=userService.getUsers();
				 model.addAttribute("managers", managers);*/
			  
			    //理财客户
			  List<TVip> vips=new ArrayList<TVip>();
			  vips=vipService.getVips(0);
				 model.addAttribute("vips", vips); 
				 
				 
				 //理财项目
			    List<TInvests> invests=new ArrayList<TInvests>();
			    invests=investService.getInvests(0);
				 model.addAttribute("invests", invests);
			    
				 
				 
			 
		/*		 Gson gson=new Gson();
				  System.out.println("返回的数据:"+gson.toJson(pages)); */
			 
		     
		    
			return "manager_bill/getBillList";
		}
		
		
		@RequestMapping("/deleteBillById")
		@ResponseBody
		public AjaxMsg deleteBillById(Integer id,HttpServletRequest request)
		{
			
			AjaxMsg msg=new AjaxMsg();
		     
			 billService.deleteBillById(id,msg,request);
			
			return msg;
		}
		
		
		 //显示详情
		@RequestMapping("/getBillInfo")
		public String getBillInfo(Integer id,Model model) throws ServicesException
		{
	         BillInfoVo vo=new BillInfoVo();
	         
	         vo=billService.getBillInfoVo(id);
	         
	         model.addAttribute("vo", vo);
	         
	   /*      Gson gson=new Gson();
	         System.out.println(gson.toJson(vo));*/
			
			return "manager_bill/getBillInfo";
		}
		
		
		
		 // 导出excel  实际上是以查ｂｉｌｌ表为主， 因为有续签情况
	 	@RequestMapping("/exportBillList")
		public String  exportBillList(PageVo pageVo,HttpServletRequest request,HttpServletResponse response    ) throws  Exception
		{
		 
			String fileName="合同列表 ";
	        //填充projects数据
			
		    List<ExcelBillVo> vos=new ArrayList<ExcelBillVo>();
		    
		     //根据查询条件获取数据
		     vos=billService.getBillDate(pageVo);
		 
	        List<Map<String,Object>> list=billService.createExcelBillVo(vos);
	      //列名
	        String columnNames[]={"单据编号","合同编号","合同金额","合同时间","合同期限","还款类型","融资客户","融资项目","理财经理","理财客户","当前状态"};
	        //map中的key
	        String keys[]    =     {"billId","contractString","billMoney", "billTime","billLongTime","backType","rongzi","invest","managerName","vipName","flag"};
	        
	  
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	           
	        	 ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
	        	 
	        	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	        return null;
			 
		} 
		 
		
 
}
