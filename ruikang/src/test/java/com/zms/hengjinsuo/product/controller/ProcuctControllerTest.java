package com.zms.hengjinsuo.product.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zms.hengjinsuo.financial.controller.FinancialController;


@RunWith(SpringJUnit4ClassRunner.class)
// dao接口在 spring配置文件 applicationContext.xml中   其他的在springmvc.xml中 所以要为@Test指定2个配置文件
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"}) 
public class ProcuctControllerTest {

	@Resource
	 private FinancialController procuctController;
	
	@Test
	public void getProducts() {
		
		 
/*		  
 	 List<TProduct>  products=new ArrayList<TProduct>();
		 
		 products=procuctController.getProducts();
		 
		 for (TProduct product:products)
		 {
			 log.debug(product.getName()+"价格:"+product.getPrice());
		 } 
	 */
	}

}
