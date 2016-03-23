package com.zms.hengjinsuo.vo;

import java.util.Date;



 //导出到EXCEL 中的数据  
public class ExcelScheduleVo     {
	
	 //合同编号  字符串的
	private String contractString;
	 //投资金额
	private float  billMoney;
	//合同时间
	private String  billTime;
	 //投资期限
	private  int   billLongTime;
	//融资客户 和银行账号
	private String  rongzi;
	private String  investName;  //项目名称
	private  String rzBankName;
	private  String  rzBankNum;
	//理财经理
	private  String managerName;
	private String  vipName;
	
	private String backType;
   //理财客户银行账号
	private String vipBankName;
	private String vipBankNum;
	//还款时间
	private String backTime;
	 //还款金额
	private float backMoney;
	//当前状态
	private String  flag;
	
	
	
	
	
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}
	public String getInvestName() {
		return investName;
	}
	public void setInvestName(String investName) {
		this.investName = investName;
	}
	public String getContractString() {
		return contractString;
	}
	public void setContractString(String contractString) {
		this.contractString = contractString;
	}
	public float getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(float billMoney) {
		this.billMoney = billMoney;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	public int getBillLongTime() {
		return billLongTime;
	}
	public void setBillLongTime(int billLongTime) {
		this.billLongTime = billLongTime;
	}
	public String getRongzi() {
		return rongzi;
	}
	public void setRongzi(String rongzi) {
		this.rongzi = rongzi;
	}
	public String getRzBankName() {
		return rzBankName;
	}
	public void setRzBankName(String rzBankName) {
		this.rzBankName = rzBankName;
	}
	public String getRzBankNum() {
		return rzBankNum;
	}
	public void setRzBankNum(String rzBankNum) {
		this.rzBankNum = rzBankNum;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getVipBankName() {
		return vipBankName;
	}
	public void setVipBankName(String vipBankName) {
		this.vipBankName = vipBankName;
	}
	public String getVipBankNum() {
		return vipBankNum;
	}
	public void setVipBankNum(String vipBankNum) {
		this.vipBankNum = vipBankNum;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public float getBackMoney() {
		return backMoney;
	}
	public void setBackMoney(float backMoney) {
		this.backMoney = backMoney;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	 
  

}
