package com.zms.hengjinsuo.vo;

import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;


/**
 * 
 * 功能说明：银行账号 包装类
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年11月3日/上午11:10:02
 */
public class BankVo {
	
	private  TRzbank bank;
	
	private  TRongzi rz;

	public TRzbank getBank() {
		return bank;
	}

	public void setBank(TRzbank bank) {
		this.bank = bank;
	}

	public TRongzi getRz() {
		return rz;
	}

	public void setRz(TRongzi rz) {
		this.rz = rz;
	}
	
	

}
