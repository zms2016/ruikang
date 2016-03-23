package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TContentTypes;
import com.zms.hengjinsuo.bean.TContents;


/**
 * 
 * 功能说明：新闻列表  需要增加一个栏目  所属类别“ 一级菜单--二级菜单”  所有 要增加一个 vo类来扩展 新闻类
 * 创建人：330140511@qq.com  
 * 创建时间：2015年10月27日/上午9:47:44
 */
public class ContentVo   {
	
 
	private TContents content;
	
	//  自己的 类型信息
	private TContentTypes typeInfo;
	  //父节点的类型信息 
	private TContentTypes fartherTypeInfo;
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public TContentTypes getFartherTypeInfo() {
		return fartherTypeInfo;
	}

	public void setFartherTypeInfo(TContentTypes fartherTypeInfo) {
		this.fartherTypeInfo = fartherTypeInfo;
	}

	public TContents getContent() {
		return content;
	}

	public void setContent(TContents content) {
		this.content = content;
	}



	public TContentTypes getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(TContentTypes typeInfo) {
		this.typeInfo = typeInfo;
	}
	
	
	
	

}
