package com.zms.hengjinsuo.contract.controller;

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
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ContractVo;
import com.zms.hengjinsuo.vo.ExcelContractVo;
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
public class ContractController {
	
	private static Logger log = Logger.getLogger(ContractController.class);
	
	
	@Autowired
	private RzBankService rzBankService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private RongziService rongziService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private  InvestService investService;
	
	@Autowired
	private  VipService vipService;
	
	
	//跳转到批量创建合同界面
  @RequestMapping("/createContractInit")
 public String addContractInit(Model model)
 {
	 
  List<TRongzi> rongzis=new ArrayList<TRongzi>();
	 
  rongzis=rongziService.getRongzis(true);
	 
	 model.addAttribute("rongzis", rongzis); 
	  
/*		 List<TInvests> invests=new ArrayList<TInvests>();
		 //获取未满标的
		 invests=investService.getInvests(1);
		 model.addAttribute("invests", invests);*/
	 
	 return   "manager_contract/createContract";
 
 }
 
    /**
     * 
     * 功能说明： 批量创建合同
     * 创建人：张木生 330140511@qq.com   
     * 创建时间：2015年11月5日/上午9:46:52 
     * @param preFix  合同前缀
     * @param startNum 起始编号
     * @param count   印发数量
     * @param investNum  融资项目编号
     * @return
     * @throws Exception
     */
	 @RequestMapping("/createContractAction")
	 @ResponseBody
	public AjaxMsg  createContractAction(String preFix,Integer startNum,Integer count,Integer rzId) throws Exception
	{
	 
            AjaxMsg msg=new AjaxMsg();
     
            contractService.addContractAction(preFix,startNum,count,rzId,msg);
            
            
            
            return msg;
		 
	}
	 
 
	 
	 
	// 合同 列表 分页
			@RequestMapping("/getContractList")
			public String getContractList(Model model,PageVo pageVo) throws ServicesException
			{
				 
				
				  PageBean<ContractVo>  pages=contractService.getContractVoPages(pageVo);

		       
				  model.addAttribute("pages", pages);
				  
				  
				   //理财经理
			         List<TUser> managers=new ArrayList<TUser>();
			         managers=userService.getUsers();
					 model.addAttribute("managers", managers);
					 
				 
					/* Gson gson=new Gson();
					 System.out.println("返回的数据:"+gson.toJson(pages));*/
				 
			     
			    
				return "manager_contract/getContractList";
			}
			
			
		//创建合同界面 根据 融资项目id 动态查询 还款银行
			
			@RequestMapping("/getBankByInvest")
			@ResponseBody
			public List<TRzbank>  getBankByInvest(Integer id)
			{
				
				return contractService.getBankByInvest(id);
				
			}
	 
			
			 //领取合同编辑页面
			 @RequestMapping("/receiveContractInit")
			 public String receiveContractInit(Integer id,Model model)
			 {
				 
				 List<TInvests> invests=new ArrayList<TInvests>();
				 
				 //获取 合同编对应 融资客户的 所有项目
				 //invests=investService.getInvests(0);
				 invests=investService.getInvestsByContrantId(id,0);
				 model.addAttribute("invests", invests);
				 
				   //根据合同id 获取 对应 融资客户的所有 银行列表
				   List<TRzbank> banks=new ArrayList<TRzbank>();
				   banks=contractService.getBanksByContractId(id);
				   model.addAttribute("banks", banks);
				   
				
 				  
				   //理财经理
			         List<TUser> managers=new ArrayList<TUser>();
			         managers=userService.getUsers();
					 model.addAttribute("managers", managers);
					 
					   //理财客户
				        List<TVip>  vips=vipService.getVips(0);
						 model.addAttribute("vips", vips);
						 
				 
                 TContract contract=contractService.getContractById(id);
				 model.addAttribute("contract", contract);
				 
				  Gson gson=new Gson();
				   System.out.println( "获取的项目:"+gson.toJson(invests)); 
				 return   "manager_contract/receiveContract";
			 }
			 
			
			
			 // 领取合同
				@RequestMapping("/receiveContractAction")
				@ResponseBody
				public AjaxMsg receiveContractAction(TContract contract  ,HttpServletRequest request  )
				{
					 
			            AjaxMsg msg=new AjaxMsg();
			          
			            contractService.updateContractAction(contract,msg,request);

					 return  msg;
					 
				} 
				
				
			 //删除
			@RequestMapping("/deleteContractById")
			@ResponseBody
			public AjaxMsg deleteContractById(Integer id,HttpServletRequest request) throws Exception
			{
				 
				 
		       AjaxMsg msg=new AjaxMsg();
		       
		       contractService.deleteContractById(id,msg,request);

		       return msg;
			}
			
			
			
			 // 导出excel  实际上是以查ｂｉｌｌ表为主， 因为有续签情况
		 	@RequestMapping("/exportContractList")
			public String  exportContractList(PageVo pageVo,HttpServletRequest request,HttpServletResponse response    ) throws  Exception
			{
			 
				String fileName="合同列表 ";
		        //填充projects数据
				
			    List<ExcelContractVo> vos=new ArrayList<ExcelContractVo>();
			    
			     //根据查询条件获取数据
			     vos=contractService.getContractDate(pageVo);
			 
		        List<Map<String,Object>> list=contractService.createExcelRecord(vos);
		      //列名
		        String columnNames[]={"单据编号","合同编号","合同金额","合同时间","合同期限","融资客户","融资项目","理财经理","理财客户","当前状态"};
		        //map中的key
		        String keys[]    =     {"billId","contractString","billMoney", "billTime","billLongTime","rongzi","invest","managerName","vipName","flag"};
		        
		  
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
