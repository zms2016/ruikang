package com.zms.hengjinsuo.bean;

public class TRoles {
    private Integer id;

    private String name;

    private String description;

    private String rightids;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRightids() {
        return rightids;
    }

    public void setRightids(String rightids) {
        this.rightids = rightids == null ? null : rightids.trim();
    }
}