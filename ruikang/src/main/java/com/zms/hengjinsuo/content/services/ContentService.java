package com.zms.hengjinsuo.content.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TContentTypes;
import com.zms.hengjinsuo.bean.TContentTypesExample;
import com.zms.hengjinsuo.bean.TContents;
import com.zms.hengjinsuo.bean.TContentsExample;
import com.zms.hengjinsuo.bean.TFinanical;
import com.zms.hengjinsuo.dao.TContentTypesMapper;
import com.zms.hengjinsuo.dao.TContentsMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ContentVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.ResultMsg;
import com.zms.hengjinsuo.vo.SqlParams;
import com.zms.util.Constants;


@Service
public class ContentService {
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private TContentTypesMapper  contentTypesMapper;
	
	@Autowired
	private TContentsMapper  contentsMapper;
	
	 //获取 所有 一级 类型列表  条件是 parentid=-1 
	public   List<TContentTypes> getContentTypes()
		
	{
		     TContentTypesExample example=new TContentTypesExample();
		     TContentTypesExample.Criteria criteria=example.createCriteria();
		     
		    
		     criteria.andParentidEqualTo(-1);
		     
		     return contentTypesMapper.selectByExample(example);
	}
	

	 //根据父类型ID，获取 类型列表   
	public   List<TContentTypes> getContentTypesByParentId(Integer partentId)
		
	{
		
		     TContentTypesExample example=new TContentTypesExample();
		     TContentTypesExample.Criteria criteria=example.createCriteria();
		     
		     criteria.andParentidEqualTo(partentId);
		     
		     return contentTypesMapper.selectByExample(example);
	}
	
	//根据id 获取 types 实体
	public    TContentTypes  getTypeInfoById(Integer id)
	
	{
		
		      
		     
		     return contentTypesMapper.selectByPrimaryKey(id);
	}
	
	
	 //保存 内容条目 
	public   void   saveContent(TContents content,AjaxMsg msg) throws Exception
		
	{
		
		     TContents contents=content;
		     content.setTime(new Date());
		        if ( contentsMapper.insertSelective(contents) >0)
		        {
		        	msg.setCode(1);
		        	msg.setMsg("insertOK");
		        }
		        else
		        {
		        	msg.setCode(0);
		        	msg.setMsg("insertFailed");
		        }
	}
	
	
	
	 //更新 内容条目 
	public   void   updateContent(TContents content,AjaxMsg msg)
		
	{
 
		     content.setTime(new Date());
		     if ( contentsMapper.updateByPrimaryKeySelective(content) >0)
		     {
		    	  msg.setCode(1);
		    	  msg.setMsg("updateOK");
		     }
		     else {
		    	 msg.setCode(0);
		    	  msg.setMsg("updateFailed");
			}
	}
	
	
	 //根据 typeid 获取 内容列表
	public    List<TContents>  getContentsByTypeId(Integer typeId)
		
	{
		
		 if (typeId==null)
		 {
			    return contentsMapper.selectByExample(null);
			 
		 }
		 else {
			    TContentsExample example=new TContentsExample();
				TContentsExample.Criteria criteria=example.createCriteria();
			     criteria.andTypeidEqualTo(typeId);
			     return contentsMapper.selectByExample(example);
		}
 
	 
	}
	
