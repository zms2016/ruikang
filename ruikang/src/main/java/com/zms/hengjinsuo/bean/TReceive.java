package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TReceive {
    private Integer id;

    private Integer investid;

    private Float backmoney;

    private Date backtime;

    private Date realtime;

    private Integer backflag;

    private Integer backsort;

    private Boolean lastflag;

    private Integer longtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
    }

    public Float getBackmoney() {
        return backmoney;
    }

    public void setBackmoney(Float backmoney) {
        this.backmoney = backmoney;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    public Date getRealtime() {
        return realtime;
    }

    public void setRealtime(Date realtime) {
        this.realtime = realtime;
    }

    public Integer getBackflag() {
        return backflag;
    }

    public void setBackflag(Integer backflag) {
        this.backflag = backflag;
    }

    public Integer getBacksort() {
        return backsort;
    }

    public void setBacksort(Integer backsort) {
        this.backsort = backsort;
    }

    public Boolean getLastflag() {
        return lastflag;
    }

    public void setLastflag(Boolean lastflag) {
        this.lastflag = lastflag;
    }

    public Integer getLongtime() {
        return longtime;
    }

    public void setLongtime(Integer longtime) {
        this.longtime = longtime;
    }
}