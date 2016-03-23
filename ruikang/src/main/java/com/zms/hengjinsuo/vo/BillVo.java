package com.zms.hengjinsuo.vo;

public class BillVo {
	
	
 
	
	private int id;
	
	private String contractId;
	
	private float money;
	
	private String contractTime;
	
	private int longTime;
	
	private  float apr;
	
	private String flag;
	
	private String manager;
	private String vipName;
	private String investName;
	private String rzName;
	private float rzMoney;
	
	
	//是理财单据的还款类型 不是融资项目的还款类型
	private String backType;
	
	
	
	public String getRzName() {
		return rzName;
	}
	public void setRzName(String rzName) {
		this.rzName = rzName;
	}
	public float getRzMoney() {
		return rzMoney;
	}
	public void setRzMoney(float rzMoney) {
		this.rzMoney = rzMoney;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getContractTime() {
		return contractTime;
	}
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	public int getLongTime() {
		return longTime;
	}
	public void setLongTime(int longTime) {
		this.longTime = longTime;
	}
	public float getApr() {
		return apr;
	}
	public void setApr(float apr) {
		this.apr = apr;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getInvestName() {
		return investName;
	}
	public void setInvestName(String investName) {
		this.investName = investName;
	}
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}


}
