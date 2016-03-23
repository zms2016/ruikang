package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TUser;

/**
 * 
 * 功能说明： 需要对 角色 快速绑定 和解除绑定 用户，所以增加一个 vo类, 对应类  getUserListByRoleId
 * 创建人：330140511@qq.com  
 * 创建时间：2015年10月27日/上午9:49:00
 */
public class RoleHasUserVo {
	
	
	private TUser  user;
	
	//是否已经绑定
	private  boolean hasBinded;
	
	//角色id
	private  int roleId;

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public boolean isHasBinded() {
		return hasBinded;
	}

	public void setHasBinded(boolean hasBinded) {
		this.hasBinded = hasBinded;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	

}
