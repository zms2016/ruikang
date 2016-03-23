package com.zms.hengjinsuo.menu.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRightUser;
import com.zms.hengjinsuo.bean.TRightUserExample;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRightsExample;
import com.zms.hengjinsuo.bean.TRoleRight;
import com.zms.hengjinsuo.bean.TRoleRightExample;
import com.zms.hengjinsuo.dao.TRightActionsMapper;
import com.zms.hengjinsuo.dao.TRightTypesMapper;
import com.zms.hengjinsuo.dao.TRightUserMapper;
import com.zms.hengjinsuo.dao.TRightsMapper;
import com.zms.hengjinsuo.dao.TRoleRightMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.RightVo;


@Service
@RequestMapping("/manager")
public class MenuService {

	@Autowired
	private  TRightTypesMapper rightTypesMapper;
	
	@Autowired
	private  TRightsMapper rightsMapper;
	
	@Autowired
  private TRightActionsMapper rightActionsMapper;
	
	@Autowired
	private  WorkMapper workMapper;
	
	
	@Autowired
	private TRightUserMapper rightUserMapper;
	
	@Autowired
	private  TRoleRightMapper roleRightMapper;

	
	 //获取 一级菜单列表  不需要过滤 isUser是否为1
	public PageBean<TRightTypes> getRightTypesPages(PageVo pageVo) {
		
		 //给前台返回所有 分页数据
		PageBean<TRightTypes> pageBean=new PageBean<TRightTypes>();
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
  
		}
		
	 	List<TRightTypes>  rightTypes=new ArrayList<TRightTypes>();
		 
