package com.zms.hengjinsuo.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zms.exception.ControllerException;
import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.login.services.LoginService;
import com.zms.hengjinsuo.user.services.UserService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.UserVo;
import com.zms.util.ApplicationTools;
import com.zms.util.Constants;

@Controller
@RequestMapping("/manager")
public class  UserController {

	private static Logger log = Logger.getLogger( UserController.class);

	@Autowired
	private  UserService userService;
 
    @Autowired
    private LoginService loginService;
 
 


	/**
	 * 
	 * 功能说明：进入编辑页面 创建人：@author 330140511@qq.com 创建时间：2015年9月10日/上午9:45:54
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws ServicesException
	 */

	@RequestMapping("/updateUserInit")
	public String updateUserInit(Integer id, Model model) throws Exception {
		
		
		 //防止用户在 更新页面刷新
		if (id!=null)
		{
			TUser user;
			user = userService.findUserById(id);
			if (user != null) {

				log.debug("需要编辑的用户" + user.getUsername() + "原来的图片路径:" + user.getPic());
				model.addAttribute("user", user);
				
				
				//把部门列表返回
				model.addAttribute("departmentList", userService.getDepartmentList());
				
				
				
		 
				 //获取所有 角色，用来在前台显示在很多 checkbox中
				model.addAttribute("roleList", userService.getRolesList(-1));
				
				//获取 角色ID 序列， 用来把前台 checkbox打勾
				model.addAttribute("roleIds", userService.getRoleListByUserId(id));
				
				
				return "manager_user/updateUser";

			} else {
				  
				 return "redirect:getUserList.html";
			 
			}

		}
		else {
			
			 //id为 null 跳转到 用户列表
			 return "redirect:getUserList.html";
			
		}
		
	
	}

/**
 * 
 * 功能说明： 响应 用户信息修改
 * 创建人：@author 330140511@qq.com   
 * 创建时间：2015年9月29日/下午2:26:43 
 * @param user
 * @param model
 * @param picFile
 * @return
 * @throws Exception
 */
	@RequestMapping("/updateUserAction")
	@ResponseBody
	public AjaxMsg updateUserAction(TUser user, Model model, MultipartFile picFile,HttpServletRequest request) throws Exception {

		    AjaxMsg msg=new AjaxMsg();
		    
		 String[] ids=null;
		 
			if (request.getParameterValues("roleids") != null )
			{
			 
				ids=request.getParameterValues("roleids") ;
			}
			
			log.debug("需要修改的用户信息:" + user.getUsername() + "生日："+ user.getBirthday()+"角色IDs："+Arrays.toString(ids));

		TUser userLast = null;

		// 判断 文件控件是否传了文件 一般情况下 字符串长度肯定大于4
		if (picFile != null  ) {
		 
			   if (  picFile.getOriginalFilename().length() > 4  )
			   {

					// 将接收到的图片 写入 硬盘
					try {

						// 获取图片后缀名
						// 获取原始文件名称
						String sourceFilename = picFile.getOriginalFilename();
						// 取扩展名
						String subFixName = sourceFilename.substring(sourceFilename.lastIndexOf("."));
						// 新图片名称
						String newFileName = UUID.randomUUID() + subFixName;

						// 创建新图片

						File newPicFile = new File(Constants.USEPIC_PATH  + newFileName);
						picFile.transferTo(newPicFile);

						userLast = userService.findUserById(user.getId());

						// 删除老图片
						String oldPicName = userLast.getPic();
						File oldPicFile = new File(Constants.USEPIC_PATH  + oldPicName);
						oldPicFile.delete();

						// 为用户 图片 属性赋值
						user.setPic(newFileName);

					} catch (IllegalStateException e) {

						throw new ControllerException(e, "图片上传失败" + e.getMessage());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						throw new ControllerException(e, "图片上传失败" + e.getMessage());
					} 
				   
			   }

		}
		//上面一段是处理图片的，没有图片的话直接处理下面这个

		userService.updateUser(user,ids,msg);

	 

		return msg;
	 
	}

/**
 * 
 * 功能说明：删除用户 前台 通过 ajax 提交 ， 返回 json ,并且限制 只能通过post提交
 * 创建人：@author 330140511@qq.com   
 * 创建时间：2015年9月29日/下午2:26:25 
 * @param id
 * @return
 * @throws Exception
 */

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(int id) throws Exception {

		TUser user = null;

		//如果是管理员 则不让 删除
           if (id==1)
           {
        	   
        	   return "delAdmin";
           }
           //看看是否绑定了vip理财客户
          if (userService.hasVip(id))
          {
        	  //System.out.println("不让删除！");
        	  return "hasVip";
          }

		if (userService.findUserById(id) != null) {
                
			try {

				user = userService.findUserById(id);

				String picName = user.getPic();

				File picFile = new File(Constants.USEPIC_PATH  + picName);

				picFile.delete();

				if (userService.deleteUser(id) > 0) {

					return "deleteok";
				}

			} catch (Exception e) {
				throw new ControllerException(e, "删除用户失败");
			}

		}

		return "nodelete";

	}

	
	/**
	 * 
	 * 功能说明：修改用户账号 状态
	 * 创建人：张木生 330140511@qq.com   
	 * 创建时间：2016年3月12日/下午2:25:51 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeUserFlag", method = RequestMethod.POST)
	@ResponseBody
	public String changeUserFlag(int userId ) throws Exception {
  
           if (userId==1)
           {
        	   
        	   return "delAdmin";
           }
      
		if (userService.findUserById(userId) != null) {
                
			try {

			     userService.changeUserFlag(userId );
			     return "changeOk";

			} catch (Exception e) {
				throw new ControllerException(e, "删除用户失败");
			}

		}

		return "noChange";

	}
	
	
	  //跳转到 增加用户界面
	@RequestMapping("/addUserInit")
	public String addUserInit(Model model) {
		
		
		
		model.addAttribute("roleList", userService.getRolesList(-1));
		
		model.addAttribute("departmentList", userService.getDepartmentList());
	

		return "manager_user/addUser";

	}

	/**
	 * 
	 * 功能说明：处理增加用户 创建人：@author 330140511@qq.com 创建时间：2015年9月10日/上午11:07:04
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUserAction")
	@ResponseBody
	public AjaxMsg addUserAction(TUser user, MultipartFile picFile, String roleids,Model model,HttpServletRequest request) throws Exception {
		 
		 String[] ids=null;
		 
		 AjaxMsg msg=new AjaxMsg();
		 
		if (request.getParameterValues("roleids") != null )
		{
		 
			ids=request.getParameterValues("roleids") ;
		}
		
	/*	log.debug("需要增加的用户信息:" + user.getUsername() +"密码: "+user.getPassword()+"生日："+ user.getBirthday()+"角色IDs："+Arrays.toString(ids))*/;

