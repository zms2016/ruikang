package com.zms.hengjinsuo.rightaction.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TRightActions;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRightTypesExample;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRightsExample;
import com.zms.hengjinsuo.dao.TRightActionsMapper;
import com.zms.hengjinsuo.dao.TRightTypesMapper;
import com.zms.hengjinsuo.dao.TRightsMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.RightActionVo;
import com.zms.hengjinsuo.vo.SqlParams;


@Service
public class RightActionService {
	
	private static Logger log = Logger.getLogger(RightActionService.class);
	
	@Resource
	private TRightTypesMapper rightTypesMapper;
	
	@Resource
	private TRightsMapper  rightsMapper;
	
	@Resource
	private  TRightActionsMapper rightActionsMapper;
	
	@Resource
	private WorkMapper workMapper;
	 
	 //获取 所有 一级 类型列表   
	public   List<TRightTypes> getFirstMenus()
		
	{
	     	TRightTypesExample example=new TRightTypesExample();
		    TRightTypesExample.Criteria criteria=example.createCriteria();
		    criteria.andIsuseEqualTo(true);
		 	example.setOrderByClause(" sortid");
		     return rightTypesMapper.selectByExample(example);
	}
	

	 //获取 所有2级 类型列表   
	public   List<TRights> getSecMenus(int  partentId)
		
	{
	       	TRightsExample example=new TRightsExample();
	       	TRightsExample.Criteria criteria=example.createCriteria();
	       	criteria.andTypeidEqualTo(partentId);
	       	criteria.andIsuseEqualTo(true);
	         
	       	example.setOrderByClause(" sortid");
	       
		
		     return rightsMapper.selectByExample(example);
	}
	
	
	// 根据查询条件 获取   权限路径列表  支持分页
			public  PageBean<RightActionVo>   getRightActionPage(SqlParams sqlParams)
			{
				   
				    PageBean<RightActionVo> pageBean=new PageBean<RightActionVo>();
				    
				    List<TRightActions>  rightActions=new ArrayList<TRightActions>();
				    
				    //把查询条件 带回给前台
					 Map<String,Object>   searchSql=new HashMap<String,Object>();
		 
				    String sqlString=" where  1= 1 ";
				    
				    //根据前端 传来的查询条件 拼接sql
				      
				    //是否需要查询  路径或者 备述
					if(  ! StringUtils.isBlank(sqlParams.getFiletitle() ))
							{
						           sqlString=sqlString+" and   (    action like '%"+sqlParams.getFiletitle()+"%'    or    description like  '%"+sqlParams.getFiletitle()+"%'  ) ";
						           
						           searchSql.put("filetitle", sqlParams.getFiletitle());
							}
				 
					 //是否需要过滤 菜单类型
					if( sqlParams.getTypeid()!=null)
					{
						 if (sqlParams.getTypeid()>0)
						 {
				           sqlString=sqlString+" and rightId="+sqlParams.getTypeid();
				          
				           searchSql.put("firstMenuId", sqlParams.getParentId());
				           
				           searchSql.put("secMenuId", sqlParams.getTypeid());
				           
				           
						 }
					}
					
					
				 
					
					  //统计 记录数量
				    int  count=workMapper.getCountOfRightActionByParams(sqlString);
			 
					  //查询 本次查询的 记录集 支持分页 
				   
				    	 sqlString=sqlString+"   limit " +((sqlParams.getCurrPage()-1)*sqlParams.getPageSize())+"," + sqlParams.getPageSize() ;
				    	 
				    	 
				    	 rightActions=workMapper.getRightActionsByParams(sqlString);
				   
				    	 
				    
				    	 pageBean.setCurrPage(sqlParams.getCurrPage());
						 pageBean.setPageSize(sqlParams.getPageSize());
						  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
						 pageBean.setTotalRecords(count);
						
					     //转换一下，因为需要给列表 显示  “所属类别"
						 List<RightActionVo>  rightActionVos=new ArrayList<RightActionVo>();
						 
						  
						 
							  for(int i=0; i<rightActions.size();i++)
						  {
							/*  log.debug("第"+(i+1)+"条记录");*/
								
								  RightActionVo vo=new RightActionVo();
					 
								   TRightActions rActions=rightActions.get(i);
								  
								    vo.setRightActions(rActions);
								    
								    
								    int rightId=rightActions.get(i).getRightid();
								    
								    TRights right=rightsMapper.selectByPrimaryKey(rightId);
				 
							        vo.setRights(right);
				 
							        rightActionVos.add(vo);
						  }
							  
						 pageBean.setPageDatas(rightActionVos);
						 
						
					
			 
						  pageBean.setConditions(searchSql);
				  
				  return pageBean;
		 
			}
			
			
			
			
			// 根据 action id 获取  rightactionvo 用来 填充 “编辑 action" 页面 
		 
				public RightActionVo getRightActionVoById(Integer id) throws Exception
				{
					RightActionVo vo=new RightActionVo();
					
					TRightActions rightAction=rightActionsMapper.selectByPrimaryKey(id);
					
					TRights  right=rightsMapper.selectByPrimaryKey(rightAction.getRightid());
					
					
					vo.setRightActions(rightAction);
					vo.setRights(right);
					
					
					return vo;
					
					
				}
	
				 /* 修改*/
				public void updateRightActionAction(TRightActions rightAction,AjaxMsg msg) throws Exception
				
				{
					       if ( rightActionsMapper.updateByPrimaryKeySelective(rightAction) >0 )
					       {
					    	   
					    	   msg.setMsg("updateOK");
					       }
					       else {
							msg.setMsg("updateFailed");
						}
					
					
				}
	 

			public void deleteById(Integer id, AjaxMsg msg) {
				 
				//删除条目
		 
					  if ( rightActionsMapper.deleteByPrimaryKey(id) >0)
					  {
						  msg.setMsg("deleteok");
					  }
					  else {
						 msg.setMsg("deletefailed");
					}
				 
				
			}


			public TRightActions addRightAction(int rightId, String action, String description) {
				
				    TRightActions  rightActions=new TRightActions();
				    
				    rightActions.setRightid(rightId);
				    rightActions.setAction(action);
				    rightActions.setDescription(description);
				
				     int rigId=rightActionsMapper.insertSelective(rightActions);
				     log.debug("插入数据库返回的id："+rigId);
				     
				     rightActions.setId(rigId);
				
				 
				return rightActions;
			}
	
}
