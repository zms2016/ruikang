package com.zms.hengjinsuo.vip.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bean.TVisit;
import com.zms.hengjinsuo.bill.services.BillService;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.schedule.services.ScheduleService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.MoneyToolInfo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ScheduleVo;
import com.zms.hengjinsuo.vo.VipVisitVo;
import com.zms.hengjinsuo.vo.VipVo;
import com.zms.util.ExcelUtil;


/**
 * 
 * 功能说明： 用来处理 理财客户 相关页面请求
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年10月30日/下午3:32:10
 */

@Controller
@RequestMapping("/manager")
public class VipController {
	
	private static Logger log = Logger.getLogger( VipController.class);
	
	@Autowired
	private VipService vipService;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private BillService billService;
	
	 //调转到增加理财客户页面
	 @RequestMapping("/addVipInit")
	public String  addVipInit(Model model,HttpSession session)
	
	{
		 
		 
		  model.addAttribute("userId", (int)session.getAttribute("userid"));
		  
		  
           List<TUser> users=new ArrayList<TUser>();
           
            users=userService.getUsers();
            
            model.addAttribute("users", users);
            
		return  "manager_vip/addVip";
		
	}
	 
	 
	  //跳转到 理财计算器
	 @RequestMapping("/moneyTool")
 
	public String  moneyTool(Model model )
	
	{
		 
		 
		 
            
		return  "manager_vip/moneyTool";
		
	}
	  //计算
	 @RequestMapping("/moneyToolAction")
	 @ResponseBody
	public MoneyToolInfo  moneyToolAction(Model model,TBill bill ) throws NumberFormatException, ParseException
	
	{
		
		 Date bDate=bill.getBegintime();
		 float money=(float)bill.getMemony(); 
		 Date fDate=bill.getFirsttime();
		 float apr=bill.getApr();
		 int  backType=bill.getBacktype();
		 int longTime=bill.getLongtime();
		 
		 System.out.println("金额:"+money+"利率:"+apr+"投资期限:"+longTime+"还款类型:"+backType+"起息日期:"+bDate+"第一次还款日期"+fDate);
 
		 MoneyToolInfo info=billService.getInfo(Double.parseDouble(String.valueOf(money)) ,backType,longTime,Double.parseDouble(String.valueOf(apr)),bDate,fDate);
		 
	/*	 Gson gson=new Gson();
		 System.out.println(gson.toJson(info));*/
	        
            
		return  info;
		
	}
	 
	 
	 
	  //处理增加理财 客户
	 @RequestMapping("/addVipAction")
	 @ResponseBody
	public AjaxMsg  addVipAction(TVip vip ) throws Exception
	
	{
		 
		 
             AjaxMsg msg=new AjaxMsg();
             
             
             
             vipService.addVipAction(vip,msg );
             
             return msg;
		 
	}
	 
	 
	 
 /**
  * 
  * 功能说明： 获取 理财客户 分页 数据
  * 创建人：张木生 330140511@qq.com   
  * 创建时间：2015年11月2日/下午1:55:14 
  * @param pageVo
  * @return
 * @throws ServicesException 
  */
	 @RequestMapping("/getVipList")
	public String  getVipList(PageVo pageVo,Model model,HttpSession session) throws ServicesException
	
	{
		 int managerId=(int)session.getAttribute("userid");
		 
		 
		 PageBean<VipVo>  pages=null;
		 
 
		
 
		   //根据 账号 获取 理财经理 列表
		 
			 TUser user2=userService.findUserById(managerId);
			 
		  if (   (user2.getDepartmentid()==6 ) || ( user2.getUsername().equals("admin")  ) )
		  {
			  pages=vipService.getVipPages(pageVo,0);
			  
		         List<TUser> managers=new ArrayList<TUser>();
		         managers=userService.getUsers();
				 model.addAttribute("managers", managers);
		  } else{
			  
			  pages=vipService.getVipPages(pageVo,managerId);
			  
			    List<TUser> managers=new ArrayList<TUser>();
		         managers.add(user2);
				 model.addAttribute("managers", managers);
			  
		  }
	   
			 
		  model.addAttribute("pages", pages);
		 
		   Gson gson=new Gson();
		   
	 
	     
		 // System.out.println("打印:"+gson.toJson(pages));
	    
	 
		 
 
		return  "manager_vip/getVipList";
		
	}
	 
	 
	 
