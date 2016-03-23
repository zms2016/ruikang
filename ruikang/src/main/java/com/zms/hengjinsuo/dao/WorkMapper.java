package com.zms.hengjinsuo.dao;

import java.util.List;

import com.zms.hengjinsuo.bean.TContents;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TFinanical;
import com.zms.hengjinsuo.bean.TInvests;
import com.zms.hengjinsuo.bean.TRightActions;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRights;
import com.zms.hengjinsuo.bean.TRoles;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TSchedule;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.vo.BillVo;
import com.zms.hengjinsuo.vo.ExcelBillVo;
import com.zms.hengjinsuo.vo.ExcelContractVo;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.LogVo;
import com.zms.hengjinsuo.vo.ReceiveVo;
import com.zms.hengjinsuo.vo.SqlParams;
import com.zms.hengjinsuo.vo.VipVisitVo;


/**
 * 
 * 功能说明：由于系统使用 mybatis的逆向工程，能自动生成所有基于单表的CRUD， 负责的sql 专门用一个WorkMapper.xml来配置，这里定义dao
 * 创建人：@author 330140511@qq.com  
 * 创建时间：2015年9月30日/上午11:18:29
 */
public interface WorkMapper {
	
	
    /*    用户 分页*/
    int getCountOfUsers(String  sqlString);
    List<TUser>  getUsers(String sqlString);
    
    
 
    /*    网站内容条目 分页*/
    int getCountOfContentByParams(String  sqlString);
    List<TContents>  getContentsByParams(String sqlString);
    
	 /*角色列表分页*/
    List<TRoles>  getRoles(String sqlString);
    int     getCountOfRole(String sqlString); 
    
    /*路径映射列表分页*/
    List<TRightActions>  getRightActionsByParams(String sqlString);
    int     getCountOfRightActionByParams(String sqlString);
    
    
    /* 融资项目分页*/
  int   getCountOfFinancialByParams(SqlParams params);
  List<TFinanical>  getFinancialsByParams(SqlParams params);
  
  
  
   /* 根据用户 获取 所有 一级权限列表 */
  List<TRightTypes>   getAllSuperMenusByUserId(String sqlString);
  
  /*根据用户id获取到所拥有的 所有一级权限后，遍历 获取该用户拥有的该一级目录下的 所有二级权限*/
 List<TRights> getAllSecMenuBySuperId(String sqString);
    
 
/* 菜单管理 分页 ，一级菜单 和 二级菜单*/
 
 List<TRightTypes>  getSuperMenus(String sqlString);
 int getCountOfSuperMenus(String sqlString);
 
 List<TRights>  getSecondMenus(String sqlString);
 int getCountOfSecondMenus(String sqlString);
 
 
/* 理财客户分页*/ 
 
 List<TVip>  getVips(String sqlString);
 int getCountOfVips(String sqlString);
 
/* 融资客户分页*/
 
 List<TRongzi>  getRongzis(String sqlString);
 int getCountOfRongzis(String sqlString);
 
 
 
/* 融资客户银行账号分页*/
 
 List<TRzbank>  getRzBanks(String sqlString);
 int getCountOfRzBanks(String sqlString);
 
 
 
/* 融资客户银行账号分页*/
 
 List<TInvests>  getInvests(String sqlString);
 int getCountOfInvests(String sqlString);
 
 
 
/* 合同分页*/
 
 List<TContract>  getContracts(String sqlString);
 int getCountOfContract(String sqlString);
 
 
 
  //访谈记录 分页
 List<VipVisitVo>  getVipVisitVos(String sqlString);
 int getCountOfVipVistis(String sqlString);
 
 
    //理财单据 分页
 List<BillVo>  getBillVos(String sqlString);
 int getCountOfBillVo(String sqlString);
 float getInvestSumMemonyByBill(String sqlString);
 
 
 
   //日志记录分页  
 
List<LogVo>  getLogVos(String sqlString);
int getCountOfLogVo(String sqlString);
 
 
 
/* 还款计划表分页*/
 
 List<TSchedule>  getSchedules(String sqlString);
 int getCountOfSchedule(String sqlString);
 
 
 
/* 收款计划表分页*/
 
 List<ReceiveVo>  getReceiveVos(String sqlString);
 int getCountOfReceiveVo(String sqlString);
 
 
 
/*   统计某个项目当前融资了多少钱  */
 
 float getSumOfInvest(String sqlString);
 
 
 
   //财务模块 还款计划导出
 List<ExcelScheduleVo>   getfinanceDate(String sqlString);
 
 
 
 
 // 合同导出
List<ExcelContractVo>   getContractDate(String sqlString);


// 合同导出
List<ExcelBillVo>   getBillDate(String sqlString);

 
}
