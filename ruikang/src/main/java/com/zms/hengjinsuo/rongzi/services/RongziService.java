package com.zms.hengjinsuo.rongzi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRongziExample;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TRzbankExample;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TRongziMapper;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.invest.services.InvestService;
import com.zms.hengjinsuo.rzbank.services.RzBankService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;

@Service
public class RongziService {

	@Autowired
	private TRongziMapper rzMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	
	@Autowired
	private TRzbankMapper rzbankMapper;
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private RzBankService rzBankService;
	
	@Autowired
	private InvestService investService;
	
	   //增加 银行账号
 
	public void addRongzi(TRongzi rz, AjaxMsg msg) {
		
		   if (rzMapper.insertSelective(rz)>0)
		   {
			   msg.setMsg("insertOK");
		   }
		   else {
			msg.setMsg("insertFailed");
		}
		
	}
    // 融资客户分页
	public PageBean<TRongzi> getRongziPages(PageVo pageVo) {
		//给前台返回所有 分页数据
				PageBean<TRongzi> pageBean=new PageBean<TRongzi>();
				 //给前台返回 搜索条件
				 Map<String,Object> conditions=new HashMap<String, Object>();
				 //执行的sql语句
				String sqlString="";
				
				  //开始吧 条件里的参数 和值 取出来
				if ( pageVo.conditions!=null)
				{
					
					 if (pageVo.conditions.get("name")!=null)
					 {
						 sqlString=sqlString+" and  name like  '%"+pageVo.conditions.get("name")+"%'";
						 
						 conditions.put("name", pageVo.conditions.get("name"));
						 
					 }
		  
				}
				
			 	List<TRongzi>  rongzis=new ArrayList<TRongzi>();
				 
				  //统计 记录数量
					 int  count=workMapper.getCountOfRongzis(sqlString);
			 
		       
				  //查询 本次查询的 记录集 
			/*		  if (pageVo.currPage<1)
					  {
						  pageVo.setCurrPage(1);
					  }*/
					 
					  
					 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
					 rongzis=workMapper.getRongzis(sqlString);

			   
				 
				 
				 pageBean.setCurrPage(pageVo.getCurrPage());
				 pageBean.setPageSize(pageVo.getPageSize());
				  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
				 pageBean.setTotalRecords(count);
				 
				 pageBean.setPageDatas(rongzis);
				 
				  
				 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
				// pageBean.setParams(sqlParams.getParams());*/
				  pageBean.setConditions(conditions);
				  
				  return pageBean;
	}
	
	  //删除记录
	public void deleteRongziById(Integer id, AjaxMsg msg) {
		 
		      
		 //先判断有没有 银行账户绑定到本账号
		
		 if ( rzBankService.checkHasBankOfRongZi(id))
		 {
			 
			 msg.setMsg("hasBanks");
			 return ;
		 }
		  
		 
		 //再判断 项目有没有绑定，因为 新建项目的时候 不需要银行账户
		  if (investService.checkHasRongzi(id))
		  {
			    msg.setMsg("hasInvest");
			    return;
		  }
		
		
		
		     if (rzMapper.deleteByPrimaryKey(id)>0)
		     {
		    	 
		    	 
					  msg.setMsg("deleteOK");
					}
					else {
					 msg.setMsg("deleteFailed");
					}
		   }
	
	  //根据主键 获取实体
	public TRongzi getRongziById(Integer id) {
		 
		return  rzMapper.selectByPrimaryKey(id);
	}
	
	
	
	public void updateRongziAction(TRongzi rongzi, AjaxMsg msg) {
		 
		   if (rzMapper.updateByPrimaryKeySelective(rongzi)>0)
		   {
			   
			   msg.setMsg("updateOK");
		   }else {
			
			    msg.setMsg("updateFailed");
		}
		
	}
	
	//获取 融资客户列表  flag 确定是获取所有的 还是 正常的账户
	  //true 表示获取正常客户  false 表示所有客户
	public List<TRongzi> getRongzis(Boolean flag)
	{
		
		List<TRongzi> rzsList=new ArrayList<TRongzi>();
		
		TRongziExample example=new TRongziExample();
		TRongziExample.Criteria criteria=example.createCriteria();
		
	 
		 if (flag)
		 {
			 criteria.andFlagEqualTo( 0);
		 }
		 
		 rzsList=rzMapper.selectByExample(example);
		 return   rzsList;
	}
	
	
	  //根据合同编号 获取 融资客户
	public TRongzi getRongziByContractId(Integer contractid) {
		
		TContract contract=new TContract();
		
		contract=contractService.getContractById(contractid);
		
		 
		
		return rzMapper.selectByPrimaryKey(contract.getRongziid());
	}
	
 
	
 
	
		
 

}
