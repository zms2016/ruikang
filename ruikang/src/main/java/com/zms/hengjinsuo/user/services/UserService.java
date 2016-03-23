package com.zms.hengjinsuo.user.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TDepartment;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRightUser;
import com.zms.hengjinsuo.bean.TRightUserExample;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRoleUser;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TUserExample;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.dao.TRightUserMapper;
import com.zms.hengjinsuo.dao.TRoleUserMapper;
import com.zms.hengjinsuo.dao.TUserMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.department.services.DepartmentService;
import com.zms.hengjinsuo.role.services.RoleService;
import com.zms.hengjinsuo.vip.services.VipService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.SqlParams;
import com.zms.hengjinsuo.vo.UserVo;
import com.zms.util.KeyTools;
import com.zms.util.MathTools;



@Service
public class UserService {
	
	
 	//@Autowired  //默认按类型装配  spring自己的
	//@Resource     //默认按名称装配  是JAVA 
	
	@Resource
	private TUserMapper userMapper;
	
	@Resource
	private RoleService roleService;
	
    @Resource
    private TRoleUserMapper roleUserMapper;
    
    @Resource
    private TRightUserMapper rightUserMapper;
    
    @Resource
    private WorkMapper workMapper;
    
    @Resource
    private DepartmentService departmentService;
    
    @Resource
    private VipService vipService;
    
	
	private static Logger log = Logger.getLogger(UserService.class); 
	
	
	

