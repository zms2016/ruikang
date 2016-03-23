package com.zms.hengjinsuo.bean;

import java.util.Date;

public class TFundpool {
    private Integer id;

    private Float money;

    private String md5;

    private Date time;

    private Float fundpool;

    private Short flow;

    private Short flowtype;

    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Float getFundpool() {
        return fundpool;
    }

    public void setFundpool(Float fundpool) {
        this.fundpool = fundpool;
    }

    public Short getFlow() {
        return flow;
    }

    public void setFlow(Short flow) {
        this.flow = flow;
    }

    public Short getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(Short flowtype) {
        this.flowtype = flowtype;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}