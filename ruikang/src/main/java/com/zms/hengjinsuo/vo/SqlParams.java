package com.zms.hengjinsuo.vo;


 /**
  * 
  * 功能说明： 用来封装  查询条件  支持分页
  * 创建人：330140511@qq.com  
  * 创建时间：2015年10月8日/上午11:54:44
  */
public class SqlParams  extends PageVo{
 
	
	 //一级类别父id  （一级菜单）
	private Integer parentId;
	
	 //二级类别ID   （二级菜单）
	private Integer typeid;
	
	//标题
	private  String filetitle;
	
	
	//用于 项目搜索中的 供应商ID
	private  Integer providerId;
	
	//查询条件字符串
	   private  String params;
	
	
	
	 public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}


		

 
 
 

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

 
	
	
	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getFiletitle() {
		return filetitle;
	}

	public void setFiletitle(String filetitle) {
		this.filetitle = filetitle;
	}

	public String getParams() {
		
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
 
	

}
