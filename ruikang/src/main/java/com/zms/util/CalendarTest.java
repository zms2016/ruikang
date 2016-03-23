package com.zms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarTest {

	public static void main(String[] args) throws ParseException {
 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
	    //起息时间
	   String  dateBeginStr="2016-1-1";
	    //第一次还款时间
	   String  dateFirstStr="2016-3-5";
	    
	    Date beginDate = sdf.parse(dateBeginStr);
	    Date firstDate = sdf.parse(dateFirstStr);
	     getDaysOfBill(beginDate, firstDate);
 
	}
 
	/**
	 * 
	 * 功能说明：计算 气息日期和 第一次还款日期之间的天数  每个月按30天算！！！！   调用前请保证第一次支付日期晚于起息日期
	 * 创建人：张木生   
	 * 创建时间：2015年11月30日/下午3:52:24 
	 * @param beginDate  起息日期
	 * @param firstDate  第一次支付日期
	 * @return
	 */
	public static int getDaysOfBill(Date beginDate,Date firstDate )
	{
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar beginC=Calendar.getInstance();
		Calendar firstC=Calendar.getInstance();
		 //起息日期
		beginC.setTime(beginDate);
		 //第一次还款日期
		firstC.setTime(firstDate);
		 
		int totalDays=0;
 
		  
		  //如果是 同一个月 就直接相减
		   if ( firstC.get(Calendar.YEAR)==beginC.get(Calendar.YEAR) && firstC.get(Calendar.MONTH)==beginC.get(Calendar.MONTH) )
		   {
			   totalDays= firstC.get(Calendar.DAY_OF_MONTH)-beginC.get(Calendar.DAY_OF_MONTH)+1;
			   System.out.println("同年，同月 计息天数:"+totalDays);
		   }
		    //出现跨年或者跨月情况 那就分3部分： 1 起息时间当月剩余的天数  2 （之间相差的月份-1）*30天  3 第一次还款时间在当月的天数
		   else{
			   //获取起息时间本月的剩余天数 ,默认为1天， 如果是30或者31号，则剩余天数都为1天， 其他的都用 30去减，哪怕是2月28号起息，当月剩余天数仍然是3天
				 int nowDays=1;
			
				 int dayOfBeginTime=beginC.get(Calendar.DAY_OF_MONTH);
				 
				  if (dayOfBeginTime>=30)
				  {
					 nowDays=1;  
				  }
				  else{
					  nowDays=30-dayOfBeginTime+1;
				  }
				  
				  //获取 第一次付款时间 在该月的天数，如果还款日期是 31号，则只算30天利息
				  int firstDays=1;
				  int dayOfFirstTime=firstC.get(Calendar.DAY_OF_MONTH);
				 
				  if(dayOfFirstTime>30)
				  {
					 firstDays=30;
				  }else {
					firstDays=dayOfFirstTime;
				}
				  
	
			       //计算2个日期之间的月份 ，需要考虑跨年的问题
				 // int months= (firstC.get(Calendar.YEAR)-beginC.get(Calendar.YEAR)-1)*12 + Math.abs( firstC.get(Calendar.MONTH)-beginC.get(Calendar.MONTH)) ;
				    
				    int months=compareDate(sdf.format(beginDate),sdf.format(firstDate),1);
				  
				  totalDays=nowDays+firstDays+(months-1)*30;
				 
				  
		           //返回本月剩余天数  +  相差的月份数*乘以12 得到2个时间的相差天数
			   
				  System.out.println("跨年或者跨月 计息天数:"+totalDays);
		   }
		 
		  
		return totalDays;
		
	}
	
	
	/**
	 * 功能说明：计算2个时间之间 月份数 跨年也支持，参数顺序无所谓
	 * 创建人：张木生 330140511@qq.com  
	 * 创建时间：2015年12月1日/上午10:04:25
    * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式   
    * @param date2 被比较的时间  为空(null)则为当前时间   
    * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年   
    * @return   
    */  
   public static int compareDate(String date1,String date2,int stype){   
       int n = 0;   
          
       String[] u = {"天","月","年"};   
       String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";   
          
       date2 = date2==null?CalendarTest.getCurrentDate():date2;   
          
       DateFormat df = new SimpleDateFormat(formatStyle);   
       Calendar c1 = Calendar.getInstance();   
       Calendar c2 = Calendar.getInstance();   
       try {   
           c1.setTime(df.parse(date1));   
           c2.setTime(df.parse(date2));   
       } catch (Exception e3) {   
           System.out.println("参数格式错误");   
       }   
       while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果   
           n++;   
           if(stype==1){   
               c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1   
           }   
           else{   
               c1.add(Calendar.DATE, 1);           // 比较天数，日期+1   
           }   
       }   
          
       n = n-1;   
          
       if(stype==2){   
           n = (int)n/365;   
       }      
            
       return n;   
   }   
      
   /**   
    * 得到当前日期   
    * @return   
    */  
   public static String getCurrentDate() {   
       Calendar c = Calendar.getInstance();   
       Date date = c.getTime();   
       SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");   
       return simple.format(date);   

   }   
   
   
}  

	
	
 