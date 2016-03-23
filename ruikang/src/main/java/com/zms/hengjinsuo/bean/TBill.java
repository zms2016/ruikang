package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TBill {
    private Integer id;

    private Integer contractid;

    private Integer investid;

    private Float memony;

    private Float extmemony;

    private Date contracttime;

    private Integer flag;

    private Boolean isnext;

    private Float apr;

    private Integer longtime;

    private Date begintime;

    private String memo;

    private Integer rzbankid;

    private Integer rongziid;

    private Date firsttime;

    private Integer backtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractid() {
        return contractid;
    }

    public void setContractid(Integer contractid) {
        this.contractid = contractid;
    }

    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
    }

    public Float getMemony() {
        return memony;
    }

    public void setMemony(Float memony) {
        this.memony = memony;
    }

    public Float getExtmemony() {
        return extmemony;
    }

    public void setExtmemony(Float extmemony) {
        this.extmemony = extmemony;
    }

    public Date getContracttime() {
        return contracttime;
    }

    public void setContracttime(Date contracttime) {
        this.contracttime = contracttime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Boolean getIsnext() {
        return isnext;
    }

    public void setIsnext(Boolean isnext) {
        this.isnext = isnext;
    }

    public Float getApr() {
        return apr;
    }

    public void setApr(Float apr) {
        this.apr = apr;
    }

    public Integer getLongtime() {
        return longtime;
    }

    public void setLongtime(Integer longtime) {
        this.longtime = longtime;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getRzbankid() {
        return rzbankid;
    }

    public void setRzbankid(Integer rzbankid) {
        this.rzbankid = rzbankid;
    }

    public Integer getRongziid() {
        return rongziid;
    }

    public void setRongziid(Integer rongziid) {
        this.rongziid = rongziid;
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Integer getBacktype() {
        return backtype;
    }

    public void setBacktype(Integer backtype) {
        this.backtype = backtype;
    }
}