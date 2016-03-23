package com.zms.hengjinsuo.schedule.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TBill;
import com.zms.hengjinsuo.bean.TBillExample;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TSchedule;
import com.zms.hengjinsuo.bean.TScheduleExample;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TBillMapper;
import com.zms.hengjinsuo.dao.TContractMapper;
import com.zms.hengjinsuo.dao.TInvestsMapper;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.TScheduleMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.fundpool.services.FundPoolService;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.ScheduleVo;


@Service
public class ScheduleService {

	@Autowired
	private TContractMapper contractMapper;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private RongziService rongziService;
	
	@Autowired
	private  InvestService investService;
	
	
	@Autowired
	private VipService vipService;
	
	@Autowired
	private TBillMapper billMapper;
	
	
	@Autowired
	private RzBankService  rzBankService;
	
	@Autowired
	private TRzbankMapper rzbankMapper;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private TScheduleMapper scheduleMapper;
	
	@Autowired
	private TInvestsMapper investsMapper;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private FundPoolService fundPoolService;
	
	
	private static Logger log = Logger.getLogger(ScheduleService.class);


	  //还款计划表
	 //managerId 如果=0 说明查所有 ，managerId>0 说明查某个 理财经理的
	public PageBean<ScheduleVo> getScheduleVoPages(PageVo pageVo,Integer byManagerId) throws ServicesException {
		 
		
		
		
		//给前台返回所有 分页数据
		PageBean<ScheduleVo> pageBean=new PageBean<ScheduleVo>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 
		 	List<TSchedule>  schedules=new ArrayList<TSchedule>();
		 	
			List<ScheduleVo>  vos=new ArrayList<ScheduleVo>();
		 	
		 	
	  //执行的sql语句
		String sqlString="";
		
		   if (byManagerId!=null )
		   {
			    if (byManagerId>0)
			    {
			    	
			    	 sqlString=sqlString+" and  managerId="+byManagerId;
			    	 conditions.put("manager", byManagerId);
			    }
			   
		   }
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			//合同编号  字符串那个
			 if (pageVo.conditions.get("contractId")!=null &&  pageVo.conditions.get("contractId")!="")
			 {
				  
						 sqlString=sqlString+" and  contractId like  '%"+pageVo.conditions.get("contractId")+"%'";
						 
						 conditions.put("contractId", pageVo.conditions.get("contractId"));
 
			 }
			 
			 
			//状态
			 if (pageVo.conditions.get("flag")!=null && pageVo.conditions.get("flag")!="")
			 {
				 String  flagId=(String)pageVo.conditions.get("flag");
				  if ( ! flagId.equals("99"))
				  {
						 sqlString=sqlString+" and  flag =  "+ flagId;
						 
						 conditions.put("flag", pageVo.conditions.get("flag"));
				  }
				  else {
					
					  conditions.put("flag", 99);
				}
			
				 
			 }
			 
			 
			 
			 //还款时间
			 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
			 {
				 
				 sqlString=sqlString+" and  memonyTime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
				 
				 conditions.put("beginHT", pageVo.conditions.get("beginHT"));
				 
				// System.out.println("开始时间:"+pageVo.conditions.get("beginHT"));
			 }
			 
			 
			 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
			 {
				 sqlString=sqlString+" and  memonyTime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
				 
				 conditions.put("endHT", pageVo.conditions.get("endHT"));
				 
			 }
			 
			  //理财经理
			 if (pageVo.conditions.get("manager")!=null   && pageVo.conditions.get("manager")!="" )
			 {
				 
			 int managerId=  Integer.valueOf((String)pageVo.conditions.get("manager")    );
			 
				  if (managerId>0)
				  {
						 sqlString=sqlString+" and  managerId="+managerId;
						 
						 conditions.put("manager", pageVo.conditions.get("manager"));
				  }
				  else {
					  conditions.put("manager", 0);
					  
				}
			
				 
			 }
			 
			   //理财客户
			 if (pageVo.conditions.get("vip")!=null && pageVo.conditions.get("vip")!="" )
			 {
				 
				 sqlString=sqlString+" and  vipname like  '%"+pageVo.conditions.get("vip")+"%'";
				 
				 conditions.put("vip", pageVo.conditions.get("vip"));
				 
			 }
			 
			 
  
		}
		  //默认没有查询条件的时候 把 manager=0 意思是查询所有 理财经理 否则 前台会传 manager=过来 导致 转整数失败
	/*	  else {
			  conditions.put("manager", 0);
			  conditions.put("flag", 99);
		}
		*/

		 
		  //统计 记录数量
			 int  count=workMapper.getCountOfSchedule(sqlString);
			 
