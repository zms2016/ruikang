package com.zms.j2se;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestMain {

	public static void main(String[] args) throws ParseException {
 
		TestMain main=new TestMain();
	       main.getOrders(3, 0.14d,"2014-2-28",10000d,0);
	}

	/**
	 * 
	 * 功能说明：根据 投资金额 年收益率 投资期限 还款类型 得到还款计划
	 * 创建人：@author 330140511@qq.com   
	 * 创建时间：2015年10月30日/上午9:39:08 
	 * @param months  投资期限 （投几个月）
	 * @param apr       年收益率  0.14 0.12
	 * @param dateStr      开始计息时间
	 * @param money      投资额
	 */
	 public void getOrders(int months,Double apr,String  dateStr,Double money,int type) throws ParseException
	 {
 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = sdf.parse(dateStr);
		 
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 
		// DecimalFormat moneyFormat = new DecimalFormat("#.00");
		 
		 switch (type) {
		 //先息后本
		case 0:
		{
			//计算总利息
		 
			 BigDecimal totalBigDecimal = new BigDecimal(((money*apr)/12)*months);
	         double totalInterest= totalBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
	         System.out.println("利息总和"+totalInterest);
	         
			 //因为 前面每个月 付息 都四舍五入，导致总共利息 会超过  totalBigDecimal, 所以最后一个月的利息 是 总利息--前面N个月的利息总和(nowInterest)
			double  nowInterest=0.00d; 
		 
			  
			for ( int i=1; i<=months;i++)
			  {
				   //最后一个月
				   if (i==months)
				   {
					   
					    c.add(Calendar.MONTH, 1);//获取下个月月份
			            String  backTime =sdf.format(c.getTime());
			            //最后一期的利息为:
			            double lastInterest=totalInterest-nowInterest;
			            System.out.println("最后一期还款,还款时间:"+backTime+"付息金额:"+(lastInterest+money));
				   }
				   //前面先付利息
				   else {
	 
					     BigDecimal bg = new BigDecimal((money*apr)/12);
				         double moneys= bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	
				         
				         //累计 利息 ， 最后一个月 用总利息减去 累计利息， 不需要计算利息
				         nowInterest=nowInterest+moneys;
				         
					    c.add(Calendar.MONTH, 1);//获取下个月月份
			            String  backTime =sdf.format(c.getTime());
			            System.out.println("第"+i+"次还款时间:"+backTime+"付息金额:"+moneys);
			            
					
				}
				  
			  }
			
			break;
		}
		 //等额本息
			case 1:
			{
				
				break;
			}
			//还本付息
			case 2:
			{
				
				break;
			}
			

		default:
			break;
		}
		 
		 
		 
	 
}
}
