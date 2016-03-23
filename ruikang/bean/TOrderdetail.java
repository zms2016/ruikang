package com.zms.hengjinsuo.bean;

public class TOrderdetail {
    private Integer id;

    private Integer orderId;

    private Integer productsId;

    private Integer productsNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Integer getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(Integer productsNum) {
        this.productsNum = productsNum;
    }
}