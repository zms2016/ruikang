package com.zms.hengjinsuo.bill.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TBillMapper;
import com.zms.hengjinsuo.dao.TContractMapper;
import com.zms.hengjinsuo.dao.TFundpoolMapper;
import com.zms.hengjinsuo.dao.TInvestsMapper;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.TScheduleMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.fundpool.services.FundPoolService;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.schedule.services.ScheduleService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.BillInfoVo;
import com.zms.hengjinsuo.vo.BillVo;
import com.zms.hengjinsuo.vo.ContractBillVo;
import com.zms.hengjinsuo.vo.ExcelBillVo;
import com.zms.hengjinsuo.vo.ExcelContractVo;
import com.zms.hengjinsuo.vo.MoneyToolInfo;
import com.zms.hengjinsuo.vo.MySchedule;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.util.CalendarTest;


@Service
public class BillService {

	@Autowired
	private TContractMapper contractMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private RongziService rongziService;
	
	@Autowired
	private  InvestService investService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private VipService vipService;
	
	
	@Autowired
	private RzBankService  rzBankService;
	
	@Autowired
	private TRzbankMapper rzbankMapper;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private TBillMapper billMapper;
	
	@Autowired
	private TScheduleMapper scheduleMapper;
	
	@Autowired
	private TInvestsMapper investsMapper;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private LogService logService;
	
   @Autowired
   private  FundPoolService fundPoolService;
	
