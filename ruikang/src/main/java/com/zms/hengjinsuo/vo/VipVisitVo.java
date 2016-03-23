package com.zms.hengjinsuo.vo;

 

import java.util.Date;

import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bean.TVisit;

public class VipVisitVo  {

 
	
	private int    visitId;
	
	private String vipName;
	
	private Date  visitTime;
	
	private String content;
	
	private String memo;
	
	private String visitType;
	
	
	private String managerName;
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	
	
	

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	
	
	
	
	
}
