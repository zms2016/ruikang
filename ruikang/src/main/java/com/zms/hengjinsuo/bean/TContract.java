package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TContract {
    private Integer id;

    private String contractid;

    private Integer investid;

    private Integer rongziid;

    private Integer rzbankid;

    private Date createcontracttime;

    private Date getcontracttime;

    private Date putcontracttime;

    private Float maybememony;

    private Float memony;

    private Integer managerid;

    private Integer vipid;

    private Integer flag;

    private String memo;

    private String managername;

    private String invertname;

    private String rongziname;

    private Integer longtime;

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

    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
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

    public Date getCreatecontracttime() {
        return createcontracttime;
    }

    public void setCreatecontracttime(Date createcontracttime) {
        this.createcontracttime = createcontracttime;
    }

    public Date getGetcontracttime() {
        return getcontracttime;
    }

    public void setGetcontracttime(Date getcontracttime) {
        this.getcontracttime = getcontracttime;
    }

    public Date getPutcontracttime() {
        return putcontracttime;
    }

    public void setPutcontracttime(Date putcontracttime) {
        this.putcontracttime = putcontracttime;
    }

    public Float getMaybememony() {
        return maybememony;
    }

    public void setMaybememony(Float maybememony) {
        this.maybememony = maybememony;
    }

    public Float getMemony() {
        return memony;
    }

    public void setMemony(Float memony) {
        this.memony = memony;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername == null ? null : managername.trim();
    }

    public String getInvertname() {
        return invertname;
    }

    public void setInvertname(String invertname) {
        this.invertname = invertname == null ? null : invertname.trim();
    }

    public String getRongziname() {
        return rongziname;
    }

    public void setRongziname(String rongziname) {
        this.rongziname = rongziname == null ? null : rongziname.trim();
    }

    public Integer getLongtime() {
        return longtime;
    }

    public void setLongtime(Integer longtime) {
        this.longtime = longtime;
    }
}