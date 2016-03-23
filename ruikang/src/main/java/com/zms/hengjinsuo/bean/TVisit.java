package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TVisit {
    private Integer id;

    private Integer managerid;

    private Date visittime;

    private String content;

    private String memo;

    private Date writetime;

    private Integer vipid;

    private Integer visittype;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Date getVisittime() {
        return visittime;
    }

    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getWritetime() {
        return writetime;
    }

    public void setWritetime(Date writetime) {
        this.writetime = writetime;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public Integer getVisittype() {
        return visittype;
    }

    public void setVisittype(Integer visittype) {
        this.visittype = visittype;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}