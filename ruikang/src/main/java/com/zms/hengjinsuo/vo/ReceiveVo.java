package com.zms.hengjinsuo.vo;

import java.util.Date;

/**
 * 
 *@时间: 2016年3月20日下午4:55:54
 *@作者:张木生 330140511@qq.com
 *@说明:代收款列表 实体
 */
public class ReceiveVo {
	
/*    select i.name investname,rz.name rzname,i.rzmoney rzmoney,i.apr apr,
	  i.backtype   backtype, i.contractTime contracttime,
	r.backmoney backmoney,r.backTime backtime,r.longTime longtime, r.backsort backsort,
	  r.lastFlag   lastflag,r.realTime realtime,
	 r.backFlag backflag,
	b.bankName bankname,b.bankNum banknum,r.id id
	from t_receive r left join  t_invests i    on r.investId=i.id    left join t_rongzi   rz on i.rongziId=rz.id left join   t_rzbank b on i.rzbankid=b.id
	 where  1=1 */
	
	private int id;
	private String  investname;
	
	private String rzname;
	
	private float rzmoney;
	private float apr;
	private int backtype;
	private  float backmoney;
	private Date backTime;
	private int longtime;
	private int backsort;
	private boolean lastflag;
	private int  backflag;
	private String bankname;
	private String banknum;
	
	private Date realtime;
	private Date contracttime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getRealtime() {
		return realtime;
	}
	public void setRealtime(Date realtime) {
		this.realtime = realtime;
	}
	public Date getContracttime() {
		return contracttime;
	}
	public void setContracttime(Date contracttime) {
		this.contracttime = contracttime;
	}
	public String getInvestname() {
		return investname;
	}
	public void setInvestname(String investname) {
		this.investname = investname;
	}
	public String getRzname() {
		return rzname;
	}
	public void setRzname(String rzname) {
		this.rzname = rzname;
	}
	public float getRzmoney() {
		return rzmoney;
	}
	public void setRzmoney(float rzmoney) {
		this.rzmoney = rzmoney;
	}
	public float getApr() {
		return apr;
	}
	public void setApr(float apr) {
		this.apr = apr;
	}
	public int getBacktype() {
		return backtype;
	}
	public void setBacktype(int backtype) {
		this.backtype = backtype;
	}
	public float getBackmoney() {
		return backmoney;
	}
	public void setBackmoney(float backmoney) {
		this.backmoney = backmoney;
	}
	public Date getBackTime() {
		return backTime;
	}
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	public int getLongtime() {
		return longtime;
	}
	public void setLongtime(int longtime) {
		this.longtime = longtime;
	}
	public int getBacksort() {
		return backsort;
	}
	public void setBacksort(int backsort) {
		this.backsort = backsort;
	}
	public boolean isLastflag() {
		return lastflag;
	}
	public void setLastflag(boolean lastflag) {
		this.lastflag = lastflag;
	}
	public int getBackflag() {
		return backflag;
	}
	public void setBackflag(int backflag) {
		this.backflag = backflag;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBanknum() {
		return banknum;
	}
	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}
	
	
 

	
	
}
