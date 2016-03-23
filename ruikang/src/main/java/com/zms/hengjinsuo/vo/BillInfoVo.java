package com.zms.hengjinsuo.vo;

import java.util.ArrayList;
import java.util.List;

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
 * 功能说明：理财单据清单 最详细的信息
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月23日/下午3:52:41
 */
public class BillInfoVo {
	
	private TBill bill;
	
	private TContract contract;
	
	private TRongzi rongzi;
	
	private TRzbank rzbank;
	
	
	private  TVip vip;
	
	private  TUser manager;
	
	private TInvests invest;
	
	
	
	public TInvests getInvest() {
		return invest;
	}


	public void setInvest(TInvests invest) {
		this.invest = invest;
	}


	private List<TSchedule> schedules=new ArrayList<TSchedule>();


	public TBill getBill() {
		return bill;
	}


	public void setBill(TBill bill) {
		this.bill = bill;
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


	public TUser getManager() {
		return manager;
	}


	public void setManager(TUser manager) {
		this.manager = manager;
	}


	public List<TSchedule> getSchedules() {
		return schedules;
	}


	public void setSchedules(List<TSchedule> schedules) {
		this.schedules = schedules;
	}
	
	
	

}
