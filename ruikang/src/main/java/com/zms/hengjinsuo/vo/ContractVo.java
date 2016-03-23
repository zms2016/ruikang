package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;


/**
 * 
 * 功能说明： 合同包装类，需要放很多属性
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月4日/下午4:59:50
 */
public class ContractVo {
	
	private TContract contract;
	
	 //融资客户
	private TRongzi rongzi;
	//融资项目
	private TInvests invest;
	//理财经理
	private TUser  manager;
	 //融资客户的银行账号
	private TRzbank bank;
	
	//理财客户
	private TVip vip;
	
	
	public TVip getVip() {
		return vip;
	}
	public void setVip(TVip vip) {
		this.vip = vip;
	}
	public TContract getContract() {
		return contract;
	}
	public void setContract(TContract contract) {
		this.contract = contract;
	}
	public TRongzi getRongzi() {
		return rongzi;
	}
	public void setRongzi(TRongzi rongzi) {
		this.rongzi = rongzi;
	}
	public TInvests getInvest() {
		return invest;
	}
	public void setInvest(TInvests invest) {
		this.invest = invest;
	}
	public TUser getManager() {
		return manager;
	}
	public void setManager(TUser manager) {
		this.manager = manager;
	}
	public TRzbank getBank() {
		return bank;
	}
	public void setBank(TRzbank bank) {
		this.bank = bank;
	}

}
