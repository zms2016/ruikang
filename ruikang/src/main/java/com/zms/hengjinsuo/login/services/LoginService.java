package com.zms.hengjinsuo.login.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.log.services.LogService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.MenuVo;


@Service
public class LoginService {
	
	private static Logger log = Logger.getLogger(LoginService.class);
	
	
	
	@Autowired
    private WorkMapper workMapper;
	
	@Autowired
	private UserService userService;
	  @Autowired
	  private LogService logService;
	
	public void doLogin(TUser user,String  kaptcha,  AjaxMsg msg,HttpSession session,HttpServletRequest request ) throws Exception
	{
		
		
		 //去掉验证码验证
		TUser userTemp=new TUser();
		 
		
		
		 userTemp= userService.checkUser(user);
            if (  userTemp!=null  )
            {
            	 //账号停用
       		 
      		  if (userTemp.getIsuse()>0)
      		  {
      			   
      			  msg.setMsg("userError");
                    return  ;
      		  }
           	 session.setAttribute("userid", userTemp.getId());
        	 session.setAttribute("loginname", userTemp.getUsername());
           	 session.setAttribute("username", userTemp.getRealname());
           	 
            
              //logService.doLog(userTemp.getId() , "登录成功", request.getRemoteAddr());
           	
           	  msg.setMsg("loginOk");
                  return  ;
            }
            else {
            	 
            	 //logService.doLog(0, user.getUsername()+"登录失败", request.getRemoteAddr());
           	  //密码 不正确
  			       msg.setMsg("passwordError");
                 return  ;
			}
            
            
		
		/*  需要验证码
		 * String sessionText="";
		 if(  session.getAttribute("picText")!=null )
		 {
			  sessionText=(String)session.getAttribute("picText");
		 }
 
		 session.removeAttribute("picText");
		
		 if (kaptcha!=null & kaptcha.length()==4)
		 {
				  //判断 缓存中的图片验证码
				 if (kaptcha.equals( sessionText)) 
				 {
						TUser userTemp=new TUser();
					 userTemp= userService.checkUser(user);
			             if (  userTemp!=null  )
			             {
			            	 log.debug("获取到的userid:"+userTemp.getId());
			            	 session.setAttribute("userid", userTemp.getId());
			            	 session.setAttribute("username", userTemp.getRealname());
			            	 
			            	  msg.setMsg("loginOk");
 	                          return  ;
			             }
			             else {
							
			            	  //密码 不正确
			   			       msg.setMsg("passwordError");
			                  return  ;
						}
  
				 }
				 else{
					  //验证码 不正确
					  msg.setMsg("picError");
		               return  ;
				 }
			 
		 }
		 else{
			  //验证码 不正确
			  msg.setMsg("picError");
              return  ;
		 }
		 */
 
		
	}


	  //获取 用户的所有 权限菜单
	public List<MenuVo> getRightsMap(int userId) {
		 
		List<MenuVo>  menus=new ArrayList<MenuVo>();
		
		String sqlString="  select *  from t_right_types where  isUse=1 and  id in(  select  distinct(r.typeid)  from t_rights as r where  r.id in (select ros.rightId from t_right_user as ros where ros.userId = "+userId
				+") or r.id in (select rog.rightId from t_role_right as rog where roleId in (select gos.roleId from t_role_user as gos where gos.userId ="+ userId
				+"))   ) order by sortid " ;
		
     
		 log.debug("进入后台首页，根据用户"+userId+"，获取顶级菜单语句:"+sqlString);
		
		List<TRightTypes> typesOfUser=workMapper.getAllSuperMenusByUserId(sqlString) ;
		  
		   for ( int i=0; i<typesOfUser.size();i++)
		   {
			  
			   String  sqlString2="select r.id,r.name,r.typeid,r.url from t_rights as r where  isUse=1 and  typeid="+typesOfUser.get(i).getId() 
                                        +"  and  ( r.id in (select ros.rightId from t_right_user as ros where ros.userId = "+userId+")"
                                        +"  or r.id in (select rog.rightId from t_role_right as rog where roleId in (select gos.roleId from t_role_user as gos where gos.userId = "+userId+" )) ) order by sortid";
				
			  log.debug(" 用户"+userId+"在菜单:"+typesOfUser.get(i).getName()+"下，所有二级菜单的语句:"+sqlString);
			  
				List<TRights> rights=workMapper.getAllSecMenuBySuperId(sqlString2);
			   
			   MenuVo vo=new MenuVo();
			   
			    vo.settRightTypes(typesOfUser.get(i));
			   
			   vo.setRights(rights);
			  
			   menus.add(vo);
		   }
		
		
		return menus;
	}
	

}
