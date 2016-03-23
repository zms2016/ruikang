package com.zms.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

 /**
  * 
  * 功能说明：由于 kindeditor和springmvc有冲突，  springmvc获取不到 request里的多媒体列表 所以要自己写一个，判断是否是Kindetior的上传。
  * 创建人：330140511@qq.com  
  * 创建时间：2015年10月8日/上午8:48:49
  */
public class MyMultipartResolver extends CommonsMultipartResolver {
	   
    @Override
    public boolean isMultipart(HttpServletRequest request) {
    	
    /*	http://kindeditor.net/docs/upload.html  图片上传的时候提交2个参数
     *  imgFile: 文件form名称
    	dir: 上传类型，分别为image、flash、media、file*/
        String dir = request.getParameter("dir");
        if(dir!=null){  // kindeditor 上传图片的时候 不进行request 的转换
            return false;
        }
        return super.isMultipart(request);
    }
 
}