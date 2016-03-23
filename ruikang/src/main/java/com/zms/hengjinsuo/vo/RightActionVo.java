package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TRightActions;
import com.zms.hengjinsuo.bean.TRights;

public class RightActionVo {
	
	
	private Integer id;
	
	private  TRightActions rightActions;
	
	//  给 列表 显示 该 action 的路径
   private  TRights  rights;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TRightActions getRightActions() {
		return rightActions;
	}
	public void setRightActions(TRightActions rightActions) {
		this.rightActions = rightActions;
	}
	public TRights getRights() {
		return rights;
	}
	public void setRights(TRights rights) {
		this.rights = rights;
	}
 
	
	
 
	

	
	
	

}
