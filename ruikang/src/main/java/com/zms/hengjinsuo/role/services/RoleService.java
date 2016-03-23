package com.zms.hengjinsuo.role.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRightTypesExample;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRightsExample;
import com.zms.hengjinsuo.bean.TRoleRight;
import com.zms.hengjinsuo.bean.TRoleRightExample;
import com.zms.hengjinsuo.bean.TRoleUser;
import com.zms.hengjinsuo.bean.TRoleUserExample;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.bean.TRolesExample;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.dao.TRightTypesMapper;
import com.zms.hengjinsuo.dao.TRightsMapper;
import com.zms.hengjinsuo.dao.TRoleRightMapper;
import com.zms.hengjinsuo.dao.TRoleUserMapper;
import com.zms.hengjinsuo.dao.TRolesMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.RoleHasUserVo;
import com.zms.hengjinsuo.vo.SqlParams;
import com.zms.util.MathTools;

@Controller
public class RoleService {
 
	private static Logger log = Logger.getLogger(RoleService.class);

	@Autowired
	private TRolesMapper roleMapper;
	
	@Autowired
	private TRightTypesMapper  rightTypesMapper;
	
	
	@Autowired
	private TRightsMapper  rightsMapper;
	
	@Autowired
	private TRoleRightMapper roleRightMapper;
	
	@Autowired
	private TRoleUserMapper roleUserMapper;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private WorkMapper workMapper;
	
	
	//获取查询  角色列表   
 	public List<TRoles>  getRolesList( int  userId)
	{
		
		 List<TRoles>  roles=new ArrayList<TRoles>();
		 
		  //没有传用户ID过来，直接查 t_roles表
		  if (userId<1)
		  {
		  roles=roleMapper.selectByExample(null);
		  }
		  else   //传了 userId过来， 需要去 角色用户 对应表中查
		  {
			  
		  }
		 
		 return roles;
 
	} 
	
	
	//获取查询 角色列表 支持分页
	public  PageBean<TRoles>   getRolesPage(PageVo pageVo)
	{
		 PageBean<TRoles> pageBean=new PageBean<TRoles>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 
		 List<TRoles>  roles=new ArrayList<TRoles>();
		 
		 String sqlString="";
		 
		//开始吧 条件里的参数 和值 取出来
			if ( pageVo.conditions!=null)
			{
				
				 if (pageVo.conditions.get("name")!=null)
				 {
					 String name=(String)pageVo.conditions.get("name");
					 
				 	 sqlString=sqlString+" and    name like '%"+name+"%'";
					  //给前台返回条件
					 conditions.put("name", name);
					 
					 
				 }
			}
			
			
 
		  //统计 记录数量
			 int  count=workMapper.getCountOfRole(sqlString);
	 
        
		  //查询 本次查询的 记录集 
			 //查询 本次查询的 记录集 
			 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
	    	 roles=workMapper.getRoles(sqlString);
 
	   
		
		 
			 pageBean.setCurrPage(pageVo.getCurrPage());
			 pageBean.setPageSize(pageVo.getPageSize());
			  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
			 pageBean.setTotalRecords(count);
		 
		 pageBean.setPageDatas(roles);
		 
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
 
	}
	
	
	//获取角色信息
	public TRoles  getRoleById(int id) throws Exception
	{
		
	     return roleMapper.selectByPrimaryKey(id);
		
	}
	
	
	
	//保存角色
	public void  insertRoles(TRoles tRoles,AjaxMsg msg) throws Exception
	{
		
	    int lastid=0;
		  if (  roleMapper.insert(tRoles)>0)
		  {
			  lastid=tRoles.getId();
			  log.debug("插入后获取的id:"+lastid);
			  msg.setCode(lastid);
		
			  msg.setMsg("ok");
		  }
		  else
		  {
			  msg.setMsg("no");
		  }
	}
	
	
	//修改角色
	public void  updateRole(TRoles tRoles,AjaxMsg msg) throws Exception
	{
		
	 
		
	     if (   roleMapper.updateByPrimaryKeySelective(tRoles) >0)
	     {
	    	 msg.setMsg("updateok");
	     }
	     else {
			msg.setMsg("updatefailed");
		}
	}
	
	
	