		// 判断 文件控件是否传了文件 一般情况下 字符串长度肯定大于4
		if (picFile != null  ) {
			
			
                 if (picFile.getOriginalFilename().length() > 4  )
                	 
                 {
                	 
                	// 获取图片后缀名
         			// 获取原始文件名称
         			String sourceFilename = picFile.getOriginalFilename();
         			// 取扩展名
         			String subFixName = sourceFilename.substring(sourceFilename.lastIndexOf("."));
         			// 新图片名称
         			String newFileName = UUID.randomUUID() + subFixName;

         			// 创建新图片

         			File newPicFile = new File(Constants.USEPIC_PATH + newFileName);

         			 log.debug("需要操作的文件:"+newPicFile.getName());
         			// 将接收到的图片 写入 硬盘
         			try {

         				picFile.transferTo(newPicFile);

         				// 设置 图片存储路径， windows下 newPicFile.getName()获取的是 xxx.jpg 但是linux下 获取的却是 路径加文件名，所以这里要改
         				//user.setPic(newPicFile.getName());
                         //改成
         				 user.setPic(newFileName);
         				
         			} catch (IllegalStateException e) {
                       
         				 
         				throw new ControllerException(e, "图片上传失败" + e.getMessage());
         			 

         			} catch (IOException e) {
         				// TODO Auto-generated catch block
         				throw new ControllerException(e, "图片上传失败" + e.getMessage());
         			}

                	 
                 }
 
			
		}

		// 如果没有上传图片也能 保存 ,还需要把角色编号 写入 用户 角色对应表
		
		
		userService.insertUser(user,ids,msg);