	  //删除理财 客户
	 @RequestMapping("/deleteVipById")
	 @ResponseBody
	public AjaxMsg  deleteVipById(Integer id) throws Exception
	
	{
		 
		 
            AjaxMsg msg=new AjaxMsg();
            
            
            
            vipService.deleteVipById(id,msg);
            
            return msg;
		 
	}
	 
	 

	 
	 
	 
	  //跳转到修改界面
	 @RequestMapping("/updateVipInit")
	 public String updateVipInit(Integer id,Model model)
	 {
		 
		 List<TUser> users=new ArrayList<TUser>();
         
         users=userService.getUsers();
         
           //给前台 返回 所有 理财经理列表 （就是用户表)
         model.addAttribute("users", users);
         
         
		 
		  VipVo  vipVo=new VipVo();
		  
		  vipVo=vipService.getVipVoById(id);
		  
		  model.addAttribute("vipVo", vipVo);
		  
	/*	  Gson gson=new Gson();
		 System.out.println( gson.toJson(vipVo));*/
		 
		 return "manager_vip/updateVip";
	 }
	 
	 
	  //修改理财 客户
	 @RequestMapping("/updateVipAction")
	 @ResponseBody
	public AjaxMsg  updateVipAction(TVip vip) throws Exception
	
	{
		 
		 
          AjaxMsg msg=new AjaxMsg();
          
          
          
          vipService.updateVipAction(vip,msg);
          
          return msg;
		 
	}
	 
	 
	 
//   分页  根据 理财经理id 获取列表， 因为前台把managerid写死了 ，所以后台代码不动
	@RequestMapping("/getSchedules")
	public String getSchedules(Model model,PageVo pageVo,HttpSession session) throws ServicesException
	{
		 
	       int userId=(int)session.getAttribute("userid");
	/*        String username=(String)session.getAttribute("username");
	        model.addAttribute("username", username);*/
	       
	       PageBean<ScheduleVo>  pages=null;
	       List<TUser> managers=new ArrayList<TUser>();
	       
                     //admin  张兰兰    则不加限制  否则强制增加 条件过滤
	               if (  (userId==180) || (userId==182)  )
	               {
	            	     pages=scheduleService.getScheduleVoPages(pageVo,0);
	            	     
	            	     //理财经理
	         	        
	        	         managers=userService.getUsers();
	               }
	               else {
	            	    
	            	    managers.add(userService.findUserById(userId));
	            	    pages=scheduleService.getScheduleVoPages(pageVo,userId);
				}
	      
		         model.addAttribute("pages", pages);
 
			 model.addAttribute("managers", managers);
			 
			 
		/*	 int managerId=(int)session.getAttribute("userid");
			 model.addAttribute("managerId", managerId);*/
		 
			   //理财客户
	         List<TVip> vips=new ArrayList<TVip>();
	         vips=vipService.getVips(0);
			 model.addAttribute("vips", vips);
			 
			 
			  

/* 	 Gson gson=new Gson();
			 System.out.println("cccc"+gson.toJson(managers)); */
	    
		return "manager_vip/getSchedules";
	}
	
	
	