	private static Logger log = Logger.getLogger(BillService.class);

	
	  //billFlag=1 表示 是虚签
	public void addBillAction(TBill bill, Integer billFlag,AjaxMsg msg,HttpServletRequest request) throws ParseException {
		
		    //1 判断一下 起息日期是否小于 下一月还款日期
		             //起息日期
		          Date beginTime=bill.getBegintime();
		          // 第一次还款时间
		          Date firstTime=bill.getFirsttime();      
		          if (! beginTime.before(firstTime))
		          {
		        	   //起息日期 必须大于第一次还款日期
		        	  msg.setMsg("timeError");
		        	  return;
		          }
 
	            //是不是续签
		        if (billFlag==1)
		        {
		        	bill.setIsnext(true);
		        }
		    
		        //插入bill
		        int rzBankId=bill.getRzbankid();
		        TRzbank bank=rzBankService.getBankById(rzBankId);
		        
		         //把融资客户ID 插入到 bill表
		        bill.setRongziid(bank.getRongziid());
		        
		        int insertFlag=billMapper.insertSelective(bill);
		         //获取 新增加记录的 Id
		        int billId=bill.getId();
		        
		   if (insertFlag>0)
		   { 
			      //生成 还款计划表 	 public void createOrders(int months,Double apr,String  dateStr,Double money,int type,int contractId) throws ParseException
			          int months=bill.getLongtime();
			          double apr=Double.parseDouble(String.valueOf(bill.getApr()));
 
			          double money=Double.parseDouble(String.valueOf(bill.getMemony()));
			         
			           //把合同数据全部放到 计划表中 免得关联那么多表
			          TContract contract=contractMapper.selectByPrimaryKey(bill.getContractid());
			            
			           //通过融资项目 获取还款类型
			         TInvests invest=investService.getInvestById(bill.getInvestid());
			          
			       //   createOrders(months,apr  , beginTime  , firstTime, money,invest.getBacktype(), contract,billId);
			          //2015-12-2 把还款类型放到财务录入的时候选择，不使用项目中的那个还款方式！  
			         createOrders(months,apr  , beginTime  , firstTime, money,bill.getBacktype(), contract,billId);
			         
			             //把融资项目 余额改小
			           if (invest.getNeedmoney()!=null)
			           {
			        	    invest.setNeedmoney(invest.getNeedmoney()-bill.getMemony());
					          investsMapper.updateByPrimaryKeySelective(invest);
			           }
			      
			          
			            //续签的 就改为5 否则就改为3
			          if (billFlag==1)
			          {
			        	  TContract contractOrg=contractMapper.selectByPrimaryKey(bill.getContractid());
			        	     //签约时间
			        	  contractOrg.setMemony(bill.getMemony());
			        	  contractOrg.setPutcontracttime(bill.getContracttime());
			        	     //合同标识
			        	  contractOrg.setFlag( 5);
			        	    //合同期限
			        	  contractOrg.setLongtime(bill.getLongtime());
			        	  
			        	   // 防止 财务 修改了 项目 和 银行账号， 所以 把 合同里的 也修改一下
			        	  contractOrg.setInvestid(bill.getInvestid());
			        	  contractOrg.setRzbankid(bill.getRzbankid());
			        	  
			        	  
			        	  contractMapper.updateByPrimaryKeySelective(contractOrg);
			          } else {
			        	  
			        	  TContract contractOrg=contractMapper.selectByPrimaryKey(bill.getContractid());
			        	  contractOrg.setMemony(bill.getMemony());
			        	  contractOrg.setPutcontracttime(bill.getContracttime());
			        	  contractOrg.setFlag(  3);
			        	  contractOrg.setLongtime(bill.getLongtime() );
			        	  
			        	// 防止 财务 修改了 项目 和 银行账号， 所以 把 合同里的 也修改一下
			        	  contractOrg.setInvestid(bill.getInvestid());
			        	  contractOrg.setRzbankid(bill.getRzbankid());
			        	  
			        	  
			        	  contractMapper.updateByPrimaryKeySelective(contractOrg);
						
					}
			          
			         //增加资金池
			     /*     *@参数:@param flow  资金流向 0 流入 1 流出
			          *@参数:@param flowType 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减*/
			          fundPoolService.addItem(0, 4, bill.getMemony(), (String)request.getSession().getAttribute("username")); 
			          
			          logService.doLog((int)request.getSession().getAttribute("userid"), "录入财务单据,合同编号为:"+bill.getContractid()+"成功! 返回的billId为:"+billId, request.getRemoteAddr());
			          
			          msg.setMsg("insertOK");
		   }
		   else {
			   logService.doLog((int)request.getSession().getAttribute("userid"), "录入 财务单单据，合同编号为:"+bill.getContractid()+"失败", request.getRemoteAddr());
			   
			   msg.setMsg("insertFailed");
		}
		  
	 
		
	}

 
		
	
 
	
	  /**
	   * 
	   * 功能说明： 根据 投资金额 年收益率 投资期限 还款类型 得到还款计划
	   * 创建人：张木生 330140511@qq.com   
	   * 创建时间：2015年11月20日/下午4:51:53 
		 * @param months  投资期限 （投几个月）
    	 * @param apr       年收益率  0.14 0.12
	   * @param beginTime  起息日期
	   * @param firstTime  第一次还款日期
	   * @param money  合同金额
	   * @param type    还款类型 0 还本付息 
	   * @param contract  合同
	   * @param billId
	   * @throws ParseException
	   */
	 public void createOrders(int months,Double apr,Date  beginTime,Date firstTime,Double money,int type,TContract contract,int billId) throws ParseException
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
			//  System.out.println("日利息:"+dayInterestD+"月利息:"+monthInterestD);
		// DecimalFormat moneyFormat = new DecimalFormat("#.00");
		 
