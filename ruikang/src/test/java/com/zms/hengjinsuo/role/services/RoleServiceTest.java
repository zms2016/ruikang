package com.zms.hengjinsuo.role.services;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//dao接口在 spring配置文件 applicationContext.xml中   其他的在springmvc.xml中 所以要为@Test指定2个配置文件
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"}) 
public class RoleServiceTest {

	@Resource
	private RoleService  roleService;
	
	
 
	
	@Test
	public void test() {
		
		
	 
		
		roleService.getRoleListByUserId(156);
		 
/*		 List<Map<String, Object>>  rightmapList=roleService.getRightsMap(52);
		 Gson gson=new Gson();
		 
		 log.debug(gson.toJson(rightmapList));
		 */
		
		 
	}

}