	 // 理财经理 导出
	@RequestMapping("/exportVipScheduleList")
	public String  exportVipScheduleList(PageVo pageVo,HttpServletRequest request,HttpServletResponse response    ) throws  Exception
	{
	 
		String fileName="还款计划表 ";
        //填充projects数据
		
	    List<ExcelScheduleVo> vos=new ArrayList<ExcelScheduleVo>();
	    
	     //根据查询条件获取数据
	     vos=scheduleService.getfinanceDate(pageVo);
	    
    //    List<Project> projects= createData();
        
        List<Map<String,Object>> list=vipService.createExcelRecord(vos);
      //列名
        String columnNames[]={"合同编号","合同金额","合同时间","合同期限", "理财经理","理财客户","收款银行","账号","还款时间","还款金额","当前状态"};
        //map中的key
        String keys[]    =     {"contractString","billMoney", "billTime","billLongTime", "managerName","vipName","vipBankName","vipBankNum","backTime","backMoney","flag"};
        
  
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
	
	
	
	 //调转到增加拜访记录
	 @RequestMapping("/addVipVisitInit")
	public String  addVipVisitInit(Model model,HttpSession session) throws ServicesException
	
	{
		 
	     List<TVip> vips=new ArrayList<TVip>();
         
	     
	     
		 int loginUserId=(int)session.getAttribute("userid");
		 
		 
		
		 TUser user=userService.findUserById(loginUserId);
		 
		  //客服 和 admin 可以显示所有理财客户
		  if ( user.getDepartmentid()==6  |   user.getUsername().equals("admin")  )
		  {
	          vips=vipService.getVips(0);
		  }
		  else {
			
			  vips=vipService.getVips(loginUserId);
		}
 
           model.addAttribute("vips", vips);
  
		return  "manager_vip/addVipVisit";
		
	}
	 
	 
	  //处理增加拜访记录
	 @RequestMapping("/addVipVisitAction")
	 @ResponseBody
	public AjaxMsg  addVipVisitAction(TVisit  visit,HttpSession session) throws Exception
	{
 
            AjaxMsg msg=new AjaxMsg();
             
            int userId=(int)session.getAttribute("userid");
            vipService.addVipVisitAction(visit,userId,msg );
            
            return msg;
		 
	}
	 
	 
	 @RequestMapping("/getVipVisitList")
	  
	public String  getVipVisitList(PageVo pageVo,Model model,HttpSession session) throws Exception
	{
 
		 
		 List<TVip> vips=new ArrayList<TVip>();
         vips=vipService.getVips(0);
         model.addAttribute("vips", vips);
         
           
         int userId=(int)session.getAttribute("userid");
         
         
         /*model.addAttribute("managerId", userId);*/
         
       
     	       List<TUser> managers=new ArrayList<TUser>();
     	       
     	      PageBean<VipVisitVo>  pages=null;
     	      
                          //admin    或者客服部门人员   则不加限制  否则强制增加 条件过滤
     	               if (  (userId==180)  || (userService.findUserById(userId).getDepartmentid()==6) )
     	               {
     	            	     pages=vipService.getVipVisitPages(pageVo,0) ;
     	            	     
     	            	     //理财经理
     	         	        
     	        	         managers=userService.getUsers();
     	               }
     	               else {
     	            	    
     	            	    managers.add(userService.findUserById(userId));
     	            	    pages=vipService.getVipVisitPages(pageVo,userId) ;
     				}
     	      
     		         model.addAttribute("pages", pages);
      
     			 model.addAttribute("managers", managers);
        
     			 
  
		    Gson gson=new Gson();
	 
		 
		 // System.out.println("数据:"+gson.toJson(pages));
		     

		return  "manager_vip/getVipVisitList";
		 
	}
	 
	 
	 @RequestMapping("/getVipVisitInfo")
	  
	public String  getVipVisitInfo(Integer visitId,Model model) throws Exception
	{
       
		 
		 model.addAttribute("visitVo", vipService.getVisitVoById(visitId));
		  
		return  "manager_vip/getVipVisitInfo";
		 
	}
	 
	 
	 
	 
	  //删除 拜访记录
	 @RequestMapping("/deleteVipVisitById")
	 @ResponseBody
	public AjaxMsg  deleteVipVisitById(Integer id,HttpServletRequest request,HttpSession session) throws Exception
	
	{
		 
			// log.error("操作用户:"+session.getAttribute("username")+ "删除id:"+id+"来源ip："+request.getRemoteAddr() );
			
			logService.doLog((int)session.getAttribute("userid"), "删除id为"+id+"的拜访记录", request.getRemoteAddr());
		 
           AjaxMsg msg=new AjaxMsg();
           
           
           
           vipService.deleteVipVisitById(id,msg);
           
           return msg;
		 
	}
	 
	

}
