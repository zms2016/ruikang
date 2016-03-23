package com.zms.hengjinsuo.vo;

import java.util.List;

import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;


 /**
  * 
  * 功能说明：  在 财务录入单据的时候 要求能显示 原先的项目和银行 同时要求能改，所以要把 项目和银行列表页返回到前面 所以需要一个包装类
  * 创建人：张木生 330140511@qq.com  
  * 创建时间：2015年11月16日/下午2:50:03
  */
public class ContractBillVo  {
	
	 
	private TContract contract;
	
	private  List<TInvests> invests;
	
	private  List<TRzbank> banks;

	public TContract getContract() {
		return contract;
	}

	public void setContract(TContract contract) {
		this.contract = contract;
	}

	public List<TInvests> getInvests() {
		return invests;
	}

	public void setInvests(List<TInvests> invests) {
		this.invests = invests;
	}

	public List<TRzbank> getBanks() {
		return banks;
	}

	public void setBanks(List<TRzbank> banks) {
		this.banks = banks;
	}
	

}
