package com.zms.hengjinsuo.log.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TLogs;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.dao.TLogsMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.LogVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.VipVo;


@Service
public class LogService {
	
	@Autowired
	private TLogsMapper logsMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	
	 //插入日志
	public void doLog(TLogs log)
	{
		
		 logsMapper.insertSelective(log);
		
	}
	
	 //插入日志 只需要用户ID和 内容
	public  void doLog(int userId,String content,String ip)
	{
		
		TLogs log=new TLogs();
		
		log.setContent(content);
		log.setManagerid(userId);
		log.setSourceip(ip);
		
		
		logsMapper.insertSelective(log);
		
	}
	
	 //分页查询

	public PageBean<LogVo> getLogPages(PageVo pageVo) {
		 //给前台返回所有 分页数据
		
		PageBean<LogVo> pageBean=new PageBean<LogVo>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 //执行的sql语句
		String sqlString="";
		
	  
		  
		
		
	 
		
		List<LogVo>  vos=new ArrayList<LogVo>();
		
		  //开始吧 条件里的参数 和值 取出来 查询条件 操作时间  操作员  内容（模糊)
		if ( pageVo.conditions!=null)
		{
			

			 
			 if (pageVo.conditions.get("userId")!=null  && pageVo.conditions.get("userId")!="")
			 {
				 int  userId=( Integer.valueOf( (String)pageVo.conditions.get("userId")  ) );
	 
				  if (userId>0)
				  {
					 	 sqlString=sqlString+" and  l.managerid = "+userId+"";
						  //给前台返回条件
						 conditions.put("userId", pageVo.conditions.get("userId"));
				  }
				 
		
				 
				 
			 }
			 
			 
			 
			 //操作时间
			 if (pageVo.conditions.get("beginTime")!=null && pageVo.conditions.get("beginTime")!="")
			 {
				 
				 sqlString=sqlString+" and  l.modiTime >= '"+(String)pageVo.getConditions().get("beginTime")+"'";
				 
				 conditions.put("beginTime", pageVo.conditions.get("beginTime"));
				 
				 
			 }
			 
			 
			 if (pageVo.conditions.get("endTime")!=null && pageVo.conditions.get("endTime")!="")
			 {
				 sqlString=sqlString+" and  l.modiTime<= '"+(String)pageVo.getConditions().get("endTime")+"'";
				 
				 conditions.put("endTime", pageVo.conditions.get("endTime"));
				 
			 }
			 
			 
			 if (pageVo.conditions.get("content")!=null  && pageVo.conditions.get("content")!="")
			 {
				 String content=(String)pageVo.conditions.get("content");
				 
			 	 sqlString=sqlString+" and  l.content like  '%"+content+"%'";
				  //给前台返回条件
				 conditions.put("content", content);
				 
				 
			 }
			 
			 
 
		}
 
		     //统计数量
		
		   int count=workMapper.getCountOfLogVo(sqlString);
			  
			 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			 
		//	 System.out.println(sqlString);
			 vos=workMapper.getLogVos(sqlString);
	        
		     pageBean.setPageDatas(vos);
			 
		 
		 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 
		
		 
		  
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
	}

}
