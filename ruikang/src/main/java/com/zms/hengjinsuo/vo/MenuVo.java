package com.zms.hengjinsuo.vo;

import java.util.List;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRights;

/**
 * 
 * 功能说明： 用来存放 后台管理主页面的 左边导航带单项
 * 创建人：330140511@qq.com  
 * 创建时间：2015年10月26日/下午2:59:51
 */
public class MenuVo     {

	private TRightTypes tRightTypes;
	
	
	 //存放 二级菜单项
	private List<TRights> rights;

	
	
	
	public List<TRights> getRights() {
		return rights;
	}

	public void setRights(List<TRights> rights) {
		this.rights = rights;
	}

	public TRightTypes gettRightTypes() {
		return tRightTypes;
	}

	public void settRightTypes(TRightTypes tRightTypes) {
		this.tRightTypes = tRightTypes;
	}
	
	
}
