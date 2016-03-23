package com.zms.hengjinsuo.vip.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TContentsExample;
import com.zms.hengjinsuo.bean.TContract;
import com.zms.hengjinsuo.bean.TContractExample;
import com.zms.hengjinsuo.bean.TRightTypes;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.bean.TUserExample;
import com.zms.hengjinsuo.bean.TVip;
import com.zms.hengjinsuo.bean.TVipExample;
import com.zms.hengjinsuo.bean.TVisit;
import com.zms.hengjinsuo.contract.services.ContractService;
import com.zms.hengjinsuo.dao.TUserMapper;
import com.zms.hengjinsuo.dao.TVipMapper;
import com.zms.hengjinsuo.dao.TVisitMapper;
import com.zms.hengjinsuo.dao.WorkMapper;
import com.zms.hengjinsuo.vo.AjaxMsg;
import com.zms.hengjinsuo.vo.ExcelScheduleVo;
import com.zms.hengjinsuo.vo.PageBean;
import com.zms.hengjinsuo.vo.PageVo;
import com.zms.hengjinsuo.vo.ScheduleVo;
import com.zms.hengjinsuo.vo.VipVisitVo;
import com.zms.hengjinsuo.vo.VipVo;


/**
 * 
 * 功能说明：处理 理财客户相关业务
 * 创建人：张木生 330140511@qq.com  
 * 创建时间：2015年10月30日/下午3:32:48
 */

@Service
public class VipService {

	private static Logger log = Logger.getLogger( VipService.class);
	
	@Autowired
	private TVipMapper vipMapper;
	
	@Autowired
	private WorkMapper workMapper;
	
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private TVisitMapper visitMapper;
	
	
	public void addVipAction(TVip vip, AjaxMsg msg ) {
		 
	 
		
		   if (vipMapper.insertSelective(vip)>0)
		   {
			   msg.setMsg("insertOK");
		   }
		   else {
			msg.setMsg("insertFailed");
		}
	}


    //理财客户分页， 要根据登录账号 来判断是显示什么数据
 
	public PageBean<VipVo> getVipPages(PageVo pageVo,int loginId) {
	 
		 //给前台返回所有 分页数据
			PageBean<VipVo> pageBean=new PageBean<VipVo>();
			 //给前台返回 搜索条件
			 Map<String,Object> conditions=new HashMap<String, Object>();
			 //执行的sql语句
			String sqlString="";
			
		  
			  if (    loginId>0 )
			  {
			 
					 sqlString=sqlString+" and  managerid = "+loginId+" ";
					 conditions.put("manager", String.valueOf(loginId));
			} 
			
			
			List<TVip>  vips=new ArrayList<TVip>();
			
			List<VipVo>  vos=new ArrayList<VipVo>();
			
			  //开始吧 条件里的参数 和值 取出来
			if ( pageVo.conditions!=null)
			{
				
				 if (pageVo.conditions.get("name")!="")
				 {
					 String name=(String)pageVo.conditions.get("name");
					 
				 	 sqlString=sqlString+" and  name =  '"+name+"'";
					  //给前台返回条件
					 conditions.put("name", name);
					 
					 
				 }
				 
				 if (pageVo.conditions.get("manager")!=null  && pageVo.conditions.get("manager")!="")
				 {
					 int  managerid=( Integer.valueOf( (String)pageVo.conditions.get("manager")  ) );
		 
					  if (managerid>0)
					  {
						 	 sqlString=sqlString+" and  managerid = "+managerid+"";
							  //给前台返回条件
							 conditions.put("manager", pageVo.conditions.get("manager"));
					  }
					 
			
					 
					 
				 }
				 
				 
 
			}
	 
			     //统计数量
			
			   int count=workMapper.getCountOfVips(sqlString);
				  
				 sqlString=sqlString+"  limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
				 
				 vips=workMapper.getVips(sqlString);
		        
				 if(vips!=null)
				 {
					 
					 if (vips.size()>0)
					 {
						 
						 for (int i=0;i<vips.size();i++)
						 {
							 
							 VipVo vo=new VipVo();
							 vo.setVip(vips.get(i));
							 
							 int managerId=vips.get(i).getManagerid();
							 
							 String managerName=userMapper.selectByPrimaryKey(managerId).getRealname();
							 vo.setManagerName(managerName);
						//	 客户级别， 1共有   2理财经理私有   3理财经理级别查看
							  /*switch (vips.get(i).getLevel()) {
							case 1:
							{
								vo.setLevel("公共客户");
								break;
							}
							case 2:
							{
								vo.setLevel("理财经理专有");
								break;
							}
								 

							default:
							{
								vo.setLevel("公共客户");
								break;
							}
						
							}*/
							  vos.add(vo);
							 
						 }
						 pageBean.setPageDatas(vos);
					 }
				 }
			 
			 
			 pageBean.setCurrPage(pageVo.getCurrPage());
			 pageBean.setPageSize(pageVo.getPageSize());
			  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
			 pageBean.setTotalRecords(count);
			 
			
			 
			  
			 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
			// pageBean.setParams(sqlParams.getParams());*/
			  pageBean.setConditions(conditions);
			  
			  return pageBean;
	}



