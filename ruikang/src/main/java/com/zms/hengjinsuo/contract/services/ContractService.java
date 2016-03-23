package com.zms.hengjinsuo.contract.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TContractExample;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TRzbankExample;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bill.services.BillService;
import com.zms.hengjinsuo.dao.TContractMapper;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.schedule.services.ScheduleService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ContractVo;
import com.zms.hengjinsuo.vo.ExcelContractVo;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;


@Service
public class ContractService {

	@Autowired
	private TContractMapper contractMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private RongziService rongziService;
	
	@Autowired
	private  InvestService investService;
	
	
	@Autowired
	private VipService vipService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private RzBankService  rzBankService;
	
	@Autowired
	private TRzbankMapper rzbankMapper;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private ScheduleService scheduleService;
	

	
	private static Logger log = Logger.getLogger(ContractService.class);
	
	
	
	
	
	
	
	  //删除
		public void deleteContractById(Integer id, AjaxMsg msg,HttpServletRequest request) {

			//判断 财务单据bill和 还款计划表里有没有  （还款计划表增加一个字段 contractidInt) 
			
			
			  if (billService.checkHasContract(id))
			  {
				  logService.doLog((int)request.getSession().getAttribute("userid"), "删除合同:"+id+"失败，因为合同已经生成财务单据！", request.getRemoteAddr());
				    msg.setMsg("hasBill");
				    return ;
			  }
			  
			  if (scheduleService.checkHasContract(id))
			  {
				  logService.doLog((int)request.getSession().getAttribute("userid"), "删除合同:"+id+"失败,因为合同已经生成还款计划！", request.getRemoteAddr());
				    msg.setMsg("hasSchedule");
				    return ;
			  }

		     if (contractMapper.deleteByPrimaryKey(id)>0)
		     {
		    	 
		    	  logService.doLog((int)request.getSession().getAttribute("userid"), "删除合同:"+id+"成功", request.getRemoteAddr());
					  msg.setMsg("deleteOK");
					}
					else {
						 logService.doLog((int)request.getSession().getAttribute("userid"), "删除合同:"+id+"失败", request.getRemoteAddr());
					 msg.setMsg("deleteFailed");
					}
		     
		     
			
		}
 
		 //根据字符串的 那个 获取 实体

     public TContract getByStringId(String id)
     {
    	 TContractExample example=new TContractExample();
    	 
         TContractExample.Criteria criteria=example.createCriteria();
         criteria.andContractidEqualTo(id);
         
         List<TContract> contracts=contractMapper.selectByExample(example);
         
         if (contracts!=null)
         {
        	 
        	 if (contracts.size()>0)
        	 {
        		 return contracts.get(0);
        	 }
         }
         return null;
    	 
    	 
     }



