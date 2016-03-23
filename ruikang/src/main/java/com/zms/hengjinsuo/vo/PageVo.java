package com.zms.hengjinsuo.vo;

import java.util.Map;

import com.zms.util.Constants;


/**
 * 
 * 功能说明：用来封装所有  分页查询 的请求参数，jsp页面 传3个参数 currPage  pageSize  params
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月25日/下午2:41:08
 */


public class PageVo {
	
	
	   //当前需要查询的是第几页
		public int  currPage;
		
		 //每页显示数量
		public int  pageSize;
		
	
		
		  //通过 currPage 和 pageSize 计算出来
		public int  fromNum;
		
		
		/**
		 * 搜索条件
		 */
		public Map<String,Object> conditions;
		
		
		
		
		public Map<String, Object> getConditions() {
			return conditions;
		}

		public void setConditions(Map<String, Object> conditions) {
			this.conditions = conditions;
		}

		public int getCurrPage() {
			   if (currPage<1)
			   {
				    currPage=Constants.DEFAULT_CURRPAGE;
			   }
			return currPage;
		}

		public void setCurrPage(int currPage) {
			
			   if (currPage<1)
			   {
				   this.currPage=Constants.DEFAULT_CURRPAGE;
			   }
			   else {
				   this.currPage = currPage;
			}
			   
				this.fromNum=(this.currPage-1)*pageSize;
			
			
		}

		public int getPageSize() {
			  if (pageSize<1)
			  {
				  pageSize=Constants.DEFAULT_PAGESIZE;
			  }
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			
			this.fromNum=(currPage-1)*pageSize;
			this.pageSize = pageSize;
		}



		public int getFromNum() {
	 
			    if (currPage<1)
			    {
			    	currPage=1;
			    }
			   fromNum= (currPage-1)*(getPageSize());
			return fromNum;
		}

		public void setFromNum(int fromNum) {
			
			this.fromNum = fromNum;
		}
	 


	 

}
