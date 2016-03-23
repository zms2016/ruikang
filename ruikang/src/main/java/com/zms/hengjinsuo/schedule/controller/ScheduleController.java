package com.zms.hengjinsuo.schedule.controller;

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
import com.zms.hengjinsuo.bean.TSchedule;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.schedule.services.ScheduleService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.ScheduleVo;
import com.zms.util.ExcelUtil;

 
  //还款计划表
@Controller
@RequestMapping("/manager")
public class ScheduleController {
	
	private static Logger log = Logger.getLogger(ScheduleController.class);
	
	@Autowired
	private ScheduleService scheduleService;
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
	
	
	
	//   分页
	@RequestMapping("/getScheduleList")
	public String getScheduleList(Model model,PageVo pageVo) throws ServicesException
	{
		 
		  PageBean<ScheduleVo>  pages=scheduleService.getScheduleVoPages(pageVo,0);
		  model.addAttribute("pages", pages);
		  
		  
		   //理财经理
	         List<TUser> managers=new ArrayList<TUser>();
	         managers=userService.getUsers();
			 model.addAttribute("managers", managers);
			 
		 
			   //理财客户
	         List<TVip> vips=new ArrayList<TVip>();
	         vips=vipService.getVips(0);
			 model.addAttribute("vips", vips);

			/* Gson gson=new Gson();
			 System.out.println(gson.toJson(pages));*/
	    
		return "manager_schedule/getScheduleList";
	}
	
	
	
	
	

	 // 编辑页面
	 @RequestMapping("/updateScheduleInit")
	 public String updateScheduleInit(Integer id,Model model)
	 {
		 
		 //TSchedule schedule=scheduleService.getScheduleById(id);
				 
		 ScheduleVo vo=scheduleService.getScheduleVoById(id);
       
		 model.addAttribute("vo", vo);
		 
		/* Gson gson=new Gson();
		 System.out.println("sm::::"+gson.toJson(vo));*/
		 
		 return   "manager_schedule/updateSchedule";
	 }
	 
	
	
	 // 修改
		@RequestMapping("/updateScheduleAction")
		@ResponseBody
		public AjaxMsg updateScheduleAction(TSchedule schedule  ,HttpServletRequest request )
		{
			 
	            AjaxMsg msg=new AjaxMsg();
	            
	          
	            scheduleService.updateScheduleAction(schedule,msg,request);

			 return  msg;
			 
		} 
		
		
 
		 // 导出excel
			@RequestMapping("/exportScheduleList")
			public String  exportScheduleList(PageVo pageVo,HttpServletRequest request,HttpServletResponse response    ) throws  Exception
			{
			 
				String fileName="还款计划表 ";
		        //填充projects数据
				
			    List<ExcelScheduleVo> vos=new ArrayList<ExcelScheduleVo>();
			    
			     //根据查询条件获取数据
			     vos=scheduleService.getfinanceDate(pageVo);
			    
		    //    List<Project> projects= createData();
		        
		        List<Map<String,Object>> list=scheduleService.createExcelRecord(vos);
		      //列名
		        String columnNames[]={"合同编号","合同金额","合同时间","合同期限","融资客户","融资项目","还款银行","账号","理财经理","理财客户","收款银行","账号","还款类型","还款时间","还款金额","当前状态"};
		        //map中的key
		        String keys[]    =     {"contractString","billMoney", "billTime","billLongTime","rongzi","investName","rzBankName","rzBankNum","managerName","vipName","vipBankName","vipBankNum","backType","backTime","backMoney","flag"};
		        
		  
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
