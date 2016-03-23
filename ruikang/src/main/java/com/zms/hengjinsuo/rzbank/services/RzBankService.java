package com.zms.hengjinsuo.rzbank.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TRongzi;
import com.zms.hengjinsuo.bean.TRzbank;
import com.zms.hengjinsuo.bean.TRzbankExample;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TRzbankMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.rongzi.services.RongziService;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.BankVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;


@Service
public class RzBankService {

	@Autowired
	private TRzbankMapper rzbankMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private RongziService rongziService;
	
	@Autowired
	private ContractService contractService;
	
	   //增加 银行账号
	public void addRzBankAction(TRzbank bank, AjaxMsg msg, HttpSession session) throws Exception
	
	{
 
		   if (rzbankMapper.insertSelective(bank)>0)
		   {
			   msg.setMsg("insertOK");
		   }
		   else {
			msg.setMsg("insertFailed");
		}
		   
		   
		
	}

	  //查询 分页  需要包装一下， 把 融资客户的名字 显示在列表中
	public PageBean<BankVo> getRzBankPages(PageVo pageVo) {
		//给前台返回所有 分页数据
		PageBean<BankVo> pageBean=new PageBean<BankVo>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 
		 	List<TRzbank>  rzbanks=new ArrayList<TRzbank>();
		 	
			List<BankVo>  vos=new ArrayList<BankVo>();
		 	
		 	
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
		

		 
		  //统计 记录数量
			 int  count=workMapper.getCountOfRzBanks(sqlString);
 
			  
			 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 rzbanks=workMapper.getRzBanks(sqlString);

	           if(rzbanks!=null)
	           {
	        	   
	        	     if (rzbanks.size()>0)
	        	     {
	        	    	     for (int i=0;i<rzbanks.size();i++)
	        	    	     {
	        	    	    	 BankVo vo=new BankVo();
	        	    	    	 
	        	    	    	 vo.setBank(rzbanks.get(i));
	        	    	    	 
	        	    	    	 int rzId=rzbanks.get(i).getRongziid();
	        	    	    	 vo.setRz(rongziService.getRongziById(rzId) );
	        	    	    	 
	        	    	    	 vos.add(vo);
	        	    	     }
	        	     }
	           }
		 
		 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 
		 pageBean.setPageDatas(vos);
		 
		  
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
	}

	
	  
	public List<TRzbank> getRzbankList() {
		 
		return  rzbankMapper.selectByExample(null);
	}

	
	  //删除
	public void deleteRzBankById(Integer id, AjaxMsg msg) {

         //先判断 合同里也没有 
		 if (contractService.checkBankHasContracts(id))
		 {
			 
			 msg.setMsg("hasContracts");
			 return;
		 }
		
		

	     if (rzbankMapper.deleteByPrimaryKey(id)>0)
	     {
	    	 
	    	 
				  msg.setMsg("deleteOK");
				}
				else {
				 msg.setMsg("deleteFailed");
				}
	     
	     
		
	}

	public TRzbank getBankById(Integer id) {
		 
		return rzbankMapper.selectByPrimaryKey(id);
	}

	public void updateRzBankAction(TRzbank bank, AjaxMsg msg) {
		 
		   if (rzbankMapper.updateByPrimaryKeySelective(bank)>0)
		   {
			   
			    msg.setMsg("updateOK");  
			   
		   }else {
			
			   msg.setMsg("updateFailed");  
			   
		}
		   
		   
	}



	public boolean checkHasBankOfRongZi(Integer id) {
		
		  TRzbankExample example=new TRzbankExample();
		    TRzbankExample.Criteria criteria=example.createCriteria();
		    criteria.andRongziidEqualTo(id);
		    List<TRzbank> banks=new ArrayList<TRzbank>();
		    banks=rzbankMapper.selectByExample(example);
		    if (banks!=null)
		    {
		    	
		    	if (banks.size()>0)
		    	{
		    		
		    		 return true;
		    	}
		    }
		    
		    return false;
		   
	}

	
	  //根据融资账户 获取所有银行列表
	public List<TRzbank> getBanksByRongzi(TRongzi rongzi) {
	 
		   TRzbankExample example=new TRzbankExample();
		   TRzbankExample.Criteria criteria=example.createCriteria();
		   criteria.andRongziidEqualTo(rongzi.getId());
		   example.setOrderByClause(" CONVERT(   name using gbk )  ");
		   
		return rzbankMapper.selectByExample(example);
	}
	
	//根据融资客户获取银行列表
	public List<TRzbank> getBanksByRongziId(int rongziId) {
	 
		  TRzbankExample example=new TRzbankExample();
		   TRzbankExample.Criteria criteria=example.createCriteria();
		   criteria.andRongziidEqualTo(rongziId);
		   example.setOrderByClause(" CONVERT(   name using gbk )  ");
		   
		return rzbankMapper.selectByExample(example);
		
	}

 

 

}