	//删除角色
	public void  deleteRoleById(int roleId,AjaxMsg msg) throws Exception
	{
   
		 //先判断  select * from t_role_user; 里有没有 role_id=id的数据， 如果有 就提示 不能删除！
		if (checkRoleHasUser(roleId))
		{
			
			msg.setMsg("hasUser");
			return;
		}
		  
		 //删除  role_right里  rolid=id的所有数据 
		 
		deleteFromRoleRightByRoleId(roleId);
		
		  //最后删除 角色表中的数据
		  if (  roleMapper.deleteByPrimaryKey(roleId)>0)
		  {
			  msg.setMsg("ok");
		  }
		  else
		  {
			  msg.setMsg("no");
		  }
 
		
	}
	
	
	//删除角色的时候 判断  角色-用户表 中是否有用户使用了这个角色 ，有的话 就不让删除！
	public boolean checkRoleHasUser(int roleId)
	{
		    List<TRoleUser>  users=new ArrayList<TRoleUser>();
		    TRoleUserExample example=new TRoleUserExample();
		    TRoleUserExample.Criteria criteria=example.createCriteria();
		    criteria.andRoleidEqualTo(roleId);
		    
		    users=roleUserMapper.selectByExample(example);
		    
		    if (users.size()>0)
		    {
		    	return true;
		    }
		    else {
				return false;
			}
 
	}
	
	 //删除 角色-权限对应表中 的数据
	public void  deleteFromRoleRightByRoleId(int roleId)
	{
		
		   TRoleRightExample example=new TRoleRightExample();
		   TRoleRightExample.Criteria criteria=example.createCriteria();
		   criteria.andRoleidEqualTo(roleId);
		   
		   roleRightMapper.deleteByExample(example);
		   
		
	}
	
	
	 
 
	//获取查询 大的模块表 不支持分页  如果 roleid>0 则 表示查询某个角色下面的大模块， 否则 显示所有大模块
		public List<TRightTypes>  getRightTypes( int roleId)
		{
			
			 List<TRightTypes>  types=new ArrayList<TRightTypes>();
			 
			 //如果grupid>0 说明是查询某个 角色下的 大模块
			 if (  roleId>0)
			 {
				    TRolesExample example=new TRolesExample();
				    TRolesExample.Criteria criteria=example.createCriteria();
				    criteria.andIdEqualTo(roleId);
				    
				    
				  List<TRoles> roles=     roleMapper.selectByExample(example);
				  
				   if (roles.size()>0)
				   {
					       TRoles role=roles.get(0);
					     
					       String ids[]=role.getRightids().split(",");
					       List<Integer> idsIntegers=MathTools.strArray2intList(ids);
					       TRightTypesExample  typesExample=new TRightTypesExample();
					       TRightTypesExample.Criteria typescriteria=typesExample.createCriteria();
					       typescriteria.andIdIn(idsIntegers);
					       typescriteria.andIsuseEqualTo(true);
					       
					       types=rightTypesMapper.selectByExample(typesExample);
				   }
				   else
				   {
					   return null;
				   }
				    
			 }
			  //查询所有的大模块
			 else
			 {
				  TRightTypesExample  typesExample=new TRightTypesExample();
			       TRightTypesExample.Criteria typescriteria=typesExample.createCriteria();
			      
			       typescriteria.andIsuseEqualTo(true);
			       
				   types=rightTypesMapper.selectByExample(typesExample);
			 }
		 
		    
			  for(TRightTypes item:types)
			  {
				  log.debug(item.getName());
			  }
			 
			 return types;
	  
		}
		
		
		 //查询某个 大模块下的所有 子模块
		public List<TRights>  getRightsByTypeId(int typeid)
		{
			
			 List<TRights> tJurisdictions=new ArrayList<TRights>();
			 
			 TRightsExample example=new TRightsExample();
			 TRightsExample.Criteria criteria=example.createCriteria();
			 criteria.andTypeidEqualTo(typeid);
			
			 tJurisdictions= rightsMapper.selectByExample(example);
		 
			 return tJurisdictions;
			
		}
		
