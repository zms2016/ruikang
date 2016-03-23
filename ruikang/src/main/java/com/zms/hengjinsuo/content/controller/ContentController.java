package com.zms.hengjinsuo.content.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.zms.exception.ControllerException;
import com.zms.hengjinsuo.bean.TContentTypes;
import com.zms.hengjinsuo.bean.TContents;
import com.zms.hengjinsuo.content.services.ContentService;
import com.zms.hengjinsuo.user.controller.UserController;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ContentVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.SqlParams;
import com.zms.util.Constants;


@Controller
@RequestMapping("/manager")
public class ContentController {

	
	private static Logger log = Logger.getLogger( ContentController.class);
	
	
	@Autowired
	private ContentService contentService;
	
	//新增内容页面
	@RequestMapping("/addContentInit")
	public String  addContentInit( Model model) throws Exception
	{
		List<TContentTypes> list=contentService.getContentTypes();
 
		
		model.addAttribute("typeList", list);
		
		 return "manager_content/addContent";
	
	}
	
	
	//新增内容 
	@RequestMapping("/addContentAction")
	@ResponseBody
	public  AjaxMsg  addContentAction(TContents contents,MultipartFile picFile, Model model  ) throws Exception
	{
	 
		 AjaxMsg msg=new AjaxMsg();
		 
        
	 
       	// 判断 文件控件是否传了文件 一般情况下 字符串长度肯定大于4
			if (picFile != null  ) {

				 if (  picFile.getOriginalFilename().length() > 4  )
				 {
						//根据绝对路径获取 磁盘位置
						/*		String pic_path = "D:\\tomcat-7.0.63\\webapps\\productPic\\";*/
					 
								// 获取图片后缀名
								// 获取原始文件名称
								String sourceFilename = picFile.getOriginalFilename();
								// 取扩展名
								String subFixName = sourceFilename.substring(sourceFilename.lastIndexOf("."));
								// 新图片名称
								String newFileName = UUID.randomUUID() + subFixName;
								// 创建新图片
								File newPicFile = new File(Constants.NEWSPIC_PATH + newFileName);

								// 将接收到的图片 写入 硬盘
								try {
									picFile.transferTo(newPicFile);
									 //添加图片
									contents.setImagefilename(newFileName);

								} catch (IllegalStateException e) {

									throw new ControllerException(e, "图片上传失败" + e.getMessage());

								} catch (IOException e) {
									// TODO Auto-generated catch block
									throw new ControllerException(e, "图片上传失败" + e.getMessage());
								}
				 }
			}
  
                contentService.saveContent(contents,msg) ;
 
				return  msg;
 
	}
	
	
	//获取子栏目 主要用来 2级菜单联动
	@RequestMapping("/getContentTypesByParentId")
	@ResponseBody
	public List<TContentTypes>  getContentTypesByParentId(Integer parentId ) throws Exception
	{
	 
		 List<TContentTypes> childTypes=new ArrayList<TContentTypes>();
		 
		 childTypes=contentService.getContentTypesByParentId(parentId);
		 
		 
		 
			
		 return childTypes;
		 
	
	}
	
	
	//获取内容列表
	@RequestMapping("/getContentList")
	public  String  getContentList( SqlParams sqlParams,Model model ) throws Exception
	{
		
	 
		/*用来给前台 返回 类型选择框*/
		List<TContentTypes> list=contentService.getContentTypes();
		model.addAttribute("typeList", list);
		
		/*为了保留 搜索后  下拉框的值保持，所以需要把二级目录也一起读回去*/
		if (sqlParams.getParentId() !=null)
		{
			 if (sqlParams.getParentId()>0)
			 {
					List<TContentTypes> childList=contentService.getContentTypesByParentId(sqlParams.getParentId());
					model.addAttribute("childList", childList);
			 }
		}
	
		
		/* 返回分页数据*/
		 PageBean<ContentVo>  pages=contentService.getContentPageBean(sqlParams);
		 model.addAttribute("pages", pages);
		
		Gson gson=new Gson();
	 
		
		return "manager_content/getContentList";
	}
	
	
	//获取单条内容
	@RequestMapping("/getContentInfo")
 
