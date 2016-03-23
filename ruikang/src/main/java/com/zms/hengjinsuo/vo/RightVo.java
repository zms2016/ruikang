package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRights;


/**
 * 
 * 功能说明： 二级菜单管理 显示列表的时候 需要把上级菜单的名字也打出来，所以要一个包装类
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月3日/下午4:13:52
 */
public class RightVo {
	
	
	private TRights right;
	
	private TRightTypes rightType;

	public TRights getRight() {
		return right;
	}

	public void setRight(TRights right) {
		this.right = right;
	}

	public TRightTypes getRightType() {
		return rightType;
	}

	public void setRightType(TRightTypes rightType) {
		this.rightType = rightType;
	}
	
	
	

}
