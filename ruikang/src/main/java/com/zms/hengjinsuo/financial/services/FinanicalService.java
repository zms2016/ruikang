package com.zms.hengjinsuo.financial.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TFinanical;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.dao.TFinanicalMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.SqlParams;



@Service
public class FinanicalService {

	private static Logger log = Logger.getLogger(FinanicalService.class);
	
	@Autowired
	private TFinanicalMapper finanicalMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	
	 //新增项目
	public  void addFinancialAction(TFinanical finanical,AjaxMsg msg)
	{
		 
		
		 if (  finanicalMapper.insertSelective(finanical) >0 )
		 {
			 msg.setMsg("insertOK");
		 }
		 else
		 {
			 msg.setMsg("insertFailed");
		 }
		
	}
	
	
	
	//获取查询  列表 支持分页
		public  PageBean<TFinanical>   getFinanicalPage(SqlParams sqlParams)
		{
			
			 List<TFinanical>  finanicals=new ArrayList<TFinanical>();
	 
			  //统计 记录数量
				 int  count=workMapper.getCountOfFinancialByParams(sqlParams);
		 
	        
			  //查询 本次查询的 记录集 
				 
				 finanicals=workMapper.getFinancialsByParams(sqlParams);
	 
		      log.debug("数量:"+finanicals.size());
			
		      PageBean<TFinanical> pageBean=new PageBean<TFinanical>();
			 
			 pageBean.setCurrPage(sqlParams.getCurrPage());
			 pageBean.setPageSize(sqlParams.getPageSize());
			  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
			 pageBean.setTotalRecords(count);
			 
			 pageBean.setPageDatas(finanicals);
			 
			  
			 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
			 pageBean.setParams(sqlParams.getParams());
			  
			  return pageBean;
	 
		}



		public void deleteFinancialById(int id,AjaxMsg msg) {
			
			
			  if (finanicalMapper.deleteByPrimaryKey(id)>0) 
					  {
				  
				        msg.setMsg("deleteOK");
					  }
			  else {
				  msg.setMsg("deleteFailed");
			}
		 
			
		}

 //根据ID 获取实体对象

		public TFinanical getFinancialById(Integer id) {
			 
			return finanicalMapper.selectByPrimaryKey(id);
		}


    //修改 
		public void updateFinancial(TFinanical finanical, AjaxMsg msg) {
			
			
			 finanical.setTime(new Date());
			
		       if (  finanicalMapper.updateByPrimaryKeySelective(finanical)>0 )
		       {
		    	   msg.setMsg("updateOK");
		       }
		       else {
				
		    	   msg.setMsg("updateFailed");
			}
		}
	
	
	
	
}
