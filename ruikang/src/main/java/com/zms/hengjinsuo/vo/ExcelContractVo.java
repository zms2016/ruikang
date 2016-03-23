package com.zms.hengjinsuo.vo;

public class ExcelContractVo {
	
	 //订单编号
	private int billId;
	
	 //合同编号  字符串的
	private String contractString;
	 //融资客户
	private String  rongzi;
	//融资项目
	private  String invest;
	 //投资金额
	private float  billMoney;
	//合同时间
	private String  billTime;
	 //投资期限
	private  int   billLongTime;
	//理财经理
	private  String managerName;
	//理财客户
	private String  vipName;
	//当前状态
	private String  flag;

	public String getContractString() {
		return contractString;
	}

	public void setContractString(String contractString) {
		this.contractString = contractString;
	}

	public String getRongzi() {
		return rongzi;
	}

	public void setRongzi(String rongzi) {
		this.rongzi = rongzi;
	}

	public String getInvest() {
		return invest;
	}

	public void setInvest(String invest) {
		this.invest = invest;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}
	
	

}