 //根据编号删除客户
	public void deleteVipById(Integer id, AjaxMsg msg) {
		 
		
		  //合同列表中的话就不能删除
		   if ( contractService.checkVipHasContract(id))
		   {
			   
			   msg.setMsg("hasContract");
			   return;
		   }
		
		  if ( vipMapper.deleteByPrimaryKey(id)>0)
		  {
			    msg.setMsg("deleteOK");
		  } else
		  {
			  msg.setMsg("deleteFailed");
		  }
		
	}

      //获取 实体
	public TVip getVipById(Integer id) {
		 
		return  vipMapper.selectByPrimaryKey(id);
	}


	   //返回 包装类
	public VipVo getVipVoById(Integer id) {
	  
		 TVip vip=vipMapper.selectByPrimaryKey(id);
		 
		 String managerName=userMapper.selectByPrimaryKey(vip.getManagerid()).getRealname();
		 
		 VipVo vo=new VipVo();
		 
		 vo.setVip(vip);
        vo.setManagerName(managerName);		
        
    	//	 客户级别， 1共有   2理财经理私有   3理财经理级别查看
		  switch (vip.getLevel()) {
		case 1:
		{
			vo.setLevel("公共客户");
			break;
		}
		case 2:
		{
			vo.setLevel("理财经理专有");
			break;
		}
			 

		default:
		{
			vo.setLevel("公共客户");
			break;
		}
	
		}
		  
		  return vo;
		  
	}

   //修改理财客户信息
	public void updateVipAction(TVip vip, AjaxMsg msg) {
	 
		   if (vipMapper.updateByPrimaryKeySelective(vip)>0){
			   
			   msg.setMsg("updateOK");
		   }
		   else
		   {
			   msg.setMsg("updateFailed");
		   }
		
	}

  //获取 理财客户列表  0 表示获取所有 vip列表， 大于0 获取 理财经理为 Id 的vips列表
	public  List<TVip> getVips(int managerId) {
		
		
	     TVipExample example=new TVipExample();
			
		TVipExample.Criteria criteria=example.createCriteria();
		
	          if (managerId>0)
	          {
	        	  criteria.andManageridEqualTo(managerId);
	          }
 
		     example.setOrderByClause("  CONVERT(   name using gbk )  ");
		
		return vipMapper.selectByExample(example);
	}


	 //根据合同 字符串号 查找 理财客户
	public TVip getVipByContrantId(String contractid) {
		  
		    TVip vip =new TVip();
		   
            TContract contract=contractService.getContractByStrId(contractid);
            
            vip=vipMapper.selectByPrimaryKey(contract.getVipid());
		    
		   
		return vip;
	}

	//根据  理财经理查找vip
	public List<TVip> getVipsByUserId(int userId) {
		  
	  
	    
	    List<TVip> vips=new ArrayList<TVip>();
	    
	    TVipExample example=new TVipExample();
 
	     TVipExample.Criteria criteria=example.createCriteria();
	     
	      criteria.andManageridEqualTo(userId);
	    vips=vipMapper.selectByExample(example);
	   
       // System.out.println("获取到"+userId+"的用户数量:"+vips.size());
	   
	return vips;
}
	
 
	
	

	public List<Map<String, Object>> createExcelRecord(List<ExcelScheduleVo> vos) {
	 
	        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("sheetName", "sheet1");
	        listmap.add(map);
	        ExcelScheduleVo vo=null;
	        for (int j = 0; j < vos.size(); j++) {
	            vo=vos.get(j);
	            Map<String, Object> mapValue = new HashMap<String, Object>();
	            mapValue.put("contractString", vo.getContractString());
	            mapValue.put("billMoney", vo.getBillMoney());
	            mapValue.put("billTime", vo.getBillTime());
	            mapValue.put("billLongTime", vo.getBillLongTime());
	            
	            mapValue.put("managerName",vo.getManagerName() );
	            mapValue.put("vipName",vo.getVipName() );
	            mapValue.put("vipBankName", vo.getVipBankName());
	            mapValue.put("vipBankNum", vo.getVipBankNum());
	            mapValue.put("backTime", vo.getBackTime());
	            mapValue.put("backMoney", vo.getBackMoney());
	            mapValue.put("flag", vo.getFlag() );
	            listmap.add(mapValue);
	        }
	        return listmap;
	    }


	public void addVipVisitAction(TVisit visit, int userId,AjaxMsg msg) {
		
		 visit.setWritetime(new Date());
		 visit.setManagerid(userId);
		 
	 
		
		if (visitMapper.insertSelective(visit)>0)
		    {
		    	
			msg.setMsg("insertOK");
		    	
		    }
		else {
			msg.setMsg("insertFailed");
		}
		
	}

