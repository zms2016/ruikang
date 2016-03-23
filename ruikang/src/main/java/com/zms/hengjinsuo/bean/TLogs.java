package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TLogs {
    private Integer id;

    private Integer managerid;

    private Integer typeflag;

    private String content;

    private Date moditime;

    private String sourceip;

    private String accessurl;

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

    public Integer getTypeflag() {
        return typeflag;
    }

    public void setTypeflag(Integer typeflag) {
        this.typeflag = typeflag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getModitime() {
        return moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip == null ? null : sourceip.trim();
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl == null ? null : accessurl.trim();
    }
}