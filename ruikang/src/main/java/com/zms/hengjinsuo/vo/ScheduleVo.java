package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TBill;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TSchedule;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;


 /**
  * 
  * 功能说明：还款计划表 需要用到其他的类 所以要扩展一个业务类
  * 创建人：张木生 330140511@qq.com  
  * 创建时间：2015年11月10日/上午9:44:09
  */
public class ScheduleVo {

	 private TSchedule schedule;
	//合同表
	private TContract contract;
	
   //项目表
	private TInvests invest;
	
	
	//理财经理
	private  TUser manager;
	
   // 还款时间 字符串型
	private  String backTime;

	//理财客户
	 private TVip  vip;
	 
	 //融资客户
	 private TRongzi rz;
	 
	 //融资客户 银行信息
	 private TRzbank rzbank;
	 
	  //理财单据  合同金额 合同期限 要从这里读 才准确，因为合同有续签的情况
	 
	  private TBill bill;
	  
	 
	 

	public TBill getBill() {
		return bill;
	}

	public void setBill(TBill bill) {
		this.bill = bill;
	}

	public TRzbank getRzbank() {
		return rzbank;
	}

	public void setRzbank(TRzbank rzbank) {
		this.rzbank = rzbank;
	}

	public TVip getVip() {
		return vip;
	}

	public void setVip(TVip vip) {
		this.vip = vip;
	}

	public TRongzi getRz() {
		return rz;
	}

	public void setRz(TRongzi rz) {
		this.rz = rz;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public TSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(TSchedule schedule) {
		this.schedule = schedule;
	}

	public TContract getContract() {
		return contract;
	}

	public void setContract(TContract contract) {
		this.contract = contract;
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
 
	
	
}
