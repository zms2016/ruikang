package com.zms.hengjinsuo.invest.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.poi.sl.draw.geom.IfElseExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TBill;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TInvestsExample;
import com.zms.hengjinsuo.bean.TReceive;
import com.zms.hengjinsuo.bean.TReceiveExample;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TRzbankExample;
import com.zms.hengjinsuo.bean.TSchedule;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bill.services.BillService;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TInvestsMapper;
import com.zms.hengjinsuo.dao.TReceiveMapper;
import com.zms.hengjinsuo.dao.TRongziMapper;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.fundpool.services.FundPoolService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.InvestVo;
import com.zms.hengjinsuo.vo.MoneyToolInfo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ReceiveVo;
import com.zms.util.CalendarTest;


@Service
public class InvestService {

 @Autowired
 private  TInvestsMapper investsMapper;
 
 @Autowired
 private TRzbankMapper rzbankMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private TRongziMapper rongziMapper;
	
	@Autowired
	private  ContractService contractService;
	
	@Autowired
	private RzBankService rzBankService;
	
	@Autowired
	private FundPoolService fundPoolService;
	
	@Autowired
	private TReceiveMapper receiveMapper;


	 /**
	  * 
	  *@throws ParseException 
	 * @throws NumberFormatException 
	 * @返回值:void
	  *@时间: 2016年3月20日上午10:31:18
	  *@作者:张木生 330140511@qq.com
	  *@说明: 保存融资项目 需要去操作 资金池 和 待收款计划表
	  *@参数:@param invest
	  *@参数:@param msg
	  */
	public void addInvest(TInvests invest, AjaxMsg msg,String username) throws ServicesException, NumberFormatException, ParseException {
	 
		      //初始剩余金额 设置为融资金额
		      invest.setNeedmoney(invest.getRzmoney());
		      
		     //0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减
		    if (investsMapper.insertSelective(invest)>0 && fundPoolService.addItem(1,0,invest.getNeedmoney(),username) )
		    {
		    	//获取刚插入的项目的id
		    	 int investId=invest.getId();
		    	createReceiveList(invest.getLongtime(), Double.parseDouble(String.valueOf(invest.getApr())), invest.getBegintime(), invest.getFirsttime(), Double.parseDouble(String.valueOf(invest.getRzmoney())), (int)invest.getBacktype(), investId);
			     msg.setMsg("insertOK");
			   return;
		   }else {
			msg.setMsg("insertFailed");
		     return;
		}
		
	}
	