			 //处理排序
			 
				if ( pageVo.conditions!=null)
				{
					 
					 if (pageVo.conditions.get("orderBy")!=null &&  pageVo.conditions.get("orderBy")!="")
					 {
						 
						 sqlString=sqlString+" order by " +pageVo.conditions.get("orderBy");
						 conditions.put("orderBy", pageVo.conditions.get("orderBy"));
					 }
					 
					 if (pageVo.conditions.get("desc")!=null &&  pageVo.conditions.get("desc")!="")
					 {
						  int  desc=(int)pageVo.conditions.get("desc");
						   if (desc==1)
						   {
							   sqlString=sqlString+" order by " +pageVo.conditions.get("desc");
							   conditions.put("desc", pageVo.conditions.get("desc"));
						   }
					 
						
						
					 }
					 
				}
			 
 
			  
			 sqlString=sqlString+"     limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			// System.out.println("财务计划表:"+sqlString);
			 
			 schedules=workMapper.getSchedules(sqlString);

	           if(schedules!=null)
	           {
	        	   
	        	     if (schedules.size()>0)
	        	     {
	        	    	     for (int i=0;i<schedules.size();i++)
	        	    	     {
	        	    	    	 ScheduleVo  vo=new ScheduleVo();
	        	    	    	 TSchedule schedule=schedules.get(i);
	        	    	    	 
	        	    	    	  //合同
	        	    	            TContract contract=contractService.getByStringId(schedule.getContractid());
	        	    	            vo.setContract(contract);
	        	    	    	 
	        	    	    	  //项目
	        	    	    	  TInvests invest=investService.getInvestById(schedule.getInvestid());
	        	    	    	  vo.setInvest(invest);
	        	    	    	 
	        	    	    	  //理财客户
	        	    	           TVip vip=vipService.getVipByContrantId( contract.getContractid());
	        	    	           vo.setVip(vip);
	        	    	    	 
	        	    	    	   //融资客户
	        	    	    	  TRongzi rongzi=rongziService.getRongziByContractId(contract.getId());
	        	    	    	  vo.setRz(rongzi);
	        	    	    	  
	        	    	    	   //融资人银行账号
	        	    	    	  TRzbank rzbank=rzBankService.getBankById(contract.getRzbankid());
	        	    	    	  vo.setRzbank(rzbank);
	        	    	 
	        	    	     	   //理财经理
	        	    	    	  TUser manager=userService.findUserById(schedule.getManagerid());
	        	    	           vo.setManager(manager);
	        	    	    	  
	        	    	           // 理财单据
	        	    	           TBill bill=billMapper.selectByPrimaryKey(schedule.getBillid());
	        	    	           
	        	    	           vo.setBill(bill);
	        	    	           
	        	    	            vo.setSchedule(schedule);
	        	    	    	  
	        	    	    	 vos.add(vo);
	        	    	     }
	        	     }
	           }
		 
		 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 
		 pageBean.setPageDatas(vos);
		 
		  
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams()); 
		  pageBean.setConditions(conditions);
		 
		  return pageBean;
		
		
		
		
		
		
		
		
	}


	public TSchedule getScheduleById(Integer id) {
		 
		return scheduleMapper.selectByPrimaryKey(id);
	}

	
	 //编辑

	public void updateScheduleAction(TSchedule schedule, AjaxMsg msg,HttpServletRequest request) {
 
		  TSchedule scheduleDB=null;
		  if (scheduleMapper.updateByPrimaryKeySelective(schedule)>0)
		  {
  
			  
			    scheduleDB=scheduleMapper.selectByPrimaryKey(schedule.getId());
			  
				 TBill bill=billMapper.selectByPrimaryKey(scheduleDB.getBillid());
			 
				  if  ( scheduleDB.getLastflag())
			    {
			    	
			    	 TContract contract=new TContract();
			    	 contract=contractService.getByStringId(schedule.getContractid());
			    	// 未领取  1 ,已领取  2 ,已签订 3 ,还款完毕 4  ,续签5  异常6
			    	 contract.setFlag( 4);
			    	 
			    	 contractMapper.updateByPrimaryKeySelective(contract);
			    	 
			    	   //把 融资项目 剩余融资金额增加
			    
			    	  //合同金额 （财务单据 真实的合同‘额）
			    	 float htMoney=bill.getMemony();
			    	 TInvests invest=investService.getInvestById(scheduleDB.getInvestid());
			    	   if (invest.getNeedmoney()!=null)
			    	   {
			    		   invest.setNeedmoney(invest.getNeedmoney()+htMoney);
			    		   investsMapper.updateByPrimaryKeySelective(invest);
			    	   }
			    	   
			    	   //修改bill 理财凭证标志 0：还未还款  1 还款中  2 还款结束  4异常
			    	  bill.setFlag(2);
			    	
			    }
			  else{
				  
				    bill.setFlag(1);
			  }
			  
			  billMapper.updateByPrimaryKeySelective(bill);
			  
			  //  *@参数:@param flow  资金流向 0 流入 1 流出
			//  *@参数:@param flowType 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减
			  fundPoolService.addItem(1, 5, scheduleDB.getMemony(), (String)request.getSession().getAttribute("username"));
			  
			  logService.doLog((int)request.getSession().getAttribute("userid"), "登记还款任务:"+schedule.getId()+"状态为:"+schedule.getFlag()+"成功! ", request.getRemoteAddr());
			  
			    
			  msg.setMsg("updateOK");
		  }
		  else {
			  logService.doLog((int)request.getSession().getAttribute("userid"), "登记还款任务:"+schedule.getId()+"状态为:"+schedule.getFlag()+"失败! ", request.getRemoteAddr());
			 
			msg.setMsg("updateFailed");
		}
		
	}


	  //根据合同号 查找 还款计划表里 是否有标志不为 1 （已还款) 的数据 如果没有，则把合同表的标识 改为4
	    // 合同标识   未领取  1 已领取  2 已签订 3 还款完毕 4  续签5  异常6
	public  boolean  contractHasFinished(String contractId)
	{
		
		TScheduleExample example=new TScheduleExample();
		TScheduleExample.Criteria criteria=example.createCriteria();
		
		criteria.andContractidEqualTo(contractId);
		criteria.andFlagNotEqualTo( 1);
		
		List<TSchedule> schedules=new ArrayList<TSchedule>();
		schedules=scheduleMapper.selectByExample(example);
		 
		if (schedules!=null)
		{
			 if (schedules.size()>0)
			 {
				 return false;
			 }
			 else
			 {
				 return true;
			 }
		}
		
		return  false;
	}
	
	
	//主键
	
	public ScheduleVo getScheduleVoById(Integer id) {
		 
		TSchedule schedule=getScheduleById(id);
		
	 ScheduleVo vo =new ScheduleVo();
	 

	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 
	  if (schedule.getBacktime()!=null)
	  {
			  vo.setBackTime(format.format(schedule.getBacktime()));
		 
	  }
	  else {
		vo.setBackTime(format.format(schedule.getMemonytime()));
	}

		 vo.setSchedule(schedule);
		 
	 
	
	 
	 return vo;
	}
	
	

	//根据查询条件获取数据
	public List<ExcelScheduleVo> getfinanceDate(PageVo pageVo) {
 
		 	 
			List<ExcelScheduleVo>  vos=new ArrayList<ExcelScheduleVo>();
			String sqlString=" select  a.contractid contractString,b.memony billMoney,date_format(b.contracttime,'%Y-%m-%d') as billTime,b.longtime billLongTime,r.name rongzi,i.name investName,k.bankname rzBankName ,k.banknum rzBankNum,u.realname managerName,v.name vipName,v.bankname vipBankName,v.banknum vipBankNum,date_format(a.memonytime,'%Y-%m-%d')  as  backTime,a.memony backMoney, " 
	       +"    case   when  a.flag=0 then  '未还款'   when  a.flag=1 then '已还款'  when  a.flag=2 then '逾期'  when a.flag=4 then '异常'  END  flag ,"
		   +"  case when b.backType=0 then '先息后本'  when b.backType=1 then '等额本息'   when  b.backType=2 then '一次性还本付息' END backType  "
           +"  from  t_schedule a, t_bill b,t_rongzi r,t_rzbank k,t_user u,t_vip v,t_invests i "
           +"  where a.billid=b.id and a.rongziid=r.id and a.rzbankid=k.id and a.managerid=u.id and a.vipid=v.id and a.investId=i.id ";
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			//合同编号  字符串那个
			 if (pageVo.conditions.get("contractId")!=null &&  pageVo.conditions.get("contractId")!="")
			 {
				  
						 sqlString=sqlString+" and  a.contractid  like  '%"+pageVo.conditions.get("contractId")+"%'";
			 }
			 
			 
			//状态
			 if (pageVo.conditions.get("flag")!=null && pageVo.conditions.get("flag")!="")
			 {
				 String  flagId=(String)pageVo.conditions.get("flag");
				  if ( ! flagId.equals("99"))
				  {
						 sqlString=sqlString+" and  a.flag =  "+ flagId;
				  }
 
			 }
			 
			 
			 
			 //还款时间
			 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
			 {
				 sqlString=sqlString+" and  a.memonytime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
			 }
			 
			 
			 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
			 {
				 sqlString=sqlString+" and  a.memonytime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
			 }
			 
			  //理财经理
			 if (pageVo.conditions.get("manager")!=null   && pageVo.conditions.get("manager")!="" )
			 {
				 
			 int managerId=  Integer.valueOf((String)pageVo.conditions.get("manager")    );
			 
				  if (managerId>0)
				  {
						 sqlString=sqlString+" and  a.managerId="+managerId;
				  }
		 
			 }
			 
			   //理财客户
			 if (pageVo.conditions.get("vip")!=null && pageVo.conditions.get("vip")!="" )
			 {
				 sqlString=sqlString+" and  a.vipname like  '%"+pageVo.conditions.get("vip")+"%'";
			 }
  
		}
 
		//	 sqlString=sqlString+"  order by a.backtime  ,a.memonytime      limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			  //System.out.println("查询的sql"+sqlString);
			 vos=workMapper.getfinanceDate(sqlString);

	    
 
		return vos;
	}

	
	
	
	  //把 数据封装成 excel需要的 行 列数据
     public  List<Map<String, Object>> createExcelRecord(List<ExcelScheduleVo> vos) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        ExcelScheduleVo vo=null;
        for (int j = 0; j < vos.size(); j++) {
            vo=vos.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("contractString", vo.getContractString());
            mapValue.put("billMoney", vo.getBillMoney());
            mapValue.put("billTime", vo.getBillTime());
            mapValue.put("billLongTime", vo.getBillLongTime());
            mapValue.put("rongzi", vo.getRongzi());
            mapValue.put("investName", vo.getInvestName());
            mapValue.put("rzBankName",vo.getRzBankName() );
            mapValue.put("rzBankNum",vo.getRzBankNum() );
            mapValue.put("managerName",vo.getManagerName() );
            mapValue.put("vipName",vo.getVipName() );
            mapValue.put("backType", vo.getBackType());
            mapValue.put("vipBankName", vo.getVipBankName());
            mapValue.put("vipBankNum", vo.getVipBankNum());
            mapValue.put("backTime", vo.getBackTime());
            mapValue.put("backMoney", vo.getBackMoney());
            mapValue.put("flag", vo.getFlag() );
            listmap.add(mapValue);
        }
        return listmap;
    }


      //删除合同的时候 判断
	public boolean checkHasContract(Integer contractId) {
    
		 List<TSchedule> schedules=new ArrayList<TSchedule>();
		
		TScheduleExample example=new TScheduleExample();
		TScheduleExample.Criteria criteria=example.createCriteria();
	    criteria.andContractidintEqualTo(contractId);
	    
	    schedules=scheduleMapper.selectByExample(example);
	  
		
	    if (schedules!=null)
	    {
	    	
	    	 if (schedules.size()>0)
	    	 {
	    		 return true;
	    	 }
	    }
		
		return false;
	}

      //删除 billid 为 id 的 还款计划表， 主要用来给财务模块 删除 bill时 清除数据使用
	public void deleteByBillId(Integer id) {
		 
		 
			
			TScheduleExample example=new TScheduleExample();
			TScheduleExample.Criteria criteria=example.createCriteria();
		    criteria.andBillidEqualTo(id);
		    
		    scheduleMapper.deleteByExample(example);
		
	}

         // 获取某个  bill 的 还款计划
	public List<TSchedule> getSchedulesByBillId(Integer billId) {
		 
		List<TSchedule> schedules=new ArrayList<TSchedule>();
		
		TScheduleExample example=new TScheduleExample();
		TScheduleExample.Criteria criteria=example.createCriteria();
	    
		criteria.andBillidEqualTo(billId);
		
		schedules=scheduleMapper.selectByExample(example);
	    
	    return schedules;
	    
	}
 
    
    
 
}