        //分页查询 访谈记录
	public PageBean<VipVisitVo> getVipVisitPages(PageVo pageVo,int userId) {
		
		 //给前台返回所有 分页数据
		PageBean<VipVisitVo> pageBean=new PageBean<VipVisitVo>();
		 //给前台返回 搜索条件
		 Map<String,Object> conditions=new HashMap<String, Object>();
		 //执行的sql语句
		String sqlString=" and t.flag !=4 ";
		
		 if ( userId>0)
		 {
			 sqlString=sqlString+" and  t.managerid ="+ userId;
			 
			 conditions.put("managerId", userId);
			 
		 }
	 
		
		List<VipVisitVo>  vos=new ArrayList<VipVisitVo>();
		
		  //开始吧 条件里的参数 和值 取出来
		if ( pageVo.conditions!=null)
		{
			
			 if (pageVo.conditions.get("vipname")!=null && pageVo.conditions.get("vipname")!="" )
			 {
				 String name=(String)pageVo.conditions.get("vipname");
				 
			 	 sqlString=sqlString+" and  v.name  like  '%"+name+"%'";
				  //给前台返回条件
				 conditions.put("vipname", name);
				 
				 
			 }
			 
			 
			 if (pageVo.conditions.get("visitType")!=null && pageVo.conditions.get("visitType")!="")
			 {
			 
				  
				  int visitType=  Integer.valueOf((String)pageVo.conditions.get("visitType"));
				  
				   if (visitType>0)
				   {
					   
					 	 sqlString=sqlString+" and  t.visitType  =" +pageVo.conditions.get("visitType");
						  //给前台返回条件
						 conditions.put("visitType", pageVo.conditions.get("visitType"));
				   }
		
				 
				 
			 }
			 
			 
			 
			 if (pageVo.conditions.get("managerId")!=null  && pageVo.conditions.get("managerId")!=""  )
			 {
				 int  managerid=Integer.valueOf((String)pageVo.conditions.get("managerId"));
				 
				  if (managerid>0)
				  {
					 	 sqlString=sqlString+" and  t.managerid = "+managerid+"";
						  //给前台返回条件
						 conditions.put("managerId", managerid);
				  }
 
			 }
			 
			 //访谈时间 
			 if (pageVo.conditions.get("beginTime")!=null  && pageVo.conditions.get("beginTime")!=""  )
			 {
					 	 sqlString=sqlString+" and  t.visittime >= '"+ pageVo.conditions.get("beginTime") +"'";
						  //给前台返回条件
						 conditions.put("beginTime", pageVo.conditions.get("beginTime"));
				 
 
			 }
			 
			 if (pageVo.conditions.get("endTime")!=null  && pageVo.conditions.get("endTime")!=""  )
			 {
					 	 sqlString=sqlString+" and  t.visittime <= '"+ pageVo.conditions.get("endTime") +"'";
						  //给前台返回条件
						 conditions.put("endTime", pageVo.conditions.get("endTime"));
 
			 }
			 
			 
			 

		}
 
		     //统计数量
		
		   int count=workMapper.getCountOfVipVistis(sqlString);
			  
			 sqlString=sqlString+"  order by t.visitTime desc   limit "+pageVo.getFromNum()+","+pageVo.getPageSize();
			 
			 vos=workMapper.getVipVisitVos(sqlString);
	   
			 pageBean.setPageDatas(vos);
			 
			  
		 
		 
		 pageBean.setCurrPage(pageVo.getCurrPage());
		 pageBean.setPageSize(pageVo.getPageSize());
		  //把记录数读出来，设置到pageBean中， pageBean会根据 currpage pagesize和 count计算 总页数 并且判断是否还有 上一页 下一页
		 pageBean.setTotalRecords(count);
		 
		
		 
		  
		 //把查询条件也返回给前台，以便在搜索框中继续保留刚才的搜索条件
		// pageBean.setParams(sqlParams.getParams());*/
		  pageBean.setConditions(conditions);
		  
		  return pageBean;
		  
		  
		 
	}


	public TVisit getVisitById(Integer visitId) {
	 
		return visitMapper.selectByPrimaryKey(visitId);
	}
	
	
	public VipVisitVo getVisitVoById(Integer visitId) {
		 
		
		VipVisitVo vo=new VipVisitVo();
		
		TVisit visit=getVisitById(visitId);
		
		 vo.setVipName(vipMapper.selectByPrimaryKey(visit.getVipid()).getName());
		 vo.setContent(visit.getContent());
		 vo.setVisitId(visitId);
		 vo.setMemo(visit.getMemo());
		 vo.setVisitTime(visit.getVisittime());
		
		return vo;
	}
	
	


	 //删除 拜访记录
	public void deleteVipVisitById(Integer id, AjaxMsg msg) {
	    
		  TVisit visit=visitMapper.selectByPrimaryKey(id);
		  
		   if  (visit!=null)
		   {
			   
			      visit.setFlag(4);
			      
			      if ( visitMapper.updateByPrimaryKeySelective(visit)>0)
			      {
			    	  msg.setMsg("deleteOK");
			      }
			      else
			      {
			    	  msg.setMsg("deleteFailed");
			      }
		   }
		
		
 
		
	} 
	}

 