		//获取某个角色 下面的 权限地图
		public  List<Map<String, Object>>  getRightsMap(int  roleid)
		{
			      List<Map<String, Object>>  rightsList=new ArrayList<Map<String, Object>>();
			      
			       //获取 角色下面的所有 大模块
				  List<TRightTypes> types = getRightTypes(roleid);
				  
				  log.debug("获取角色ID为:"+roleid+"的所有大模块数量"+types.size());
				  
				   if (types.size()>0)
				   {
  
							   for (TRightTypes type:types)
							   {
								   
								   Map<String, Object> rightMap = new HashMap<String, Object>();
								   
								   //获取 大类型下的所有 子模块
								   List<TRights> tJurisdictions=getRightsByTypeId(type.getId());
								   
								     rightMap.put("type", type);
									rightMap.put("rights", tJurisdictions);
								    
									rightsList.add(rightMap);
							   }
				   
				   }
				
			 return rightsList;
		}
		
		
		
		// 设置角色的权限，  也就是插入  角色和 权限 对应表
 	public  void  setRoleWithIds(int  roleid, String ids,AjaxMsg msg)
		{
			    //先删除 这个角色下面所有的权限
 		         //tRoleOfGroupMapper.deleteByPrimaryKey(roleid);
 		           TRoleRightExample example=new TRoleRightExample();
 		          TRoleRightExample.Criteria criteria=example.createCriteria();
 		           criteria.andRoleidEqualTo(roleid);
 		           
 		            if (roleRightMapper.deleteByExample(example) <0)
 		            {
 		            	 msg.setMsg("error");
		        		  return;
 		            }
 		            
 		            // 开始循环 插入 对应表
 		         if ( ids.length()>0 )
 		         {
 		        	 String[]  idsString= ids.split(",");
 	 		        
 	 		         List<Integer>  idd=MathTools.strArray2intList(idsString);
 	 		         
 	 		          for(Integer id:idd)
 	 		          {
 	 		        	    TRoleRight roleOfGroup=new TRoleRight();
 	 		        	    
 	 		        	    roleOfGroup.setRightid(id);
 	 		        	    roleOfGroup.setRoleid(roleid);
 	 		        	    
 	 		        	    //插入成功 返回字符串
 	 		        	  if(  roleRightMapper.insert(roleOfGroup) <0)
 	 		        	  {
 	 		        		  msg.setMsg("error");
 	 		        		  return;
 	 		        	  }
 	 		          }
 		        	 
 		         }
 		        
 		         
 		         msg.setMsg("insertroleok");
 		     
				
		 
		} 
 
		
		//获取某个角色 下的所有 权限id 
 	public  String  getRightsByRoleId(int  roleid)
		{
 		
 		//从对应表取出 角色ID 为 roleid的 所有权限id
 		TRoleRightExample example=new TRoleRightExample();
 		TRoleRightExample.Criteria criteria=example.createCriteria();
         criteria.andRoleidEqualTo(roleid);
         
         //把所欲权限ID放到 List中
	    List<TRoleRight>  roleOfGroups=roleRightMapper.selectByExample(example);
			
	    
	    List<Integer>  ids=new ArrayList<Integer>();
	    
	     for (TRoleRight group:roleOfGroups)
	     {
	    	      ids.add(group.getRightid()); 
	     }
	        //把list 转为 字符串
	           if (ids.size()>0)
	           {
	        	   String  idsString= StringUtils.join(ids, ",");
	        	   
	  	           log.debug("获取到的权限ids："+idsString);
	  	            return 	 idsString;
	           }
 
	           return "";
		} 
 
 	//根据 userid 获取 角色 ID 序列  
		
 	public  String  getRoleListByUserId(int  userId)	
 	{
 		 
 		  TRoleUserExample example=new TRoleUserExample();
 		  TRoleUserExample.Criteria criteria=example.createCriteria();
 		  criteria.andUseridEqualTo(userId);
 		  
 		 List<TRoleUser> roles=roleUserMapper.selectByExample(example);
 		 
 		List<Integer>  ids=new ArrayList<Integer>();
 		 
 	      for ( TRoleUser role:roles)
 	      {
 	    	  ids.add(role.getRoleid());
 	    	  
 	      }
 	      
 	     //把list 转为 字符串
          if (ids.size()>0)
          {
       	       String  idsString= StringUtils.join(ids, ",");
       	   
 	           log.debug("获取到用户："+userId+"的所有角色id:"+idsString);
 	            return 	 idsString;
          }
          else {
        		 return  "";
		}

 	
 	}
 	
 	
 	 // 从 用户 角色 关联表中， 删除指定用户的数据 （主要用来 删除用户  和 修改用户（先删除对应表中该用户的所有信息，再把从前台传来的ids插入对应表））
 	