		// return "userlist";
		return  msg;

	}

	 //用户列表 主界面 
	@RequestMapping("/getUserList")
	public String getUserList( PageVo pageVo, Model model,HttpServletRequest request)

	{
         
  
			  
			   PageBean<UserVo>  pages=userService.getUsersPage(pageVo);
			 
			   
			  String loginname=(String)request.getSession().getAttribute("loginname");
			   
			  
			  model.addAttribute("loginname", loginname);
			  
			  model.addAttribute("pages", pages);
			
           
		
		
		return  "manager_user/getUserList" ;
	}
	
	
	  //进入 设置权限界面
	@RequestMapping("/setUserRightsInit")
	public String setUserRightsInit(int id, Model model) throws Exception

	{
		 //显示 所有2级 和 2级下面的所有三级模块
		   List<Map<String, Object>>  rightsList=userService.getRightsMap();
		   
		   //给前台返回所有  功能地图
		   model.addAttribute("rightMap",rightsList);
 
		   //获取 用户对应的所有权限，用在把页面上的checkbox打勾
		   String idsString=userService.getRightsByUserId(id);
		   model.addAttribute("rightIds", idsString);
    
		   model.addAttribute("userId", id); 
		   
		return  "manager_user/setUserRights" ;
	}
	
	  //响应 ajax 提交的   为个人设置 权限的请求
	@RequestMapping("/setUserRightsAction")
	@ResponseBody
	public AjaxMsg setUserRightsAction( String ids,Integer userId,HttpServletRequest request) throws Exception

	{
		 
		AjaxMsg msg=new AjaxMsg();
		 
		userService.setRightsForUser(userId, ids, msg);
	
	
   	return msg;
	}
	
	
	
	 //获取用户列表  不分页
	@RequestMapping("/getUsers")
	@ResponseBody
	public List<TUser> getUsers(  )

	{
		 List<TUser> users=new ArrayList<TUser>();
		 
		 users=userService.getUsers();
 
		
		
		return users;
	}
	
	
	
   
 /**
  * 
  * 功能说明： 根据前台传来的 图片 和裁剪坐标 存放原文件，并保存裁剪后的图片
  * 创建人：张木生 330140511@qq.com   
  * 创建时间：2015年11月14日/下午1:09:11 
  * @param request
  * @param x
  * @param y
  * @param h
  * @param w
  * @param userId  需要修改的用户id
  * @param imageFile  jsp页面的  input file 的 id
  * @return
  * @throws Exception
  */
	@RequestMapping(value = "/setUserFacePic")
	@ResponseBody
    public AjaxMsg setUserFacePic(
            HttpServletRequest request,
            @RequestParam(value = "dataX") String x,
            @RequestParam(value = "dataY") String y,
            @RequestParam(value = "dataWidth") String w,
            @RequestParam(value = "dataHeight") String h,
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "inputImage") MultipartFile imageFile
    ) throws Exception{
		
		//System.out.println(x+":"+y+":"+w+":"+h);
		
		AjaxMsg msg=new AjaxMsg();
  
        if(imageFile!=null){
        	  //判断文件类型
            if(ApplicationTools.allowUpload(imageFile.getContentType()))
            {
            	 //文件重命名
                String fileName = ApplicationTools.rename(imageFile.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0,end);
                File dir = new File(Constants.USEPIC_PATH);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,saveName+"_src.jpg");
                imageFile.transferTo(file);
                String srcImagePath = Constants.USEPIC_PATH+ saveName;
                int imageX = Integer.parseInt(x);
                int imageY = Integer.parseInt(y);
                int imageH = Integer.parseInt(h);
                int imageW = Integer.parseInt(w);
            //   System.out.println("x："+imageX+"y:"+imageY+"width:"+imageW+"height:"+h );
                ApplicationTools.imgCut(srcImagePath,imageX,imageY,imageW,imageH);
                
                msg.setCode(1);
                msg.setMsg(saveName+"_cut.jpg");
                
                TUser user=userService.findUserById(userId);
                user.setPic(saveName+"_cut.jpg");
                userService.updateUserFace(user,  msg);
            }
            else {
				  msg.setCode(0);
				  msg.setMsg("fileError");
			}
        }
        return  msg;
    }
	
	
	@RequestMapping("/changeUserPwd")
	@ResponseBody
	public  AjaxMsg changeUserPwd(Integer userid,String password)
	{
		AjaxMsg msg=new AjaxMsg();
		
		  userService.changeUserPwd(userid,password,msg);
		
		return msg;
		
		
	}
}