	/**
	 * 
	 *@返回值:void
	 *@时间: 2016年3月20日下午2:24:38
	 *@作者:张木生 330140511@qq.com
	 *@说明: 生成 收款计划表
	 *@参数:@param months
	 *@参数:@param apr
	 *@参数:@param beginTime
	 *@参数:@param firstTime
	 *@参数:@param money
	 *@参数:@param type
	 *@参数:@param contract
	 *@参数:@param billId
	 *@参数:@throws ParseException
	 */
 	 public void createReceiveList(Short months,double apr,Date  beginTime,Date firstTime,double money,int type,  int investId) throws ParseException
	 {
 
	    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			   
			 //计算起息日期 和 第一次付息之间的天数
			 int days=CalendarTest.getDaysOfBill(beginTime, firstTime);
			 
			 //计算日利息
			 BigDecimal dayInterest = new BigDecimal( money*(apr/360))  ;
	         double dayInterestD= dayInterest.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();	
			
	         //计算月利息
	         BigDecimal monthInterest = new BigDecimal(money*(apr/12));
	         double monthInterestD= monthInterest.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
			 // System.out.println("日利息:"+dayInterestD+"月利息:"+monthInterestD);
		// DecimalFormat moneyFormat = new DecimalFormat("#.00");
		 
		 switch (type) {
		 //先息后本
		case 0:
		{
       
			 //如果投资期限是 1个月  则 一次算出利息和本金
			 if (months==1)
			 {
				//还款计划表公共部分开始
		                 TReceive receive=new TReceive();
		                  receive.setInvestid(investId);
		                  receive.setBacksort(1);  //还款第几期
		                 receive.setLongtime(1);  //还款总期数
		              //公共部分结束
		                 
				  //还款日期
		                  receive.setBacktime(firstTime);
                    //还款金额  天数*日利息+本金
                  double onlyMonthMeonyD=days*dayInterestD+money;
                  BigDecimal onlyBig = new BigDecimal(  onlyMonthMeonyD );
     	         double onlyMeony= onlyBig.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
                  //还款金额
                   receive.setBackmoney((float)onlyMeony);
                  //设置标志 为“最后一个月"
                   receive.setLastflag(true);
                  
                 // System.out.println("还本付息1个月期限的,起息日期:"+sdf.format(beginTime)+"还款时间:"+sdf.format(firstTime)+"计息天数:"+days+"本息金额:"+ (float)onlyMeony);
                  //插入到还款计划表
              
				  receiveMapper.insertSelective(receive);
				  
				 
			 }
			 
			 //结束 Months=1 
			 
			 //开始 months大于1个月的情况
			 else{
  
				  // 第一次还款日期 用来累加 
				 Calendar firstC = Calendar.getInstance();
				 firstC.setTime(firstTime); 
								 for ( int i=1; i<=months;i++)
								  {
								     	//还款计划表公共部分开始
									 TReceive receive=new TReceive();
					                  receive.setInvestid(investId);
					                  
					                  receive.setBacksort(i);  //还款第几期
					                 receive.setLongtime(Integer.valueOf(months));  //还款总期数
							              //公共部分结束
							                 
						               //如果是第一个月,不用考虑第一个月同时也是最后一个月的情况，因为前面已经判断了投资期限为一个月的情况
							             if (i==1)
							             {
							           	     //第一次的还款日期是固定的，前台传过来的第一次还款日期参数 firstTime
							            	 receive.setBacktime(firstTime);
							            	 
											    //第一个月的 还款日期是  计息天数*日利息
											  float firstMonthMoney=(float)(dayInterestD*days);
											 
											   receive.setBackmoney(firstMonthMoney);
											    //因为投资期只有1个月的 已经判断过了，所以这里设置为false
											   receive.setLastflag(false);
											 
											 //  System.out.println("第一次还款日期:"+sdf.format(firstTime)+"起息日期:"+sdf.format(beginTime)+"__计息天数:"+days+"__还利息款:"+firstMonthMoney);
							             }
						              
									   //如果是 最后一个月
							             else  if (i==months)
									   {
									
						                 //还款金额  要加本金
							            	  BigDecimal lastB = new BigDecimal(  monthInterestD+money );
							      	         double lastMoney= lastB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
							      	         float lastMothMoney= (float) lastMoney;
						                    
						                     receive.setBackmoney(lastMothMoney);
						                   //还款日期
						                     firstC.add(Calendar.MONTH, 1);
						                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
						                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
						                     int year=firstC.get(Calendar.YEAR);
						                     int month=firstC.get(Calendar.MONTH)+1;
						                     String backTimeStr=year+"-"+month+"-"+day;
						                     Date backTime=sdf.parse(backTimeStr);
						                  
						                     receive.setBacktime(backTime);
						                    
						                     receive.setLastflag(true);
							              //  System.out.println("第"+i+"次，也是最后一次还款时间:"+sdf.format(backTime)+"__本息还款:"+lastMothMoney);
							                
									   }
									   //不是 第一个月 ，也不是最后一个月 前面先付利息
									   else {
											  
							                 //还款金额
							            	  BigDecimal mB = new BigDecimal( monthInterestD );
							      	         double mMoney= mB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
							      	         float mothMoney= (float) mMoney;
							      	         receive.setBackmoney(mothMoney);
						                   //还款日期
						                     firstC.add(Calendar.MONTH, 1);
						                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
						                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
						                     int year=firstC.get(Calendar.YEAR);
						                     int month=firstC.get(Calendar.MONTH)+1;
						                     String backTimeStr=year+"-"+month+"-"+day;
						                     Date backTime=sdf.parse(backTimeStr);
						                
						                     receive.setBacktime(backTime);
						                     receive.setLastflag(false);
							               // System.out.println("第"+i+"次，还款时间:"+sdf.format(backTime)+"__还款利息:"+mothMoney);
							                
									}
									
									 
									 receiveMapper.insertSelective(receive);
								  }
				 
			 }
             //结束 months<>1的
			
			
			break;
		}
		 //等额本息
			case 1:
			{
				
				break;
			}
			//一次性还本付息
			case 2:
			{
				
				 
		             double  total=dayInterestD*days+money;
		             BigDecimal totalBigDecimal = new BigDecimal( total );
			         double lastTotal= totalBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
		     
		              
		         	 TReceive receive=new TReceive();
	                  receive.setInvestid(investId);
	                 
	                  receive.setBackflag(0); //还款状态
	                  receive.setBacksort(1);  //还款第几期
	                  receive.setLongtime(1);  //还款总期数
	                  receive.setBackmoney((float)lastTotal);
	                  receive.setBacktime(firstTime);
	                  receive.setLastflag(true);
	             
	                  
	                  receiveMapper.insertSelective(receive);
	                 
		              
		           // System.out.println("一次性还本付息,还款时间:"+backTime+"付息金额:"+lastTotal);
		              System.out.println("一次性还本付息,起息时间:"+sdf.format(beginTime)+"__还款时间:"+sdf.format(firstTime)+"__总共天数:"+days+"__本息金额:"+lastTotal);
				break;
			}
			

		default:
			break;
		}
		 
		 
		 
	 
}
	
	
     