		 switch (type) {
		 //先息后本
		case 0:
		{
       
			 //如果投资期限是 1个月  则 一次算出利息和本金
			 if (months==1)
			 {
				//还款计划表公共部分开始
				 
				    TSchedule schedule=new TSchedule();
			         //清单
		              schedule.setBillid(billId);
		              //合同
		              schedule.setContractid(contract.getContractid());
		              schedule.setContractidint(contract.getId());
		               //项目
		              schedule.setInvestid(contract.getInvestid());
		                //融资客户
		              schedule.setRongziid(contract.getRongziid());
		                //还款银行
		              schedule.setRzbankid(contract.getRzbankid());
		              //理财经理
		              schedule.setManagerid(contract.getManagerid());
		               //理财客户
		                  schedule.setVipid(contract.getVipid());
		                 TVip vip=vipService.getVipById(contract.getVipid());
		                 schedule.setVipname(vip.getName());
		                 
		              //公共部分结束
		                 
				  //还款日期
                  schedule.setMemonytime(firstTime);
                    //还款金额  天数*日利息+本金
                  double onlyMonthMeonyD=days*dayInterestD+money;
                  BigDecimal onlyBig = new BigDecimal(  onlyMonthMeonyD );
     	         double onlyMeony= onlyBig.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
                  schedule.setMemony((float)onlyMeony);
                    //设置标志 为“最后一个月"
                  schedule.setLastflag(true);
                  
               //   System.out.println("还本付息1个月期限的,起息日期:"+sdf.format(beginTime)+"还款时间:"+sdf.format(firstTime)+"计息天数:"+days+"本息金额:"+ (float)onlyMeony);
                  //插入到还款计划表
                  scheduleMapper.insertSelective(schedule);
				 
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
									    TSchedule schedules=new TSchedule();
								         //清单
									    schedules.setBillid(billId);
							              //合同
									    schedules.setContractid(contract.getContractid());
									    schedules.setContractidint(contract.getId());
							               //项目
									    schedules.setInvestid(contract.getInvestid());
							                //融资客户
									    schedules.setRongziid(contract.getRongziid());
							                //还款银行
									    schedules.setRzbankid(contract.getRzbankid());
							              //理财经理
									    schedules.setManagerid(contract.getManagerid());
							               //理财客户
									    schedules.setVipid(contract.getVipid());
									    TVip vipTemp=vipService.getVipById(contract.getVipid());
							             schedules.setVipname(vipTemp.getName());
							              //公共部分结束
							                 
						               //如果是第一个月,不用考虑第一个月同时也是最后一个月的情况，因为前面已经判断了投资期限为一个月的情况
							             if (i==1)
							             {
							           	     //第一次的还款日期是固定的，前台传过来的第一次还款日期参数 firstTime
											 schedules.setMemonytime(firstTime);
											    //第一个月的 还款日期是  计息天数*日利息
											  float firstMonthMoney=(float)(dayInterestD*days);
											   schedules.setMemony(firstMonthMoney);
											    //因为投资期只有1个月的 已经判断过了，所以这里设置为false
											   schedules.setLastflag(false);
										//	   System.out.println("第一次还款日期:"+sdf.format(firstTime)+"起息日期:"+sdf.format(beginTime)+"__计息天数:"+days+"__还利息款:"+firstMonthMoney);
							             }
						              
									   //如果是 最后一个月
							             else  if (i==months)
									   {
									
						                 //还款金额  要加本金
							            	  BigDecimal lastB = new BigDecimal(  monthInterestD+money );
							      	         double lastMoney= lastB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
							      	         float lastMothMoney= (float) lastMoney;
						                     schedules.setMemony(lastMothMoney);
						                   //还款日期
						                     firstC.add(Calendar.MONTH, 1);
						                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
						                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
						                     int year=firstC.get(Calendar.YEAR);
						                     int month=firstC.get(Calendar.MONTH)+1;
						                     String backTimeStr=year+"-"+month+"-"+day;
						                     Date backTime=sdf.parse(backTimeStr);
						                     schedules.setMemonytime(backTime);
						                     
							                schedules.setLastflag(true);
							          //      System.out.println("第"+i+"次，也是最后一次还款时间:"+sdf.format(backTime)+"__本息还款:"+lastMothMoney);
							                
									   }
									   //不是 第一个月 ，也不是最后一个月 前面先付利息
									   else {
											  
							                 //还款金额
							            	  BigDecimal mB = new BigDecimal( monthInterestD );
							      	         double mMoney= mB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
							      	         float mothMoney= (float) mMoney;
						                     schedules.setMemony(mothMoney);
						                   //还款日期
						                     firstC.add(Calendar.MONTH, 1);
						                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
						                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
						                     int year=firstC.get(Calendar.YEAR);
						                     int month=firstC.get(Calendar.MONTH)+1;
						                     String backTimeStr=year+"-"+month+"-"+day;
						                     Date backTime=sdf.parse(backTimeStr);
						                     schedules.setMemonytime(backTime);
						                     
							                schedules.setLastflag(false);
							           //     System.out.println("第"+i+"次，还款时间:"+sdf.format(backTime)+"__还款利息:"+mothMoney);
							                
									}
									
									 scheduleMapper.insertSelective(schedules); 
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
				
				//计算总利息
		/*		 BigDecimal totalBigDecimal = new BigDecimal(((money*apr)/12)*months);
		         double totalInterest= totalBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
				 biginC.add(Calendar.MONTH, months);//获取 投资期限后 的日期 
		            String  backTime =sdf.format(biginC.getTime());
		            //最后一期的利息为:
		            double lastTotal=totalInterest+money;
		            */
				
				
				//2015-11-30修改 
				 //一次性还款付息 利息计算方法：  先计算一天的日期， 然后算  第一次还款时间和起息时间 的天数+1 ，  然后总利息就是 日利息*天数  
				  
				//  这个是按实际天数
		                  //   int days = ((int) (firstC.getTime().getTime() / 1000) - (int) (beginC.getTime().getTime() / 1000)) / 3600 / 24+1; 
				  // 每月 按30天算 放到最前面去
				         // int days=CalendarTest.getDaysOfBill(beginTime, firstTime);
		              //把double 精确到小数点后2位
		             double  total=dayInterestD*days+money;
		             BigDecimal totalBigDecimal = new BigDecimal( total );
			         double lastTotal= totalBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
		            
		            TSchedule schedule=new TSchedule();
		              //清单
		              schedule.setBillid(billId);
		              //合同
		              schedule.setContractid(contract.getContractid());
		              schedule.setContractidint(contract.getId());
		               //项目
		              schedule.setInvestid(contract.getInvestid());
		              
		                //融资客户
		              schedule.setRongziid(contract.getRongziid());
		                //还款银行
		              schedule.setRzbankid(contract.getRzbankid());
		               
		              //理财经理
		              schedule.setManagerid(contract.getManagerid());
		               //理财客户
		              schedule.setVipid(contract.getVipid());
		                 TVip vip=vipService.getVipById(contract.getVipid());
		                 schedule.setVipname(vip.getName());
		                  
		               //还款日期
		              //schedule.setMemonytime(biginC.getTime());
		                 schedule.setMemonytime(firstTime);
		               //还款金额
		              schedule.setMemony((float)lastTotal);
		              
		              schedule.setLastflag(true);
		              
		              scheduleMapper.insertSelective(schedule);
		              
		           // System.out.println("一次性还本付息,还款时间:"+backTime+"付息金额:"+lastTotal);
		              System.out.println("一次性还本付息,起息时间:"+sdf.format(beginTime)+"__还款时间:"+sdf.format(firstTime)+"__总共天数:"+days+"__本息金额:"+lastTotal);
				break;
			}
			

		default:
			break;
		}
		 
		 
		 
	 
}

