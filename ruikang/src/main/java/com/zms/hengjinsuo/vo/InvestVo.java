package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TInvests;

public class InvestVo {
	
	private  TInvests invest;
	
	//由于 Javascript转日期 麻烦 
	
	//合同时间
	private  String htTime;
	
	//最迟还款时间
	private  String lastTime;
	
	//融资金额
	private  String rzMoney;
	
	//融资金额
	private  String needMoney;;
	
	 public String getRzMoney() {
		return rzMoney;
	}

	public String getNeedMoney() {
		return needMoney;
	}

	public void setNeedMoney(String needMoney) {
		this.needMoney = needMoney;
	}

	public void setRzMoney(String rzMoney) {
		this.rzMoney = rzMoney;
	}

	//融资人姓名
	private String rzName;

	public TInvests getInvest() {
		return invest;
	}

	public String getHtTime() {
		return htTime;
	}

	public void setHtTime(String htTime) {
		this.htTime = htTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public void setInvest(TInvests invest) {
		this.invest = invest;
	}

	public String getRzName() {
		return rzName;
	}

	public void setRzName(String rzName) {
		this.rzName = rzName;
	}
	

}