	public List<TRzbank> getBankByUserId(Integer userId) {
		 
		       TRzbankExample example=new TRzbankExample();
		       TRzbankExample.Criteria criteria=example.createCriteria();
		       criteria.andRongziidEqualTo(userId);
		       
		       return  rzbankMapper.selectByExample(example);
		
	}

	/*  分页查询融资项目*/
	public PageBean<InvestVo> getInvestListPages(PageVo pageVo) {
		//给前台返回所有 分页数据
		PageBean<InvestVo> pageBean=new PageBean<InvestVo>();
		
		List<TInvests>  invests=new ArrayList<TInvests>();
		
		List<InvestVo>  vos=new ArrayList<InvestVo>();
		
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 //执行的sql语句
		String sqlString="";
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			
			 if (pageVo.conditions.get("name")!=null)
			 {
				 sqlString=sqlString+" and  name like  '%"+pageVo.conditions.get("name")+"%'";
				 
				 conditions.put("name", pageVo.conditions.get("name"));
				 
			 }
			 
			 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
			 {
				 
				 sqlString=sqlString+" and  contractTime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
				 
				 conditions.put("beginHT", pageVo.conditions.get("beginHT"));
				 
				// System.out.println("开始时间:"+pageVo.conditions.get("beginHT"));
			 }
			 
			 
			 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
			 {
				 sqlString=sqlString+" and  contractTime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
				 
				 conditions.put("endHT", pageVo.conditions.get("endHT"));
				 
			 }
 
		}
		
 
		  //统计 记录数量
			 int  count=workMapper.getCountOfInvests(sqlString);
			
			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  
			 sqlString=sqlString+" order by contractTime desc   limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 invests=workMapper.getInvests(sqlString);