           //获取分页信息
		public PageBean<ContractVo> getContractVoPages(PageVo pageVo) throws ServicesException {
			 
			//给前台返回所有 分页数据
			PageBean<ContractVo> pageBean=new PageBean<ContractVo>();
			 //给前台返回 搜索条件
			 Map<String,Object> conditions=new HashMap<String, Object>();
			 
			 	List<TContract>  contracts=new ArrayList<TContract>();
			 	
				List<ContractVo>  vos=new ArrayList<ContractVo>();
			 	
			 	
			 //执行的sql语句
			String sqlString="";
			
			  //开始吧 条件里的参数 和值 取出来
			if ( pageVo.conditions!=null)
			{
				//合同编号  字符串那个
				 if (pageVo.conditions.get("contractId")!=null &&  pageVo.conditions.get("contractId")!="" )
				 {
					  if ( ! pageVo.conditions.get("contractId").equals(""))
					  {
							 sqlString=sqlString+" and  contractId like  '%"+pageVo.conditions.get("contractId")+"%'";
							 
							 conditions.put("contractId", pageVo.conditions.get("contractId"));
					  }
				
					 
				 }
				 
				   //合同状态
				 if (pageVo.conditions.get("flag")!=null && pageVo.conditions.get("flag")!="")
				 {
					 
					 
					 String flagString=(String)pageVo.conditions.get("flag");
					 int  flag=Integer.valueOf(flagString);
					 
					  if (flag>0)
					  {
						  sqlString=sqlString+" and  flag="+flag;
							 
							 conditions.put("flag",  String.valueOf(flag));
					  }
					
					 
				 }
				 
				 //合同签署时间
				 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
				 {
					 
					 sqlString=sqlString+" and  putContractTime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
					 
					 conditions.put("beginHT", pageVo.conditions.get("beginHT"));
					 
					// System.out.println("开始时间:"+pageVo.conditions.get("beginHT"));
				 }
				 
				 
				 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
				 {
					 sqlString=sqlString+" and  putContractTime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
					 
					 conditions.put("endHT", pageVo.conditions.get("endHT"));
					 
				 }
				 
				 if (pageVo.conditions.get("manager")!=null && pageVo.conditions.get("manager")!="" )
				 {
					 
					 String managerString=(String)pageVo.conditions.get("manager");
					 int  managerId=Integer.valueOf(managerString);
					 
					 
					  if (managerId>0)
					  {
							 sqlString=sqlString+" and  managerId="+managerId;
							 
							 conditions.put("manager",  String.valueOf(managerId));
					  }
				
					 
				 }
				 
				 
	  
			}
			

			 
			  //统计 记录数量
				 int  count=workMapper.getCountOfContract(sqlString);
	 
				  
				 sqlString=sqlString+" order by createContractTime desc  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
				 
				 contracts=workMapper.getContracts(sqlString);

		           if(contracts!=null)
		           {
		        	   
		        	     if (contracts.size()>0)
		        	     {
		        	    	     for (int i=0;i<contracts.size();i++)
		        	    	     {
		        	    	    	 ContractVo vo=new ContractVo();
		        	    	    	 TContract contract=contracts.get(i);
		        	    	    	 
		        	    	    	  //合同
		        	    	    	 vo.setContract(contract);
		        	    	    	 
		        	    	    	  //项目
		        	    	    	  TInvests invest=investService.getInvestById(contract.getInvestid());
		        	    	    	  vo.setInvest(invest);
		        	    	    	 
		        	    	    	  //理财客户
		        	    	    	  TVip vip=vipService.getVipById(contract.getVipid());
		        	    	    	  vo.setVip(vip);
		        	    	    	 
		        	    	    	   //融资客户
		        	    	    	  TRongzi rongzi=new TRongzi();
		        	    	    	  rongzi=rongziService.getRongziById(contract.getRongziid());
		        	    	    	  vo.setRongzi(rongzi);
		        	    	    	   //银行账户
		        	    	    	  TRzbank bank=new TRzbank();
		        	    	    	  bank=rzBankService.getBankById(contract.getRzbankid());
		        	    	    	  vo.setBank(bank);
		        	    	    	  
		        	    	     	   //理财经理
		        	    	    	  TUser manager=new TUser();
		        	    	    	  manager=userService.findUserById(contract.getManagerid());
		        	    	    	 vo.setManager(manager);
		        	    	    	  
		        	    	    	  
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
 
		   //批量创建合同
		public void addContractAction(String preFix, Integer startNum, Integer count,
				Integer  rzId,  AjaxMsg msg) throws Exception
		{
			 
			for (int i = startNum ; i < (startNum+count); i++) {
			    TContract contract=new TContract();
			  //  String contractId=preFix+String.format("%04d", i);
			    String contractId=preFix+i;
			    contract.setContractid(contractId);
			    contract.setRongziid(rzId);
			    //contract.setRzbankid(rzbankid);
			    contract.setCreatecontracttime(new Date());
			    contractMapper.insertSelective(contract);
			}
			
			msg.setMsg("insertOK");
			
		}





           ////创建合同界面 根据 融资项目id 动态查询 还款银行
		public List<TRzbank> getBankByInvest(Integer id) {
			 
			   if (id!=null)
			   {
				   int rzId=investService.getInvestById(id).getRongziid();
				   
				   TRzbankExample example=new TRzbankExample();
				   TRzbankExample.Criteria criteria=example.createCriteria();
				   criteria.andRongziidEqualTo(rzId);
				   
				   return  rzbankMapper.selectByExample(example);
				    
			   }
			   
			   return null;
			
		}

 
        //根据主键获取实体
		public TContract getContractById(Integer id) {
			 
		 return  contractMapper.selectByPrimaryKey(id);
			
		}

		
		
	      //根据 字符串那个 获取合同
			public TContract getContractByStrId(String strId) {
				 
			    TContractExample example=new TContractExample();
			    TContractExample.Criteria criteria=example.createCriteria();
			    criteria.andContractidEqualTo(strId);
			    
			    List<TContract> contracts=new ArrayList<TContract>();
			    contracts=  contractMapper.selectByExample(example);
			     
			    if  (contracts!=null)
			    {
			    	 if (contracts.size()>0)
			    	 {
			    		 return contracts.get(0);
			    	 }
			    }
			 return null;
				
			}



            //修改
		public void updateContractAction(TContract contract, AjaxMsg msg,HttpServletRequest request) {
			 
			      contract.setFlag(2);
			      contract.setGetcontracttime(new Date());
			   if (contractMapper.updateByPrimaryKeySelective(contract)>0){
				   
				   
				     logService.doLog((int)request.getSession().getAttribute("userid"), "领取合同:"+contract.getContractid()+"成功", request.getRemoteAddr());
				   msg.setMsg("updateOK");
				   
				    //需要把状体改为已领用
				   
				   return;
			   }
			   else {
				   logService.doLog((int)request.getSession().getAttribute("userid"), "领取合同:"+contract.getContractid()+"成功", request.getRemoteAddr());
				msg.setMsg("updateFailed");
				return;
			}
			
		}




      //根据flag 获取合同信息  未领取  1 已领取  2 已签订 3 还款完毕 4  续签5  异常6   

		public List<TContract> getContractList(int flag) {
			  
			List<TContract> contracts=new ArrayList<TContract>();
			
			TContractExample example=new TContractExample();
			TContractExample.Criteria criteria=example.createCriteria();
			 if (flag==0)
			 {
				 contracts=contractMapper.selectByExample(null); 
			 }
			 else {
				
			    criteria.andFlagEqualTo(flag); 
			    contracts=contractMapper.selectByExample(example); 
			}
			 
			return contracts;
		}

		
		//根据合同id 获取 对应 融资客户的所有 银行列表
		public List<TRzbank> getBanksByContractId(Integer id) {
			 
			  TContract contract=contractMapper.selectByPrimaryKey(id);
			  TRongzi rongzi=rongziService.getRongziById(contract.getRongziid());
			  
			  
			  List<TRzbank> banks=rzBankService.getBanksByRongzi(rongzi);
			  
			
			return banks;
		}
		
		
		 //获取数据

		public List<ExcelContractVo> getContractDate(PageVo pageVo) {
			
			
			List<ExcelContractVo>  vos=new ArrayList<ExcelContractVo>();
			
			
			String sqlString=" select  b.id billId,c.contractid contractString,b.memony billMoney,date_format(b.contracttime,'%Y-%m-%d') as billTime ,b.longtime billLongTime,r.name  rongzi,t.name invest, u.realname managerName, v.name vipName,"
					+ " case   when  c.flag=1 then  '未领取'   when  c.flag=2 then '已领取'  when  c.flag=3 then '已签订'  when c.flag=4 then '还款完毕'  when c.flag=5 then '续签' when c.flag=6 then '异常'  END  flag "
					+" from   t_bill b, t_contract c,t_rongzi r,t_invests t,t_user u,t_vip v "
					+" where b.contractid=c.id and b.rongziid=r.id and b.investid=t.id  and c.managerid=u.id and c.vipid=v.id " ;
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			//合同编号  字符串那个
			 if (pageVo.conditions.get("contractId")!=null &&  pageVo.conditions.get("contractId")!="")
			 {
				  
						 sqlString=sqlString+" and  c.contractid  like  '%"+pageVo.conditions.get("contractId")+"%'";
			 }
			 
			 
			//状态
			 if (pageVo.conditions.get("flag")!=null && pageVo.conditions.get("flag")!="")
			 {
				 String  flagId=(String)pageVo.conditions.get("flag");
				  if ( ! flagId.equals("0"))
				  {
						 sqlString=sqlString+" and  c.flag =  "+ flagId;
				  }
 
			 }
			 
			 
			 
			 //合同签订时间
			 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
			 {
				 sqlString=sqlString+" and  b.contracttime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
			 }
			 
			 
			 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
			 {
				 sqlString=sqlString+" and  b.contracttime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
			 }
			 
			  //理财经理
			 if (pageVo.conditions.get("manager")!=null   && pageVo.conditions.get("manager")!="" )
			 {
				 
			 int managerId=  Integer.valueOf((String)pageVo.conditions.get("manager")    );
			 
				  if (managerId>0)
				  {
						 sqlString=sqlString+" and  c.managerid="+managerId;
				  }
		 
			 }
			 
			   //理财客户
			 if (pageVo.conditions.get("vip")!=null && pageVo.conditions.get("vip")!="" )
			 {
				 sqlString=sqlString+" and  v.name like  '%"+pageVo.conditions.get("vip")+"%'";
			 }
  
		}
 
			// System.out.println("查询的sql"+sqlString);
		 	 vos=workMapper.getContractDate(sqlString);
	    
 
		return vos;
		}

		  // 整理数据
		public List<Map<String, Object>> createExcelRecord( List<ExcelContractVo> vos)
		
		{
			    List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("sheetName", "sheet1");
		        listmap.add(map);
		        ExcelContractVo vo=null;
		        for (int j = 0; j < vos.size(); j++) {
		            vo=vos.get(j);
		            Map<String, Object> mapValue = new HashMap<String, Object>();
		            
		            mapValue.put("billId", vo.getBillId());
		            mapValue.put("contractString", vo.getContractString());
		            mapValue.put("billMoney", vo.getBillMoney());
		            mapValue.put("billTime", vo.getBillTime());
		            mapValue.put("billLongTime", vo.getBillLongTime());
		            mapValue.put("rongzi", vo.getRongzi());
		            mapValue.put("invest", vo.getInvest());
		            mapValue.put("managerName",vo.getManagerName() );
		            mapValue.put("vipName",vo.getVipName() );
		            mapValue.put("flag", vo.getFlag() );
		            listmap.add(mapValue);
		        }
		        return listmap;
			
			 
		}
		
		  //判断 某个理财客户 是否有理财合同， 主要用在 删除 VIP的时候做一个判断
		public boolean checkVipHasContract(int vipId)
		{
			
			List<TContract> contracts=new 	ArrayList<TContract>();
			
			 TContractExample example=new TContractExample();
			 TContractExample.Criteria criteria=example.createCriteria();
			 criteria.andVipidEqualTo(vipId);
			 
			 contracts=contractMapper.selectByExample(example);
			 
			 if (contracts!=null)
			 {
				  if (contracts.size()>0)
				  {
					  return true;
				  }
			 }
			
			
			return false;
		}

		  //删除银行的时候 判断 是否有合同关联 否则不让删除
		public boolean checkBankHasContracts(Integer bankId) {
			
			List<TContract> contracts=new 	ArrayList<TContract>();
			
			 TContractExample example=new TContractExample();
			 TContractExample.Criteria criteria=example.createCriteria();
			 criteria.andRzbankidEqualTo(bankId);
			 
			 contracts=contractMapper.selectByExample(example);
			 
			 if (contracts!=null)
			 {
				  if (contracts.size()>0)
				  {
					  return true;
				  }
			 }
			
			
			return false;
		}


		  //删除项目的时候 判断 是否有合同关联 否则不让删除
		
		public boolean checkInvestHasContracts(Integer investId) {
			
			List<TContract> contracts=new 	ArrayList<TContract>();
			
			 TContractExample example=new TContractExample();
			 TContractExample.Criteria criteria=example.createCriteria();
			 criteria.andInvestidEqualTo(investId);
			 
			 contracts=contractMapper.selectByExample(example);
			 
			 if (contracts!=null)
			 {
				  if (contracts.size()>0)
				  {
					  return true;
				  }
			 }
			
			
			return false;
		}
		
		
	
 
	  

}
