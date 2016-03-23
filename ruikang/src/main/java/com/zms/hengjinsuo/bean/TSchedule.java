package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TSchedule {
    private Integer id;

    private String contractid;

    private Integer billid;

    private Float memony;

    private Date memonytime;

    private Integer flag;

    private String vipname;

    private Integer managerid;

    private Integer investid;

    private Date backtime;

    private Boolean lastflag;

    private Integer vipid;

    private Integer rongziid;

    private Integer rzbankid;

    private Integer contractidint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid == null ? null : contractid.trim();
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public Float getMemony() {
        return memony;
    }

    public void setMemony(Float memony) {
        this.memony = memony;
    }

    public Date getMemonytime() {
        return memonytime;
    }

    public void setMemonytime(Date memonytime) {
        this.memonytime = memonytime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname == null ? null : vipname.trim();
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    public Boolean getLastflag() {
        return lastflag;
    }

    public void setLastflag(Boolean lastflag) {
        this.lastflag = lastflag;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
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

    public Integer getContractidint() {
        return contractidint;
    }

    public void setContractidint(Integer contractidint) {
        this.contractidint = contractidint;
    }
}