	// 根据查询条件 获取内容列表  支持分页
		public  PageBean<ContentVo>   getContentPageBean(SqlParams sqlParams)
		{
			   
			    PageBean<ContentVo> pageBean=new PageBean<ContentVo>();
			    
			    List<TContents>  contents=new ArrayList<TContents>();
			    
			    //把查询条件 带回给前台
				 Map<String,Object>   searchSql=new HashMap<String,Object>();
	 
			    String sqlString=" where  1= 1 ";
			    
			    //根据前端 传来的查询条件 拼接sql
			      
			    //是否需要查询  文章标题  filetitle
				if(  ! StringUtils.isBlank(sqlParams.getFiletitle() ))
						{
					           sqlString=sqlString+" and filetitle like '%"+sqlParams.getFiletitle()+"%' ";
					           
					           searchSql.put("filetitle", sqlParams.getFiletitle());
						}
			 
				 //是否需要过滤 文章类型
				if( sqlParams.getTypeid()!=null)
				{
					 if (sqlParams.getTypeid()>0)
					 {
			           sqlString=sqlString+" and typeId="+sqlParams.getTypeid();
			           
			             //把查询条件 type 返回给前台
			           TContentTypes type=new TContentTypes();
						  type=contentTypesMapper.selectByPrimaryKey(sqlParams.getTypeid());
						  searchSql.put("type", type);
					 }
				}
				
				
			 
				
				  //统计 记录数量
			    int  count=workMapper.getCountOfContentByParams(sqlString);
		 
				  //查询 本次查询的 记录集 支持分页 
			   
			    	 sqlString=sqlString+" order by time  desc  limit " +((sqlParams.getCurrPage()-1)*sqlParams.getPageSize())+"," + sqlParams.getPageSize() ;
			    	 
			    	 
			    	 contents=workMapper.getContentsByParams(sqlString);
			   
			    	 
			    
			    	 pageBean.setCurrPage(sqlParams.getCurrPage());
					 pageBean.setPageSize(sqlParams.getPageSize());
					  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
					 pageBean.setTotalRecords(count);
					
				     //转换一下，因为需要给列表 显示  “所属类别"
					 List<ContentVo>  contentVos=new ArrayList<ContentVo>();
					 
					  
					 
						  for(int i=0; i<contents.size();i++)
					  {
					 
						  ContentVo vo=new ContentVo();
						   vo.setContent(contents.get(i));
						   
						   TContentTypes type=contentTypesMapper.selectByPrimaryKey(contents.get(i).getTypeid());
						   vo.setTypeInfo(type);
						   
						   int  fatherId=type.getParentid();
						   TContentTypes fatherType=contentTypesMapper.selectByPrimaryKey(fatherId);
						   
						   vo.setFartherTypeInfo( fatherType);
						   
						  contentVos.add(vo);
					  }
						  
					 pageBean.setPageDatas(contentVos);
					 
					
				
		 
					  pageBean.setConditions(searchSql);
			  
			  return pageBean;
	 
		}
		
	
	
	
	
	
		//根据 id 获取 内容 
		public     TContents  getContentsById(Integer id)
		
		{
			
			 if (id==null)
			 {
				    return null;
				 
			 }
			 else {
				 
				  TContents content= contentsMapper.selectByPrimaryKey(id);
				  
				 
				     return content;
			}
	 
		 
		}
		
		

	
	
	  //把 新闻的 浏览量 加1
	public   synchronized  void addNewslReadCount(TContents  news)
	{
		   news.setReadcount(news.getReadcount()+1);
		    
		    contentsMapper.updateByPrimaryKeySelective(news);
		
	}
	
	
	
	
	//修改 显示状态 （设置是否显示)
	public  void updateFlag(Integer id,Integer flag,AjaxMsg msg)
	{
		
		TContents content=new TContents();
		content.setId(id);
		
		content.setIsshow(flag>0? true:false);
		
	  if(	contentsMapper.updateByPrimaryKeySelective(content) >0 )
	  {
		  
		  msg.setMsg("updateok");
	  }
	  else {
		msg.setMsg("noupdate");
	}
 	
	}
	
	//删除条目
	
	public  void deleteById(Integer id,AjaxMsg msg)
	{
	     // 删除老图片
					TContents contentDB=new TContents();
					 
					contentDB=getContentsById(id);
					String oldPicName = contentDB.getImagefilename();
					File oldPicFile = new File(Constants.NEWSPIC_PATH  + oldPicName);
					oldPicFile.delete();
		
		
		  if ( contentsMapper.deleteByPrimaryKey(id) >0)
		  {
			  msg.setMsg("deleteok");
		  }
		  else {
			 msg.setMsg("deletefailed");
		}
	}
	
	
}