 	 public  int  deleteRoleByUserId(int userId)
 	 {
 		  TRoleUserExample example=new TRoleUserExample();
 		  TRoleUserExample.Criteria criteria=example.createCriteria();
 		  criteria.andUseridEqualTo(userId);
 		  
 		 return  roleUserMapper.deleteByExample(example);
 		 
 		   
 		 
 	 }


 	   //根据 角色 ID 获取用户列表
	public PageBean<RoleHasUserVo> getUsersPageByRoleId(PageVo pageVo, Integer roleId) throws Exception
	{
		 PageBean<RoleHasUserVo> pageBean=new PageBean<RoleHasUserVo>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 String sqlString="";
		    List<TUser>  users=new ArrayList<TUser>();
		    
			 
			  //开始吧 条件里的参数 和值 取出来
				if ( pageVo.conditions!=null)
				{
					
					 if (pageVo.conditions.get("name")!=null && pageVo.conditions.get("name")!="")
					 {
						 String name=(String)pageVo.conditions.get("name");
						 
					 	 sqlString=sqlString+" and  ( username like '%"+name+"%'  or  realname like '%"+name+"%')"; 
						  //给前台返回条件
						 conditions.put("name", name);
						 
						 
					 }
				}
				
				
		  //统计 记录数量
			 int  count=workMapper.getCountOfUsers(sqlString);
		  //查询 本次查询的 记录集 
			 
			 sqlString=sqlString+"     limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
	      users=workMapper.getUsers(sqlString);
	      
	      
	      pageBean.setCurrPage(pageVo.getCurrPage());
			 pageBean.setPageSize(pageVo.getPageSize());
			  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
			 pageBean.setTotalRecords(count);
		 
		 List<RoleHasUserVo> vos=new ArrayList<RoleHasUserVo>();
		 
		 for (int i=0; i<users.size();i++)
		 {
			 
			 RoleHasUserVo vo=new RoleHasUserVo();
			 
			    if ( checkRoleAndUser(roleId,users.get(i).getId()) )
			    		{
			    	         vo.setHasBinded(true);
			    		}
			       else {
					vo.setHasBinded(false);
			     	}
			    
			    vo.setRoleId(roleId);
			    
			    vo.setUser(users.get(i));
			 
			    
			    vos.add(vo);
		 }
		 
		 
		  pageBean.setPageDatas(vos);
		 
		  
		  pageBean.setConditions(conditions);
 
		return pageBean;
 
	}
	
	
	

  //根据 判断 用户 和 角色是否绑定关系
	public  boolean  checkRoleAndUser( int roleId,int userid)  throws Exception
	{
		 
		    List<TRoleUser>  users=new ArrayList<TRoleUser>();
		 
		     TRoleUserExample example=new TRoleUserExample();
		     
		     TRoleUserExample.Criteria criteria=example.createCriteria();
		     
		     criteria.andRoleidEqualTo(roleId);
		     criteria.andUseridEqualTo(userid);
 
		     users=roleUserMapper.selectByExample(example);
	   
		    if (users!=null)
		    {
		    	  if (users.size()>0)
				    {
				    	return true;
				    }
		    }
		     
		  
		  
		    return  false;
	}

	
	 //解除  用户 和  角色的绑定

	public void unBindRole(Integer userId, Integer roleId,AjaxMsg msg) {
		 
		     TRoleUserExample example=new TRoleUserExample();
		     
		     TRoleUserExample.Criteria criteria=example.createCriteria();
		     
		     criteria.andRoleidEqualTo(roleId);
		     criteria.andUseridEqualTo(userId);

		     if ( roleUserMapper.deleteByExample(example) >0)
		     {
		    	 msg.setMsg("unBindOk");
		     }
		     else {
		    	 msg.setMsg("unBindFailed");
			}
		
		
		
	}

        //绑定  就是往  角色和用户 对应表增加一个条数据
	public void bindRole(Integer userId, Integer roleId, AjaxMsg msg) {
	     
		TRoleUser roleUser=new TRoleUser();
		
		 roleUser.setRoleid(roleId);
		 roleUser.setUserid(userId);
		 
		  if ( roleUserMapper.insertSelective(roleUser)>0 )
		  {
			  msg.setMsg("bindOk");
		  }
		  else {
			msg.setMsg("unBindFailed");
		}
		
	} 

 
		
}
