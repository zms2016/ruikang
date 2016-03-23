package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TInvests {
    private Integer id;

    private String name;

    private String investsid;

    private Integer rongziid;

    private Integer rzbankid;

    private Float rzmoney;

    private Integer backtype;

    private Date contracttime;

    private Date lastbacktime;

    private String memo;

    private Integer flag;

    private Float needmoney;

    private Date begintime;

    private Short longtime;

    private Date firsttime;

    private Float apr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInvestsid() {
        return investsid;
    }

    public void setInvestsid(String investsid) {
        this.investsid = investsid == null ? null : investsid.trim();
    }

    public Integer getRongziid() {
        return rongziid;
    }

    public void setRongziid(Integer rongziid) {
        this.rongziid = rongziid;
    }

    public Integer getRzbankid() {
        return rzbankid;
    }

    public void setRzbankid(Integer rzbankid) {
        this.rzbankid = rzbankid;
    }

    public Float getRzmoney() {
        return rzmoney;
    }

    public void setRzmoney(Float rzmoney) {
        this.rzmoney = rzmoney;
    }

    public Integer getBacktype() {
        return backtype;
    }

    public void setBacktype(Integer backtype) {
        this.backtype = backtype;
    }

    public Date getContracttime() {
        return contracttime;
    }

    public void setContracttime(Date contracttime) {
        this.contracttime = contracttime;
    }

    public Date getLastbacktime() {
        return lastbacktime;
    }

    public void setLastbacktime(Date lastbacktime) {
        this.lastbacktime = lastbacktime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Float getNeedmoney() {
        return needmoney;
    }

    public void setNeedmoney(Float needmoney) {
        this.needmoney = needmoney;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Short getLongtime() {
        return longtime;
    }

    public void setLongtime(Short longtime) {
        this.longtime = longtime;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Float getApr() {
        return apr;
    }

    public void setApr(Float apr) {
        this.apr = apr;
    }
}