		  //统计 记录数量
			 int  count=workMapper.getCountOfSuperMenus(sqlString);
	 
       
		  //查询 本次查询的 记录集 
	/*		  if (pageVo.currPage<1)
			  {
				  pageVo.setCurrPage(1);
			  }*/
			 
			  
			 sqlString=sqlString+"order by sortId limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 rightTypes=workMapper.getSuperMenus(sqlString);

	   
		 
		 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 
		 pageBean.setPageDatas(rightTypes);
		 
		  
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
	}

	
	  //更改一级菜单的 显示 标识

	public void updateSuperMenuFlag(Integer id, Integer flag, AjaxMsg msg) {
		 
		TRightTypes rightType=new TRightTypes();
		
		rightType.setId(id);
		
		 
		rightType.setIsuse(flag>0? true:false);
		
	  if(	rightTypesMapper.updateByPrimaryKeySelective(rightType) >0 )
	  {
		  
		  msg.setMsg("updateOK");
	  }
	  else {
		msg.setMsg("updateFailed");
	}
		
		
	}
	
	
	
	  //更改二级菜单的 显示 标识

		public void updateSecondMenuFlag(Integer id, Integer flag, AjaxMsg msg) {
			 
			TRights rights=new TRights();
			
			rights.setId(id);
			
			 
			rights.setIsuse(flag>0? true:false);
			
		  if(	rightsMapper.updateByPrimaryKeySelective(rights) >0 )
		  {
			  
			  msg.setMsg("updateOK");
		  }
		  else {
			msg.setMsg("updateFailed");
		}
			
			
		}
		
		

         //删除一级菜单
	public void deleteSuperMenuById(Integer id, AjaxMsg msg) throws Exception {
		 
         
          
		     //先判断 二级菜单 上有没有绑定到本菜单的数据， 有的话 就提示不让删除！
		    List<TRights> rights=new ArrayList<TRights>();
		    TRightsExample example=new TRightsExample();
		    TRightsExample.Criteria criteria=example.createCriteria();
		    criteria.andTypeidEqualTo(id);
		    rights=rightsMapper.selectByExample(example);
		    if (rights!=null)
		    {
		       if (rights.size()>0)
		       {
		    	  msg.setMsg("error");   
		    	  return;
		       }
		    	
		    }
		    
		   
		

		    TRightTypes rightType=new TRightTypes();
		     rightType.setId(id);
		  
			if ( rightTypesMapper.deleteByPrimaryKey(id) >0)
			{
			  msg.setMsg("deleteOK");
			}
			else {
			 msg.setMsg("deleteFailed");
			}
	  }
	
	
	
    //删除二级菜单
	public void deleteSecondMenuById(Integer id, AjaxMsg msg) throws Exception {
		 
         
/*          删除二级菜单的时候要判断2个条件：
          
             1   select * from t_role_right;  角色关联表  是否有角色绑定了该功能， (删除一级菜单的时候不需要判断)
             
             2  select * from t_right_user;  用户权限关联表  是否有用户 绑定了该功能  (删除一级菜单的时候不需要判断)
	 */
		
		         //判断 角色 权限表
		    List<TRoleRight> roleRights=new ArrayList<TRoleRight>();
		    TRoleRightExample example=new TRoleRightExample();
		    TRoleRightExample.Criteria criteria=example.createCriteria();
		    criteria.andRightidEqualTo(id);
		    roleRights=roleRightMapper.selectByExample(example);
		    if (roleRights!=null)
		    {
		    	if (roleRights.size()>0)
		    	{
		    		 msg.setMsg("hasRoleBind");
			    	 return;
		    	}
		    
		    }
		   
		     //判断 用户 权限表
		    List<TRightUser> rightUsers=new ArrayList<TRightUser>();
		    TRightUserExample example2=new TRightUserExample();
		    TRightUserExample.Criteria criteria2=example2.createCriteria();
		    criteria2.andRightidEqualTo(id);
		    rightUsers=rightUserMapper.selectByExample(example2);
		    if (rightUsers!=null)
		    {
		    	if(rightUsers.size()>0)
		    	{
		    		 msg.setMsg("hasUserBind");
			    	 return;
		    	}
		    
		    }
		    
		    

		    TRights  rights=new TRights();
		    rights.setId(id);
		  
			if ( rightsMapper.deleteByPrimaryKey(id) >0)
			{
			  msg.setMsg("deleteOK");
			}
			else {
			 msg.setMsg("deleteFailed");
			}
	  }
	
	

	
	  //处理增加 一级菜单事件

	public void addSuperMenuAction(TRightTypes rightTypes, AjaxMsg msg) {
		 
		    if (  rightTypesMapper.insertSelective(rightTypes)>0 )
		    {
		    	msg.setMsg("insertOK");
		    }else {
		    	msg.setMsg("insertFailed");
			}
		
	}


	 //处理更新菜单事项
	public void updateSuperMenuAction(TRightTypes rightTypes, AjaxMsg msg) {
		 
		      if (  rightTypesMapper.updateByPrimaryKeySelective(rightTypes)>0 )
		      {
		    	  
		    	 msg.setMsg("updateOK");
		      } 
		      else {
				
		    	  msg.setMsg("updateFailed");
			}
		
	}

      //根据 id 查找 一级菜单
	public TRightTypes getSuperMenuById(Integer id) {
		 
		
		
		return   rightTypesMapper.selectByPrimaryKey(id);
	}


	  //获取 二级菜单 分页 数据
	public PageBean<RightVo> getRightsPages(PageVo pageVo) throws Exception
	{
		 
		 //给前台返回所有 分页数据
		PageBean<RightVo> pageBean=new PageBean<RightVo>();
		
		List<TRights>  rights=new ArrayList<TRights>();
		
		List<RightVo>  vos=new ArrayList<RightVo>();
		
		
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 //执行的sql语句
		String sqlString="";
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			
			 if (pageVo.conditions.get("name")!=null & pageVo.conditions.get("name")!="")
			 {
				 sqlString=sqlString+" and  name like  '%"+pageVo.conditions.get("name")+"%'";
				 
				 conditions.put("name", pageVo.conditions.get("name"));
				 
			 }
 
			  if (pageVo.conditions.get("secondMenuId")!=null & pageVo.conditions.get("secondMenuId")!="")
			  {
				  
				  // 如果二级菜单 是 “全部"
				    
					 if (Integer.valueOf((String)pageVo.conditions.get("secondMenuId"))==0)
					 {
						 
						   //看看一级菜单选的是什么  不为0 说明已经 选择了某个一级菜单
						 if ( Integer.valueOf((String)pageVo.conditions.get("superMenuId"))!=0)
						 {
				 
							 sqlString=sqlString+" and  typeid ="+pageVo.conditions.get("superMenuId") ;
							 
							 conditions.put("superMenuId", pageVo.conditions.get("superMenuId"));
							 conditions.put("secondMenuId", pageVo.conditions.get("secondMenuId"));
						 }
						 else {
							 conditions.put("superMenuId", pageVo.conditions.get("superMenuId"));
							 conditions.put("secondMenuId", pageVo.conditions.get("secondMenuId"));
							
						}
						 
						
						 
					 }
					 
					  // 二级菜单 选的不是 全部  说明是精确到某一个 二级类
					 else if  (Integer.valueOf((String)pageVo.conditions.get("secondMenuId"))>0 ){
						 
						 sqlString=sqlString+" and  id ="+pageVo.conditions.get("secondMenuId") ;
						 
						 conditions.put("secondMenuId", pageVo.conditions.get("secondMenuId"));
						 
						 conditions.put("superMenuId", pageVo.conditions.get("superMenuId"));
					 }
				  
			  }
				
				
				 
		 
			 
 
		}
 
	 	
		 
		  //统计 记录数量
			 int  count=workMapper.getCountOfSecondMenus(sqlString);
	 
       
		  //查询 本次查询的 记录集 
	/*		  if (pageVo.currPage<1)
			  {
				  pageVo.setCurrPage(1);
			  }*/
			 
			  
			 sqlString=sqlString+"  order by  typeid, sortId limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			 rights=workMapper.getSecondMenus(sqlString);
	   
		       if (rights!=null)
		       {
		    	   
		    	   if (rights.size()>0)
		    	   {
		    		   for (int i=0; i<rights.size();i++)
		    		   {
		    		   RightVo vo=new RightVo();
		    		   vo.setRight(rights.get(i));
		    		   
		    		   int typeId=rights.get(i).getTypeid();
		    		   TRightTypes type=rightTypesMapper.selectByPrimaryKey(typeId);
		    		   
		    		   vo.setRightType(type);
		    		   
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
	 
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
 
	}

       //获取 一级菜单列表
	public List<TRightTypes> getRightTypes() {
		
		     
		
		return  rightTypesMapper.selectByExample(null);
	}

	//根据 一级菜单 获取二级菜单列表
	public List<TRights> getSecondMenuListBySuperId(Integer superMenuId) {
		
		   if (superMenuId!=null)
		   {
			   
			   TRightsExample example=new TRightsExample();
		    	 TRightsExample.Criteria criteria=example.createCriteria();
		    	 
		    	 
			    //返回所有二级菜单
			     if (superMenuId==0)
			     {
			    	 example.setOrderByClause(" typeid, sortid");
			    	 return  rightsMapper.selectByExample(example);
			    	 
			     }
			     else {
					
			    	
			    	 criteria.andTypeidEqualTo(superMenuId);
			    	 example.setOrderByClause("  sortid");
			         return rightsMapper.selectByExample(example);
			    	 
				}
			   
			   
		   }
		   
		
		return null;
	}


	 //新增 二级菜单
	public void addSecondMenuAction(TRights rights, AjaxMsg msg) {

             if (rightsMapper.insertSelective(rights) >0)
             {
            	 
            	 msg.setMsg("insertOK");
             }
             else {
				msg.setMsg("insertFailed");
			}
		
		
	}


	public TRights getSecondMenuById(Integer id) {
	 
		
		  return  rightsMapper.selectByPrimaryKey(id);
	}


	public void updateSecondMenuAction(TRights right, AjaxMsg msg) {
	             
		if (rightsMapper.updateByPrimaryKeySelective(right)>0)
		{
			
			msg.setMsg("updateOK");
		}
		else {
			msg.setMsg("updateFailed");
		}
		
	}
	

}
	
	
	
	
	
	
 
