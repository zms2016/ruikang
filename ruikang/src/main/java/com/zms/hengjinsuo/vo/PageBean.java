package com.zms.hengjinsuo.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageBean<T> {
	
	private int currPage = 1; //当前页
	private int totalPages = 1; // 总页数
	
	  //单个查询条件的情况直接使用  params
	 private String params=""; 
	 //多个查询条件的情况 使用 conditions
	private  Map<String,Object> conditions;
	
	private int pageSize = 10; // 每页记录数
	private int totalRecords = 0; // 总记录数
	
	
	
	private boolean isHavePrePage = false; // 是否有上一页
	private boolean isHaveNextPage = false; // 是否有下一页
	
	

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

	private List<T> pageDatas = new ArrayList<T>();

 
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
	
		if (totalRecords < 0) {
			throw new RuntimeException("总记录数不能小于0!");
		}
		
		// 设置总记录数
		this.totalRecords = totalRecords;
		
		// 计算总页数
		this.totalPages = this.totalRecords / this.pageSize;
		if (this.totalRecords % this.pageSize != 0) {
			this.totalPages++;
		}
		// 计算是否有上一页
		if (this.currPage > 1) {
			this.isHavePrePage = true;
		} else {
			this.isHavePrePage = false;
		}
		// 计算是否有下一页
		if (this.currPage < this.totalPages) {
			this.isHaveNextPage = true;
		} else {
			this.isHaveNextPage = false;
		}
	}

	public List<T> getPageDatas() {
		return pageDatas;
	}

	public void setPageDatas(List<T> pageDatas) {
		this.pageDatas = pageDatas;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean getIsHavePrePage() {
		return isHavePrePage;
	}

	public boolean getIsHaveNextPage() {
		return isHaveNextPage;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}