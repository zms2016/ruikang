package com.zms.hengjinsuo.vo;
  public class Project {
            private Integer id;
            private String name;
            private String technology;//所用技术
            private String remarks;//备注
            //省略setter/getter
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
				this.name = name;
			}
			public String getTechnology() {
				return technology;
			}
			public void setTechnology(String technology) {
				this.technology = technology;
			}
			public String getRemarks() {
				return remarks;
			}
			public void setRemarks(String remarks) {
				this.remarks = remarks;
			}
        }