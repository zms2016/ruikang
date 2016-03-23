package com.zms.hengjinsuo.bean;

public class TRzbank {
    private Integer id;

    private String name;

    private String bankname;

    private String banknum;

    private Integer rongziid;

    private String memo;

    private Integer flag;

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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBanknum() {
        return banknum;
    }

    public void setBanknum(String banknum) {
        this.banknum = banknum == null ? null : banknum.trim();
    }

    public Integer getRongziid() {
        return rongziid;
    }

    public void setRongziid(Integer rongziid) {
        this.rongziid = rongziid;
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
}