	//获取查询 角色列表 支持分页
	public  PageBean<UserVo>   getUsersPage(PageVo pageVo)
	{
 
		 PageBean<UserVo> pageBean=new PageBean<UserVo>();
		 
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 
           List<TUser>  users=new ArrayList<TUser>();
		 
		 List<UserVo>  vos=new ArrayList<UserVo>();
		 
		 String sqlString=" ";
		 
		  //开始吧 条件里的参数 和值 取出来
			if ( pageVo.conditions!=null)
			{
				
				 if (pageVo.conditions.get("name")!=null)
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
			 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
	    	 users=workMapper.getUsers(sqlString);
 
			 pageBean.setCurrPage(pageVo.getCurrPage());
			 pageBean.setPageSize(pageVo.getPageSize());
			  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
			 pageBean.setTotalRecords(count);
		 
		 if (users!=null)
		 {
			 if (users.size()>0)
			 {
				 
				  for (int i=0;i<users.size();i++)
				  {
					  UserVo vo=new UserVo();
					  vo.setUser(users.get(i));
					   int depId=users.get(i).getDepartmentid();
					   
					  vo.setDepartmentName(departmentService.geTDepartmentById(depId).getName());
					  vos.add(vo);
				  }
					  
						 pageBean.setPageDatas(vos);
			 }
		 }
	
		 
		  pageBean.setConditions(conditions);
		 
		  
		  return pageBean;
 
	}
			
	 
		
		
		
	
/*	 //获取用户列表
	public   List<TUser> getUsers()
	{
		
		List<TUser> users =new ArrayList<TUser>();
		
		TUserExample example=new TUserExample();
	 
		   users=userMapper.selectByExample(example);
	 
		 
		return users;
		
	}*/
	
	
	//获取所有角色列表
	public   List<TRoles>  getRolesList(int userId )
	{
		return  roleService.getRolesList(userId);
	}
	
	
	
	public void updateUserFace(TUser user,AjaxMsg msg) throws Exception
	{
		
		 
		
		 if (userMapper.updateByPrimaryKeySelective(user)>0)
		 {
			  msg.setCode(1);
		 }
		 else {
			msg.setCode(0);
			 
		}
	}
	
	//修改用户信息
	public   void  updateUser(TUser user,String[] ids,AjaxMsg  msg) throws Exception
	{
		   
		//由于密码是MD5加密 ，无法解密， 修改密码界面会吧 数据库md5密码读过去是34位的，而且一般密码不会超过30位，所以这里 弱智的判断一下 密码框是否修改了
	    if (user.getPassword().length()<30 && user.getPassword().length()>=3)
	    {
	    	String md5Password=KeyTools.MD5(user.getPassword());
			user.setPassword(md5Password);
			 
	    }
 
	  	userMapper.updateByPrimaryKeySelective(user);
		
	  	//从 用户 角色 对应表中删除 这个用户的所有信息
	  	roleService.deleteRoleByUserId(user.getId());
	  	
	  	
	  	
	    //把前台checkbox传过来的角色id 插入到  用户角色 对应表中
        if ( ids!=null  )
        {
       	 for( int i=0;i<ids.length;i++)
	         {
	        	 
	        	  int  roleid=Integer.parseInt(ids[i]);
	        	  
	        	  TRoleUser roleUser=new TRoleUser();
	        	  
	        	  roleUser.setRoleid(roleid);
	        	  roleUser.setUserid(user.getId());
	        	  
	        	  roleUserMapper.insert(roleUser);
	        	  
	         }
        }
        
        
        msg.setMsg("updateOK");
       
		 
	 
	 
	/*	测试  事务管理
	 * 
	 *  TUser user2=userMapper.selectByPrimaryKey(91);
		   if (user2.getId()>10)
		   {
			   throw new Exception();
		   }
		 user2.setUsername("城市");
		 
		userMapper.updateByPrimaryKeySelective(user2);*/
		
	}
	
	//根据用户Id  删除用户
	public  int  deleteUser(int  userId)  throws Exception
	{
		
		 //删除  角色 用户关联表
           roleService.deleteRoleByUserId(userId);
           
           //删除 用户 权限关联表
           deleteRightUserByUserId(userId);
		
		
	    return 	userMapper.deleteByPrimaryKey(userId);
	
	
	}
	
	  //删除 用户 权限 对应表
	public  void   deleteRightUserByUserId(int  userId) throws Exception
	{
		
	       TRightUserExample example=new TRightUserExample();
	       TRightUserExample.Criteria criteria=example.createCriteria();
	       criteria.andUseridEqualTo(userId);
	       rightUserMapper.deleteByExample(example);
 
	}
	
	
	
	  /**
	   * 
	   * 功能说明：新增用户， 做2个事情，一个是 用户入库，第2个是 给 用户 角色 对应表增加相应条数的记录
	   * 创建人：@author 330140511@qq.com   
	   * 创建时间：2015年10月12日/下午3:11:37 
	   * @param user
	   * @param ids  角色ID 字符串序列
	   * @return
	   * @throws Exception
	   */
	public   void insertUser(TUser user, String[] ids,AjaxMsg msg) throws Exception
	{
		
		String md5Password=KeyTools.MD5(user.getPassword());
		user.setPassword(md5Password);
		
		    if (userMapper.insertSelective(user)>0 )
		    {
		    	
		        int userid=user.getId();
		        
		        log.debug("获取到 插入的用户id；"+userid);
		         
		        
		         //把前台checkbox传过来的角色id 插入到  用户角色 对应表中
		         if ( ids!=null  )
		         {
		        	 for( int i=0;i<ids.length;i++)
			         {
			        	 
			        	  int  roleid=Integer.parseInt(ids[i]);
			        	  
			        	  TRoleUser roleUser=new TRoleUser();
			        	  
			        	  roleUser.setRoleid(roleid);
			        	  roleUser.setUserid(userid);
			        	  
			        	  roleUserMapper.insert(roleUser);
			        	  
			         }
		         }

                     msg.setMsg("insertOK");
		    }
		    else {
		    	 msg.setMsg("insertFailed");
			}
		  
		   
	 
	}
	
	
	  //根据 主键id 查找 TUser实体
	public TUser  findUserById(Integer id) throws ServicesException
	{
		 
		     if (id!=null)
		     {
		    	 
		    	  TUser TUser= userMapper.selectByPrimaryKey(id);
				     if (TUser!=null)
				     {
				    	 return userMapper.selectByPrimaryKey(id);
				     }
				     else {
						
				    	 throw new ServicesException(new Exception(),"没有找到用户");
					}
		     }
		      
		     return null;
		
	}
	
	 //根据用户名查询
	public TUser  findUserByName(String name) throws ServicesException
	{
		 
		
	    
	      
	      TUserExample userSql=new TUserExample();
 
			 
	      TUserExample.Criteria  criteria=userSql.createCriteria();
	      
		   criteria.andUsernameEqualTo(name);
	      
	       List<TUser> users=userMapper.selectByExample(userSql);
	      
	        if (users.size()>0)
	        {
	        	return users.get(0);
	        }
	      
	      return null;
		
	}
	
	//根据用户ID 查询  拥有的角色ID
	
 	public  String  getRoleListByUserId(int  userId)	
 	{
 		
 		return roleService.getRoleListByUserId(userId);
 	}
	  
 	
 
 	
 	
 	
 	//验证用户名密码
 	
 	public   TUser checkUser(TUser user) throws Exception
 	{
 		//把传过来的密码  通过MD5加密， 然后和 已经存在数据库中的 MD5 密码串比较 是否一致      （MD5无法 逆运算！所以不能通过把数据库的密码串 解密 来和 前台传来的密码 比较）
 		String md5Password=KeyTools.MD5(user.getPassword());
 		
 		TUserExample example=new TUserExample();
 		TUserExample.Criteria criteria=example.createCriteria();
 		criteria.andUsernameEqualTo(user.getUsername());
 		criteria.andPasswordEqualTo(md5Password);
 		
 	 
 		
 		List<TUser> users=new ArrayList<TUser>();
 		
 		  users=userMapper.selectByExample(example);
 		 if (users!=null & users.size()>0 )
 		 {
 
 			 return users.get(0);
 		 }
 		
 		return  null;
 	}
 	
 	
 	
 	//获取 所有 权限地图 供 个人设置特定权限
 			public  List<Map<String, Object>>  getRightsMap( )
 			{
 				      List<Map<String, Object>>  rightsList=new ArrayList<Map<String, Object>>();
 				      
 				       //获取 角色下面的所有 大模块
 					  List<TRightTypes> types = roleService.getRightTypes( -1);
 					  
 					  log.debug("获取 的所有大模块数量"+types.size());
 					  
 					   if (types.size()>0)
 					   {
 	  
 								   for (TRightTypes type:types)
 								   {
 									   
 									   Map<String, Object> rightMap = new HashMap<String, Object>();
 									   
 									   //获取 大类型下的所有 子模块
 									   List<TRights> tJurisdictions=roleService.getRightsByTypeId(type.getId());
 									   
 									     rightMap.put("type", type);
 										rightMap.put("rights", tJurisdictions);
 									    
 										rightsList.add(rightMap);
 								   }
 					   
 					   }
 					
 				 return rightsList;
 			}

 			
 			
 			/**
 			 * 
 			 * 功能说明： 用户列表--》单个用户 设置权限   ， 为用户和权限对应表 增加相应的数据
 			 * 创建人：@author 330140511@qq.com   
 			 * 创建时间：2015年10月12日/下午3:17:23 
 			 * @param roleid
 			 * @param ids
 			 * @param msg
 			 */
 		 	public  void  setRightsForUser(int  userid, String ids,AjaxMsg msg) throws Exception
 			{
 		 		 if (userid>0)
 		 		 {
 		 			 //先删除 这个用户下面所有的权限
		 		      
	 		             TRightUserExample example=new TRightUserExample();
	 		             TRightUserExample.Criteria criteria=example.createCriteria();
	 		             criteria.andUseridEqualTo(userid);
	 		             rightUserMapper.deleteByExample(example) ;
	 		          
	 		            
	 		            // 开始循环 往 user_rights表中 插入数据
	 		         if ( ids.length()>0 )
	 		         {
	 		        	 String[]  idsString= ids.split(",");
	 	 		        
	 	 		         List<Integer>  idd=MathTools.strArray2intList(idsString);
	 	 		         
	 	 		          for(Integer id:idd)
	 	 		          {
	 	 		        	TRightUser rightUser=new TRightUser();
	 	 		        	    
	 	 		            rightUser.setRightid(id);
	 	 		            rightUser.setUserid(userid);
	 	 		        	    
	 	 		        	    //插入成功 返回字符串
	 	 		        	  if(  rightUserMapper.insert(rightUser) <0)
	 	 		        	  {
	 	 		        		  msg.setMsg("insertFailed");
	 	 		        		  throw new ServicesException(new Exception(), "插入 用户 权限对应表异常！");
	 	 		        		 
	 	 		        	  }
	 	 		          }
	 		        	 
	 		         }
	 		         msg.setMsg("insertOK");
 		 		 }
    	} 
 	
 		 	
 		 //获取某个用户下面的  所有 权限id 
 		 	public  String  getRightsByUserId(int  userId)
 				{
 		 		
 		 		//从对应表取出 角色ID 为 roleid的 所有权限id
 		 		TRightUserExample example=new TRightUserExample();
 		 		TRightUserExample.Criteria criteria=example.createCriteria();
 		         criteria.andUseridEqualTo(userId);
 		         
 		         //把所欲权限ID放到 List中
 			    List<TRightUser>  rightUsers=rightUserMapper.selectByExample(example);
 					
 			    
 			    List<Integer>  ids=new ArrayList<Integer>();
 			    
 			     for (TRightUser item:rightUsers)
 			     {
 			    	      ids.add(item.getRightid()); 
 			     }
 			        //把list 转为 字符串
 			           if (ids.size()>0)
 			           {
 			        	   String  idsString= StringUtils.join(ids, ",");
 			        	   
 			  	      
 			  	           log.debug(" userserivces/getRightsByRoleId/获取到的权限ids："+idsString );
 			  	            return 	 idsString;
 			           }
 		 
 			           return "";
 				}




               //获取部门列表

			public List<TDepartment> getDepartmentList() {
				
				
				return  departmentService.getDepartmentList();
				 
			}





              //获取所有用户
			public List<TUser> getAllUsers() {
				 
			//	0:正常  1 停用  用户账号状态
				
				
				return userMapper.selectByExample(null);
			} 
			
			

			  //获取用户列表
			public List<TUser> getUsers() {
				 
				 TUserExample example=new TUserExample();
				 TUserExample.Criteria criteria=example.createCriteria();
				 criteria.andIsuseEqualTo(  0);
				 criteria.andIdGreaterThan(180);
				 example.setOrderByClause(" CONVERT(   realName using gbk )  ");
				 
				return userMapper.selectByExample(example);
			}





             //修改密码
			public void changeUserPwd(Integer userid, String password, AjaxMsg msg) {
				 
				
				 
			    	String md5Password=KeyTools.MD5(password);
				 
			         TUser user=userMapper.selectByPrimaryKey(userid);
			         user.setPassword(md5Password);
			         
			       if (   userMapper.updateByPrimaryKeySelective(user)>0 )
			       {
			    	   msg.setMsg("updateOK");
			       }
			       else {
					 msg.setMsg("updateFailed");
				}
			    
			}





    //判断是否已经建立了理财客户
			public boolean hasVip(int id) {
				 
				List<TVip> vips=vipService.getVipsByUserId(id);
				if( vips!=null)
				{
					if (vips.size()>0)
					{
						return true;
					}
					
				}
				return false;
			}

 
			 /**
			  * 
			  * 功能说明：修改用户标志
			  * 创建人：张木生 330140511@qq.com   
			  * 创建时间：2016年3月12日/下午2:34:48 
			  * @param id
			  * @param flag
			  */
			public void changeUserFlag(int id ) {
 
				TUser user=userMapper.selectByPrimaryKey(id);
				
				int flag=user.getIsuse();
				  if(flag==0)
				  {
					  user.setIsuse(1);
				  }
				  else{
					  user.setIsuse(0);
				  }
				 
				
 		        
			     userMapper.updateByPrimaryKey(user);
			}
 	 	
}
