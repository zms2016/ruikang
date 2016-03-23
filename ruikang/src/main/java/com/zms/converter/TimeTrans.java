package com.zms.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 * 
 * 功能说明：把  字符型 转成日期型   (yyyy-mm-dd HH:mm:ss)
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月10日/下午2:43:42
 */
public class TimeTrans  implements Converter<String, Date> {
	
	private static Logger log = Logger.getLogger(TimeTrans.class); 
	
	@Override
	public Date convert(String htmlString) {
	
		SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			log.debug( "TimeTrans正在勤劳的解析 日期型字符串");
			return  simpleDateFormat.parse(htmlString);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//参数绑定失败返回 Null
		return null;
	}

}