	        if (invests!=null)
	        {
	        	
	        	if (invests.size()>0)
	        	{
	        		
	        		for (int i=0;i<invests.size();i++)
	        		{
	        			 InvestVo vo=new InvestVo();
	        			 TInvests invest=invests.get(i);
	        			// invest.setContracttime(format.format(invest.getContracttime()));
	        			 vo.setInvest(invest);
	        			 TRongzi rongzi=rongziMapper.selectByPrimaryKey(invest.getRongziid());
	        			 vo.setRzName(rongzi.getName());
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
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
	}

	
	// 删除某个 项目的所有还款计划表
	public void deleteReceiveByInvestId(int investId)
	{
		
		  TReceiveExample example=new TReceiveExample();
		  TReceiveExample.Criteria criteria=example.createCriteria();
		  criteria.andInvestidEqualTo(investId);
		  receiveMapper.deleteByExample(example);
 
		
	}
	
	
	 //删除  
	public void deleteInvestById(Integer id,AjaxMsg msg) {
		 
		    //先判断 合同里也没有 ，  财务单据bill和 还款计划里不用判断，因为删除合同的时候会判断
		
		   if (contractService.checkInvestHasContracts(id))
		   {
			   msg.setMsg("hasContract");
			   
			   return;
			   
		   }
 
		   TInvests invest=investsMapper.selectByPrimaryKey(id);
		 
		    //判断融资项目标志是否变过了（不去查收款计划表是否有）
		   if(invest.getFlag()!=0)
		   {
	           msg.setMsg("hasChange");
			   return;
		   }
		   
	     if (investsMapper.deleteByPrimaryKey(id)>0)
	     {
	    	  //如果允许删除的话  还要删除收款计划表
			   deleteReceiveByInvestId(id);
			   
			   //还要往 资金池里 减钱 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目加 7 财务删除理财单据减
			   fundPoolService.addItem(0, 6, invest.getRzmoney(), "产品经理删除融资项目");
	    	  msg.setMsg("deleteOK");
	     }
	     else {
			msg.setMsg("deleteFailed");
		}
		
	}
         //更新
	public void updateInvestAction(TInvests invest, AjaxMsg msg)  
	{
		 try {
			 
			  if (investsMapper.updateByPrimaryKeySelective(invest)>0)
			     {
			    	  msg.setMsg("updateOK");
			     }
			     else {
					msg.setMsg("updateFailed");
				}
			  
			  
			
		} 
		 
		catch(DataIntegrityViolationException da)
		{
			msg.setMsg("updateFailed");
			//System.out.println("数字月结了");
		}
		 catch (Exception e) {
		 
			 msg.setMsg("updateFailed");
		}
		 
	   
	}

	public TInvests getInvestById(Integer id) {
	 
		   return  investsMapper.selectByPrimaryKey(id);
	}

	
	//给 编辑页面用， 由于用到了日期控件，所以需要 把字符串返回到前台 否则比较麻烦
	public InvestVo getInvestVoById(Integer id) {
		 
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   
		   DecimalFormat df1 = new DecimalFormat("#0");
		 
		   TInvests invest=investsMapper.selectByPrimaryKey(id);
		   
		   InvestVo vo=new InvestVo();
		   
		   vo.setInvest(invest);
		   
		    if (invest.getContracttime()!=null)
		    {
		    	 String htTime=format.format(invest.getContracttime());
		    	  vo.setHtTime(htTime);
		    }
		    if (invest.getLastbacktime()!=null)
		    {
		    	 String lastTime=format.format(invest.getLastbacktime());
		    	  vo.setLastTime(lastTime);
		    }
		    
		    if (invest.getRzmoney()!=null)
		    {
		    	  String money=df1.format(invest.getRzmoney());
		    	  
		    	  vo.setRzMoney(money);
		    }
		    
		    if (invest.getNeedmoney()!=null)
		    {
		    	  String needMoney=df1.format(invest.getNeedmoney());
		    	  
		    	vo.setNeedMoney(needMoney);
		    }
		  
		  
		    
		   
		   
		   return vo;
	}
	
	
	//融资项目标志  所有0，未满标  1 ，满标  2 ，还款中 3 ，还款完毕 4，异常 5，，

	public List<TInvests> getInvests(int i) {
		 
		 List<TInvests>  invests=new ArrayList<TInvests>();
		TInvestsExample example=new TInvestsExample();
		TInvestsExample.Criteria criteria=example.createCriteria();
		switch (i) {
		case 0:
		{
			invests=   investsMapper.selectByExample(null);
			break;
		}
			
		case 1:
		{
			criteria.andFlagEqualTo(  1);
			invests=   investsMapper.selectByExample(example);
			break;
		}
		case 2:
		{
			criteria.andFlagEqualTo(  2);
			invests=   investsMapper.selectByExample(example);
			break;
		}
		case 3:
		{
			criteria.andFlagEqualTo( 3);
			invests=   investsMapper.selectByExample(example);
			break;
		}
		case 4:
		{
			criteria.andFlagEqualTo(4);
			invests=   investsMapper.selectByExample(example);
			break;
		}
		case 5:
		{
			criteria.andFlagEqualTo(5);
			invests=   investsMapper.selectByExample(example);
			break;
		}

		default:
			invests=   investsMapper.selectByExample(null);
			break;
		}
		 
		
		return invests;
	}

	
	 
	/**
	 * 
	 * 功能说明：根据合同 查询 对应的融资客户 对应的所有 未满标的项目  
	 * 创建人：张木生 330140511@qq.com   
	 * 创建时间：2015年11月16日/上午11:50:22 
	 * @param id   合同编号
	 * @param i    /融资项目标志  默认0，未满标  1 ，满标  2 ，还款中 3 ，还款完毕 4，异常 5，',
	 * @return
	 */
	public List<TInvests> getInvestsByContrantId(Integer id, int i) {
		
		   TContract contract=contractService.getContractById(id);
		   
		   TRongzi rz=rongziMapper.selectByPrimaryKey(contract.getRongziid());
		   
		    List<TInvests> invests=new ArrayList<TInvests>();
		    
		     TInvestsExample example=new TInvestsExample();
		   //  TInvestsExample.Criteria criteria=example.createCriteria();
		     
		    //  criteria.andRongziidEqualTo(rz.getId());
			//  criteria.andFlagEqualTo( i);
		      example.setOrderByClause(" CONVERT(   name using gbk )  ");
		      
		     
		    
		  
		      
		      invests=investsMapper.selectByExample(example);
		     
		      
		 
		return invests;
	}

	
	//根据  融资ID 查找 项目 主要用于删除融资客户的时候判断
	public boolean checkHasRongzi(Integer rongziId) {
		 
		    List<TInvests> invests=new ArrayList<TInvests>();
		    
		     TInvestsExample example=new TInvestsExample();
		     TInvestsExample.Criteria criteria=example.createCriteria();
		     
		      criteria.andRongziidEqualTo(rongziId);
		    invests=investsMapper.selectByExample(example);
		    
		    if (invests!=null)
		    {
		    	 if (invests.size()>0)
		    	 {
		    		 return true;
		    	 }
		    }
 
		      return false;
	}

	 //获取 待收款列表
	public PageBean<ReceiveVo> getReceiveListPages(PageVo pageVo) {
		 
		//给前台返回所有 分页数据
		PageBean<ReceiveVo> pageBean=new PageBean<ReceiveVo>();
		
	//	List<TReceive>  receives=new ArrayList<TReceive>();
		
		List<ReceiveVo>  vos=new ArrayList<ReceiveVo>();
		
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 //执行的sql语句
		String sqlString="  ";
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			  //理财项目
			 if (pageVo.conditions.get("investname")!=null && pageVo.conditions.get("investname")!="")
			 {
				 sqlString=sqlString+" and  i.name like  '%"+pageVo.conditions.get("investname")+"%'";
				 
				 conditions.put("investname", pageVo.conditions.get("investname"));
				 
			 }
			  //融资人姓名
			 if (pageVo.conditions.get("rzname")!=null && pageVo.conditions.get("rzname")!="")
			 {
				 sqlString=sqlString+" and  rz.name like  '%"+pageVo.conditions.get("rzname")+"%'";
				 
				 conditions.put("rzname", pageVo.conditions.get("rzname"));
				 
			 }
			 
			 //启始还款时间
			 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
			 {
				 
				 sqlString=sqlString+" and  r.backtime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
				 
				 conditions.put("beginHT", pageVo.conditions.get("beginHT"));
				 
				// System.out.println("开始时间:"+pageVo.conditions.get("beginHT"));
			 }
			 
			 //截止还款时间
			 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
			 {
				 sqlString=sqlString+" and  r.backtime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
				 
				 conditions.put("endHT", pageVo.conditions.get("endHT"));
				 
			 }
			  //还款状态
			 if ( pageVo.conditions.get("backflag")!=null && pageVo.conditions.get("backflag")!="")
			 {
		         String   backflag=(String)pageVo.conditions.get("backflag");
				   
				  if (!"99".equals(backflag))
				  {	 sqlString=sqlString+" and  r.backflag ="+backflag+"";
				      conditions.put("backflag", pageVo.conditions.get("backflag"));
				  }
			 }
		}
		
		  //统计 记录数量
			 int  count=workMapper.getCountOfReceiveVo(sqlString);
			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 sqlString=sqlString+"   limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			// System.out.println("执行的语句:"+sqlString);
			 vos=workMapper.getReceiveVos(sqlString);
 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 pageBean.setPageDatas(vos);
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
	}

	 /**
	  * 
	  *@返回值:TReceive
	  *@时间: 2016年3月21日下午3:11:51
	  *@作者:张木生 330140511@qq.com
	  *@说明: 获取单个还款计划 用来更新
	  *@参数:@param id
	  *@参数:@return
	  */
	public TReceive getReceiveById(int id) {
		 
		
		return receiveMapper.selectByPrimaryKey(id);
	}

	 //登记还款
	public void updateReceiveAction(TReceive receive, AjaxMsg msg,String username) {
	       
		 TReceive receiveDB=receiveMapper.selectByPrimaryKey(receive.getId());
	        //如果 查到数据库中 计划表的状态是1 则 退出
		 if (receiveDB.getBackflag()==1 )
		 {
			 
			 msg.setMsg("hasBack");
			 return;
		 }
		 
           // receive.setRealtime(new Date());
		 if (receiveMapper.updateByPrimaryKeySelective(receive)>0)
		 {
			   TInvests invest=  getInvestById(receive.getInvestid());
			  if (receive.getLastflag())
			  {
				  //如果是最后一期 要把 融资项目状态 改为 已还款状态4
				   invest.setFlag(4);
				   investsMapper.updateByPrimaryKeySelective(invest);
			  }
			  else
			  {
				   //把状态改为3 还款中，这时候 融资项目将无法删除
				   invest.setFlag(3);
				   investsMapper.updateByPrimaryKeySelective(invest);
			  }
			  //
			  //如果把还款计划改为1 则表示回款正常，所以要更新资金池 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减
			  if (receive.getBackflag()==1)
			  {
				  fundPoolService.addItem(0, 1, receive.getBackmoney(), username);
			  }
			
			 
			 msg.setMsg("updateOK");
		 }
		 else{
			 msg.setMsg("updateError");
		 }
		
	}

 
 
	
 
 

}
