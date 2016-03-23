package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TFinanical {
    private Integer id;

    private Integer userid;

    private Date time;

    private String title;

    private String longtime;

    private String apr;

    private String backtype;

    private Integer readcount;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLongtime() {
        return longtime;
    }

    public void setLongtime(String longtime) {
        this.longtime = longtime == null ? null : longtime.trim();
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr == null ? null : apr.trim();
    }

    public String getBacktype() {
        return backtype;
    }

    public void setBacktype(String backtype) {
        this.backtype = backtype == null ? null : backtype.trim();
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}