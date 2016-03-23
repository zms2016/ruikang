package com.zms.hengjinsuo.bean;

public class TRightTypes {
    private Integer id;

    private String name;

    private String description;

    private Boolean isuse;

    private String icopath;

    private String icopathopen;

    private Integer sortid;

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

    public Boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(Boolean isuse) {
        this.isuse = isuse;
    }

    public String getIcopath() {
        return icopath;
    }

    public void setIcopath(String icopath) {
        this.icopath = icopath == null ? null : icopath.trim();
    }

    public String getIcopathopen() {
        return icopathopen;
    }

    public void setIcopathopen(String icopathopen) {
        this.icopathopen = icopathopen == null ? null : icopathopen.trim();
    }

    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }
}