	public  String  getContentInfo(Integer itemId,Model model ) throws Exception
	{
 
	  
	   TContents   content   =contentService.getContentsById(itemId);
		
		model.addAttribute("content", content);
		

		
		return "manager_content/getContentInfo";
	}
	
	
	
	//进入 内容的编辑页面
	@RequestMapping("/updateContentInit")
	 public  String  updateContentInit(Integer itemId,Model model ) throws Exception
	{
 
	  
	   TContents   content   =contentService.getContentsById(itemId);
		model.addAttribute("content", content);
		
		    //获取所有一级目录
	    	List<TContentTypes> list=contentService.getContentTypes();
	    	 //给前台返回所有一级目录
			model.addAttribute("typeList", list);
			 
			// 根据文章 ID  获取所有二级目录
			  
			//第一步 获取到 文章所属的 父目录
			  TContentTypes   mySelfType=contentService.getTypeInfoById(content.getTypeid());
 
			  TContentTypes  fartherType =contentService.getTypeInfoById(mySelfType.getParentid());
	            //第二部 获取 父目录下所有目录
			  List<TContentTypes> childList =new ArrayList<TContentTypes>();
			  childList=contentService.getContentTypesByParentId(fartherType.getId());
			  
			   //返回 所有 二级目录
			  model.addAttribute("childList", childList);
			  
			  //返回 一级目录 和二级目录的编号
			  model.addAttribute("parentId", fartherType.getId());
			  model.addAttribute("typeId",mySelfType.getId());
			  
			 
			  Gson gson=new Gson();
		 
			  
			  
		return "manager_content/updateContent";
	}
	
	
	//修改内容 
	@RequestMapping("/updateContentAction")
	@ResponseBody
	public  AjaxMsg    updateContentAction(TContents content ,MultipartFile picFile,Model model) throws Exception
	{
	 
           
	 
         AjaxMsg msg=new AjaxMsg();
           
        // 判断 文件控件是否传了文件 一般情况下 字符串长度肯定大于4
   		if (picFile != null  ) {
   		 
   			    if ( picFile.getOriginalFilename().length() > 4 )
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

   		   				File newPicFile = new File(Constants.NEWSPIC_PATH  + newFileName);
   		   				picFile.transferTo(newPicFile);

   		   		     	// 删除老图片
   		   				TContents contentDB=new TContents();
   		   				contentDB=contentService.getContentsById(content.getId());
   		   	 
   		   				String oldPicName = contentDB.getImagefilename();
   		   				File oldPicFile = new File(Constants.NEWSPIC_PATH  + oldPicName);
   		   				oldPicFile.delete();
   		   				

   		   				// 为 图片 属性赋值
   		   				 
   		   				content.setImagefilename(newFileName);

   		   			} catch (IllegalStateException e) {

   		   				throw new ControllerException(e, "图片上传失败" + e.getMessage());

   		   			} catch (IOException e) {
   		   				// TODO Auto-generated catch block
   		   				throw new ControllerException(e, "图片上传失败" + e.getMessage());
   		   			}
   			    	
   			    }

   		
   		}
 
           contentService.updateContent(content,msg) ;
  
			return  msg;
		 
		
	
	}
	
	
	
	//设置是否显示， 主要用在 列表中 的 checkbox 选中事件 
	@RequestMapping("/updateContentShowFlag")
	@ResponseBody
	public AjaxMsg  updateContentShowFlag(Integer id,Integer flag) throws Exception
	{
	 
		
       
	 
          AjaxMsg msg=new AjaxMsg();
           
           contentService.updateFlag(id,flag,msg);
		  
            
             
		 return  msg;
	
	}
	
	
	
	//删除
	@RequestMapping("/deleteContent")
	@ResponseBody
	public AjaxMsg  deleteContent(Integer id) throws Exception
	{
	 
		
           
            AjaxMsg msg=new AjaxMsg();
            
            
            contentService.deleteById(id,msg);
            
              //删除 文章图片
           
    
            
            
            return msg;
             
	
	}
	
}