    /**
     * 
     * 功能说明：收益测算工具
     * 创建人：张木生 330140511@qq.com   
     * 创建时间：2015年12月23日/上午11:54:56 
     * @param money
     * @param type
     * @param months
     * @param apr
     * @param beginTime
     * @param firstTime
     * @return
     * @throws ParseException 
     */

	 public MoneyToolInfo getInfo(Double money,int type ,int months,Double apr,Date  beginTime,Date firstTime ) throws ParseException 
	 {
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 MoneyToolInfo info=new MoneyToolInfo();
		 
		  //还款计划
		 List<MySchedule> schedules=new ArrayList<MySchedule>();
 
		 //计算起息日期 和 第一次付息之间的天数
		 int days=CalendarTest.getDaysOfBill(beginTime, firstTime);
		 
		 //计算日利息
		 BigDecimal dayInterest = new BigDecimal( money*(apr/360))  ;
         double dayInterestD= dayInterest.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();	
		
         //计算月利息
         BigDecimal monthInterest = new BigDecimal(money*(apr/12));
         double monthInterestD= monthInterest.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
		  double aprMoney=0;
		  
		  switch (type) {
			 //先息后本
			case 0:
			{
				 //如果投资期限是 1个月  则 一次算出利息和本金
				 if (months==1)
				 {
					//还款计划表公共部分开始
					    MySchedule schedule=new MySchedule();
					  //还款日期
	                   schedule.setTimeStr(sdf.format(firstTime));
	                    //还款金额  天数*日利息+本金
	                  double onlyMonthMeonyD=days*dayInterestD+money;
	                  BigDecimal onlyBig = new BigDecimal(  onlyMonthMeonyD );
	     	         double onlyMeony= onlyBig.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
	     	       
	                 schedule.setMoney( onlyMeony);
	     	         
	                  aprMoney=aprMoney+onlyMeony;
	                  
	                  schedules.add(schedule);
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
										    MySchedule schedule=new MySchedule();
									       
								                 
							               //如果是第一个月,不用考虑第一个月同时也是最后一个月的情况，因为前面已经判断了投资期限为一个月的情况
								             if (i==1)
								             {
								           	     //第一次的还款日期是固定的，前台传过来的第一次还款日期参数 firstTime
										 
												 schedule.setTimeStr(sdf.format(firstTime));
												    //第一个月的 还款日期是  计息天数*日利息
												  double  firstMonthMoney= dayInterestD*days;
												 
												      BigDecimal lastB = new BigDecimal(  firstMonthMoney );
									      	         double lastMoneyt= lastB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
									      	         
												   schedule.setMoney(lastMoneyt);
												    //因为投资期只有1个月的 已经判断过了，所以这里设置为false
												  schedules.add(schedule);
												  
												  aprMoney=aprMoney+lastMoneyt;
								             }
							              
										   //如果是 最后一个月
								             else  if (i==months)
										   {
										
							                 //还款金额  要加本金
								            	  BigDecimal lastB = new BigDecimal(  monthInterestD+money );
								      	         double lastMoney= lastB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
								      	         
							                     schedule.setMoney(lastMoney);
							                   //还款日期
							                     firstC.add(Calendar.MONTH, 1);
							                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
							                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
							                     int year=firstC.get(Calendar.YEAR);
							                     int month=firstC.get(Calendar.MONTH)+1;
							                     String backTimeStr=year+"-"+month+"-"+day;
							                     Date backTime=sdf.parse(backTimeStr);
							                    
							                     schedule.setTimeStr(sdf.format(backTime));
							                     aprMoney=aprMoney+lastMoney-money;
							                     
								                 schedules.add(schedule);
								                
										   }
										   //不是 第一个月 ，也不是最后一个月 前面先付利息
										   else {
												  
								                 //还款金额
								            	  BigDecimal mB = new BigDecimal( monthInterestD );
								      	         double mMoney= mB.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
								      	        
							                     schedule.setMoney(mMoney);
							                  
							                   //还款日期
							                     firstC.add(Calendar.MONTH, 1);
							                      //获取 第一次还款日期的 ”日" 因为每个月的还款日都必须相同
							                     int  day=firstC.get(Calendar.DAY_OF_MONTH);
							                     int year=firstC.get(Calendar.YEAR);
							                     int month=firstC.get(Calendar.MONTH)+1;
							                     String backTimeStr=year+"-"+month+"-"+day;
							                     Date backTime=sdf.parse(backTimeStr);
							                
							                     schedule.setTimeStr(sdf.format(backTime));
							                     
							                     aprMoney=aprMoney+mMoney;
							                     
							                     schedules.add(schedule);
								                
										}
										
									 
									  }
					 
				 }
	             //结束 months<>1的
				
			        BigDecimal ddd = new BigDecimal( aprMoney );
			           aprMoney= ddd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
				 
				 
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
					     aprMoney=dayInterestD*days;
			             double  total=aprMoney+money;
			             BigDecimal totalBigDecimal = new BigDecimal( total );
				         double lastTotal= totalBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
			            
			              MySchedule schedule=new MySchedule();
			               
			                  
			               //还款日期
			              //schedule.setMemonytime(biginC.getTime());
			            
			                 schedule.setTimeStr(sdf.format(firstTime)); 
			               //还款金额
			              schedule.setMoney(lastTotal);
			              
			        
			               
			              schedules.add(schedule);
			          
			               
					break;
				}
				

			default:
				break;
			}
			 
