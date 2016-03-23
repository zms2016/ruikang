package com.zms.hengjinsuo.fundpool.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zms.hengjinsuo.bean.TFundpool;
import com.zms.hengjinsuo.dao.TFundpoolMapper;
import com.zms.hengjinsuo.dao.WorkMapper;

/**
 * 
 *@时间: 2016年3月20日上午10:36:17
 *@作者:张木生 330140511@qq.com
 *@说明:资金池
 */


@Service
public class FundPoolService {
	
@Autowired
private WorkMapper workMapper;

 @Autowired
 private TFundpoolMapper fundpoolMapper;

 
 /**
  * 
  *@返回值:boolean
  *@时间: 2016年3月20日上午10:48:21
  *@作者:张木生 330140511@qq.com
  *@说明:往资金池里插数据
  *@参数:@param flow  资金流向 0 流入 1 流出
  *@参数:@param flowType 0 融资借款减 1融资人还款加 2 财务充值加 3财务提现减 4理财客户入账加 5付息给理财客户减  6产品经理删除融资项目减 7 财务删除理财单据减
  *@参数:@param needmoney
  */
public boolean addItem(int flow,int flowType, Float money,String username ) {
	 
	       TFundpool fundpool=new TFundpool();
	       fundpool.setFlow((short)flow);
	       fundpool.setFlowtype((short)flowType);
	       fundpool.setMoney(money);
	       fundpool.setFundpool(0.0f);
	       fundpool.setTime(new Date());
	       fundpool.setOperator(username);
	     if (  fundpoolMapper.insert(fundpool)>0)
	     {
	    	 return true;
	     }
	
	return false;
}
 
 
 

}
