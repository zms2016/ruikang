package com.zms.hengjinsuo.bean;

public class TProductProvider {
    private Integer id;

    private String name;

    private String countroy;

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

    public String getCountroy() {
        return countroy;
    }

    public void setCountroy(String countroy) {
        this.countroy = countroy == null ? null : countroy.trim();
    }
}