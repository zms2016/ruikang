package com.zms.hengjinsuo.application.controller;

import java.io.File;
import java.util.UUID;

import org.hamcrest.DiagnosingMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zms.util.Constants;
 
@Controller
public class FileUploadController {
	
 
	  //表单 多个 Input  file提交
	@RequestMapping("/formFilesInit")
	public String addMutilFile(  )
	{
 
		return  "fileUpload/formFiles";
	}
	
   //  上传多个文件 一定要 加 	@RequestParam 好变态！。  
	//单个文件不需要加
   // 可以接受  1个 form里多个 多个 Input  file   前台 通过 form enctype="multipart/form-data" method="post"     input type=file name="files"  多个 input file  名字 name都写成 files 提交即可 不需要额外操作
	//也可以接受 一个 input 直接选多个文件   <input type="file" name="files"    multiple="multiple"> 如果是手机 浏览器， 请使用qq浏览器。   uc和 chrome都无法选取多个文件
	@RequestMapping("/formFilesAction")
	public String formFilesAction(@RequestParam("files") MultipartFile[] files,String msg)
	{
	   
		System.out.println(msg);
		if (files != null  ) {
			
			//  System.out.println("总共"+files.length+"个input file提交");
			for(int i=0 ; i<files.length;i++)
			{
				//  System.out.println("准备处理第:"+i+"个文件");
				
				if (  files[i].getOriginalFilename().length() > 4  )
				 {
					//  System.out.println("第:"+i+"个文件域，地址栏有效 开始处理");
					  
						//根据绝对路径获取 磁盘位置
						/*		String pic_path = "D:\\tomcat-7.0.63\\webapps\\productPic\\";*/
					 
								// 获取图片后缀名
								// 获取原始文件名称
								String sourceFilename = files[i].getOriginalFilename();
								// 取扩展名
								String subFixName = sourceFilename.substring(sourceFilename.lastIndexOf("."));
								// 新图片名称
								String newFileName = UUID.randomUUID() + subFixName;
								// 创建新图片
								File newPicFile = new File(Constants.NEWSPIC_PATH + newFileName);

								// 将接收到的图片 写入 硬盘
								try {
									files[i].transferTo(newPicFile);
									 

								} catch (Exception e) {

								   e.printStackTrace();

								}
								
							
				 }
 
			}
			return "success";
		}
		   return   "fileError";
	}
		 
	 
 
	
	  //一个Input  多file提交
	@RequestMapping("/formInputFilesInit")
	public String formInputFilesInit(  )
	{

		return  "fileUpload/formInputFiles";
	}
	
	
	
	  //表单单个文件上传
	
	
	@RequestMapping("/fileAction")
	public String fileAction(MultipartFile files)
	{
		
		if (files != null  ) {
			
		 

			 if (  files.getOriginalFilename().length() > 4  )
			 {
					//根据绝对路径获取 磁盘位置
					/*		String pic_path = "D:\\tomcat-7.0.63\\webapps\\productPic\\";*/
				 
							// 获取图片后缀名
							// 获取原始文件名称
							String sourceFilename = files.getOriginalFilename();
							// 取扩展名
							String subFixName = sourceFilename.substring(sourceFilename.lastIndexOf("."));
							// 新图片名称
							String newFileName = UUID.randomUUID() + subFixName;
							// 创建新图片
							File newPicFile = new File(Constants.NEWSPIC_PATH + newFileName);

							// 将接收到的图片 写入 硬盘
							try {
								files.transferTo(newPicFile);
								 

							} catch (Exception e) {

							   e.printStackTrace();

							}
							
							return "success";
			 }
		}
		
		   return   "fileError";
	}
}
	 
 
