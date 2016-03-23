package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TUser;

public class UserVo {
	
	private TUser user;
	
	//部门名称 在用户列表中要显示
	private String departmentName;
	

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

}