		  info.setSchedules(schedules);
		  
		  info.setTotalApr(String.valueOf(aprMoney));
		  
		 return info;
		 
		 
		 
	 }

 
	// 财务录入单据的时候 根据选择 合同id  自动获取 合同对应 融资客户


	public ContractBillVo getDateByContractId(Integer contractid) {

		ContractBillVo vo=new ContractBillVo();
		  //合同
		TContract contract=contractService.getContractById(contractid);
		
		//很据 合同id 获取项目
		 List<TInvests> invests=new ArrayList<TInvests>();
		  //获取 合同对应的 融资客户下 所有未满标的 项目
		 invests=investService.getInvestsByContrantId(contractid, 0);
		 
		 
		  //根据合同id 获取 对应融资客户下所有 银行
		 List<TRzbank>  banks=new ArrayList<TRzbank>();
		   //
		 TRongzi rongzi=rongziService.getRongziByContractId(contractid);
		 banks=rzBankService.getBanksByRongzi(rongzi);
		 
		 
		 vo.setBanks(banks);
		 vo.setContract(contract);
         vo.setInvests(invests);		
		return vo;
	}
	
	
	
	 //统计项目当前 融资了多少钱
	public void getSumOfInvest(Integer investid, AjaxMsg msg) {

	 
		   float totalMoney=investService.getInvestById(investid).getRzmoney();
		   
		   float needMoney=0.0f;
		     if (investService.getInvestById(investid).getNeedmoney()!=null )
		     {
		    	     needMoney=investService.getInvestById(investid).getNeedmoney();
		     }
		     else {
		    	   needMoney=totalMoney;
			}
		
		   //String tString=String.valueOf(receiveMoeny-total);
		   
		   msg.setCode(1);
		   msg.setMsg("总融资额:"+totalMoney+"元，还需:"+ needMoney+"元");
		    
		
		
	}





       //删除合同的时候 判断  
	public boolean checkHasContract(Integer contractId) {
		 
		List<TBill> bills=new ArrayList<TBill>();
		
		TBillExample example=new TBillExample();
	    TBillExample.Criteria criteria=example.createCriteria();
	    criteria.andContractidEqualTo(contractId);
	    
	    bills=billMapper.selectByExample(example);
		
	    if (bills!=null)
	    {
	    	
	    	 if (bills.size()>0)
	    	 {
	    		 return true;
	    	 }
	    }
		
		return false;
	}






	public PageBean<BillVo> getBillVoPages(PageVo pageVo) {
        
			//给前台返回所有 分页数据
			PageBean<BillVo> pageBean=new PageBean<BillVo>();
			 //给前台返回 搜索条件
			 Map<String,Object> conditions=new HashMap<String, Object>();
			 
		 
				List<BillVo>  vos=new ArrayList<BillVo>();
			 	
			 	
			 //执行的sql语句
			String sqlString="";
			
			  //开始吧 条件里的参数 和值 取出来
			if ( pageVo.conditions!=null)
			{
				//合同编号  字符串那个
				 if (pageVo.conditions.get("contractId")!=null  && pageVo.conditions.get("contractId")!="" )
				 {
					  if ( ! pageVo.conditions.get("contractId").equals(""))
					  {
							 sqlString=sqlString+" and  c.contractId like  '%"+pageVo.conditions.get("contractId")+"%'";
							 
							 conditions.put("contractId", pageVo.conditions.get("contractId"));
					  }
				
					 
				 }
				 
				   //合同状态
				 if (pageVo.conditions.get("flag")!=null  && pageVo.conditions.get("flag")!="" )
				 {
					 int   flag=Integer.valueOf((String)pageVo.conditions.get("flag") );
					 
					  if (flag!=99)
					  {
						  sqlString=sqlString+" and  b.flag="+flag;
							 
							 conditions.put("flag", pageVo.conditions.get("flag"));
					  }
					
					 
				 }
				 
				 //合同签署时间
				 if (pageVo.conditions.get("beginHT")!=null && pageVo.conditions.get("beginHT")!="")
				 {
					 
					 sqlString=sqlString+" and  b.contractTime >= '"+(String)pageVo.getConditions().get("beginHT")+"'";
					 
					 conditions.put("beginHT", pageVo.conditions.get("beginHT"));
					 
					 
				 }
				 
				 
				 if (pageVo.conditions.get("endHT")!=null && pageVo.conditions.get("endHT")!="")
				 {
					 sqlString=sqlString+" and  b.contractTime <= '"+(String)pageVo.getConditions().get("endHT")+"'";
					 
					 conditions.put("endHT", pageVo.conditions.get("endHT"));
					 
				 }
				 
				 if (pageVo.conditions.get("investId")!=null  &&  pageVo.conditions.get("investId")!="" )
				 {
					 
					 int investId=  Integer.valueOf(   (String)pageVo.conditions.get("investId")    );
					  if (investId>0)
					  {
							 sqlString=sqlString+" and  b.investId="+investId;
							 
							 conditions.put("investId", pageVo.conditions.get("investId"));
							 
							 
							  //如果选择了项目， 则把项目的累计金额也加上去
							 
							 conditions.put("totalMemony", workMapper.getInvestSumMemonyByBill(sqlString));
							 
							 
					  }
				
					 
				 }
				 
					//理财客户
				 if (pageVo.conditions.get("vipId")!=null  && pageVo.conditions.get("vipId")!="" )
				 {
					 String vipId=(String)pageVo.conditions.get("vipId");
					        if ( !  "0".endsWith(vipId) )
					        {
					        	 sqlString=sqlString+" and  v.id ="+pageVo.conditions.get("vipId");
								 
								 conditions.put("vipId", pageVo.conditions.get("vipId"));
					        }
							
				 
				
					 
				 }
				 
				 
			/*	 if (pageVo.conditions.get("manager")!=null  )
				 {
					 
					 int managerId=  Integer.valueOf(   (String)pageVo.conditions.get("manager")    );
					  if (managerId>0)
					  {
							 sqlString=sqlString+" and  managerId="+managerId;
							 
							 conditions.put("manager", pageVo.conditions.get("manager"));
					  }
				
					 
				 }*/
				 
				 
	  
			}
			

			 
			  //统计 记录数量
				 int  count=workMapper.getCountOfBillVo(sqlString);
	 
				  
				 sqlString=sqlString+"  order by b.contractTime desc  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
				 
				 vos=workMapper.getBillVos(sqlString);

		      
			
			 
			 
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





    //删除单据
	public void deleteBillById(Integer id, AjaxMsg msg,HttpServletRequest request) {
		 
		   //还款中 的数据 不让删除！
		   //理财凭证标志 0：还未还款  1 还款中  2 还款结束  4异常
		    TBill bill=billMapper.selectByPrimaryKey(id);
		    
		     if (bill.getFlag()>0)
		     {
		    	 
		    	 msg.setMsg("isBusy");
		    	 return;
		    	 
		     }
		       //删除后 还需要把 还款计划表删除 ，把 融资项目的 余额加回去
		     if (billMapper.deleteByPrimaryKey(id)>0)
		     {
		    	   //删除 还款计划表
		    	    scheduleService.deleteByBillId(id);
		    	    
		    	    
				      //把融资项目的 余额加回去
		    	    TInvests invest=investService.getInvestById(bill.getInvestid());
		    	    invest.setNeedmoney(invest.getNeedmoney()+bill.getMemony());
		    	    investsMapper.updateByPrimaryKeySelective(invest);
		    	    
		    	     //修改合同标识为2 已领
		    	    TContract contract=contractService.getContractById(bill.getContractid());
		    	  
		    	  //  contract.setMemony(null);
		    	
		    	    
		    	      //如果是续签的话 把合同标志改为 还款完毕，否则 改为 已领取
		    	    if (bill.getIsnext())
		    	    {
		    	    	   contract.setFlag(4);
		    	    }
		    	    else {
		    	    	  contract.setPutcontracttime(null);
		    	        contract.setMemony(0.0f);
			    	    contract.setLongtime(0);
		    	    	   contract.setFlag(2);
					}
		    	 
		    	    
		    	    contractMapper.updateByPrimaryKeySelective(contract);
		    	    
		    	    // 修改 资金池
		    	/*    *@参数:@param flow  资金流向 0 流入 1 流出
		    	    *@参数:@param flowType 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减*/
		    	    fundPoolService.addItem(1, 7, bill.getMemony(), (String)request.getSession().getAttribute("username"));
		    	    
		    	    logService.doLog((int)request.getSession().getAttribute("userid"), "删除财务单据:"+id+"成功", request.getRemoteAddr());
		    	    msg.setMsg("deleteOK");
		    	    return;
		    	 
		     }
		  
		    
		     logService.doLog((int)request.getSession().getAttribute("userid"), "删除财务单据:"+id+"失败", request.getRemoteAddr());
		      msg.setMsg("deleteFailed");
		
	}






	public BillInfoVo getBillInfoVo(Integer billId) throws ServicesException {
		
		
		BillInfoVo vo=new BillInfoVo();
		
		TBill bill=billMapper.selectByPrimaryKey(billId);
		
		vo.setBill(bill);
		
		TContract contract=contractService.getContractById(bill.getContractid());
		 vo.setContract(contract);
		 
		 TUser manager=userService.findUserById(contract.getManagerid());
		 vo.setManager(manager);
		 
		 TRongzi rongzi=rongziService.getRongziById(bill.getRongziid());
		 vo.setRongzi(rongzi);
		 
		 TRzbank rzbank=rzBankService.getBankById(bill.getRzbankid());
		 vo.setRzbank(rzbank);
		 
		 TVip vip=vipService.getVipById(contract.getVipid());
		 vo.setVip(vip);
		 
		 TInvests invest=investService.getInvestById(bill.getInvestid());
		vo.setInvest(invest);
		 
		
		 List<TSchedule> schedules=new ArrayList<TSchedule>();
		 schedules=scheduleService.getSchedulesByBillId(billId);
		 vo.setSchedules(schedules);
		   
		return vo;
	}






	public List<ExcelBillVo> getBillDate(PageVo pageVo) {
		 //获取数据
 
			
			
			List<ExcelBillVo>  vos=new ArrayList<ExcelBillVo>();
			
			
			String sqlString=" select  b.id billId,c.contractid contractString,b.memony billMoney,date_format(b.contracttime,'%Y-%m-%d') as billTime ,b.longtime billLongTime,r.name  rongzi,t.name invest, u.realname managerName, v.name vipName, "
					 +" case   when  b.flag=0 then  '还未还款'   when  b.flag=1 then '还款中'  when  b.flag=2 then '还款结束'  when b.flag=4 then '异常'     END  flag  , "
                     +"  case   when  b.backType=0 then  '先息后本'   when  b.backType=1 then '等额本金'  when  b.backType=2 then '一次性还本付息'      END  backType "
					+"  from   t_bill b, t_contract c,t_rongzi r,t_invests t,t_user u,t_vip v  "
					 +"  where b.contractid=c.id and b.rongziid=r.id and b.investid=t.id  and c.managerid=u.id and c.vipid=v.id  "   ;
		
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
				  if ( ! flagId.equals("99"))
				  {
						 sqlString=sqlString+" and  b.flag =  "+ flagId;
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
 
			 
		 	 vos=workMapper.getBillDate(sqlString);
	    
 
		return vos;
 
	}
	
	
	  // 整理数据
		public List<Map<String, Object>> createExcelBillVo( List<ExcelBillVo> vos)
		
		{
			    List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("sheetName", "sheet1");
		        listmap.add(map);
		        ExcelBillVo vo=null;
		        for (int j = 0; j < vos.size(); j++) {
		            vo=vos.get(j);
		            Map<String, Object> mapValue = new HashMap<String, Object>();
		            
		            mapValue.put("billId", vo.getBillId());
		            mapValue.put("contractString", vo.getContractString());
		            mapValue.put("billMoney", vo.getBillMoney());
		            mapValue.put("billTime", vo.getBillTime());
		            mapValue.put("billLongTime", vo.getBillLongTime());
		            mapValue.put("backType", vo.getBackType());
		            mapValue.put("rongzi", vo.getRongzi());
		            mapValue.put("invest", vo.getInvest());
		            mapValue.put("managerName",vo.getManagerName() );
		            mapValue.put("vipName",vo.getVipName() );
		            mapValue.put("flag", vo.getFlag() );
		            listmap.add(mapValue);
		        }
		        return listmap;
			
			 
		}
		
 
}
