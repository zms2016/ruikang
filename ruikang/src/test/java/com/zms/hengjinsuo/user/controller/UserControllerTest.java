package com.zms.hengjinsuo.user.controller;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zms.exception.ServicesException;
import com.zms.hengjinsuo.bean.TUser;
import com.zms.hengjinsuo.dao.TUserMapper;


@RunWith(SpringJUnit4ClassRunner.class)
// dao接口在 spring配置文件 applicationContext.xml中   其他的在springmvc.xml中 所以要为@Test指定2个配置文件
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"}) 
public class UserControllerTest {

	@Resource
	private TUserMapper userMapper;
	
	@Resource
	private UserController userController;
	
	
	private static Logger log = Logger.getLogger(UserControllerTest.class); 
	
	
	@Test
	public void testGetUser() {
 
	}

	@Test
	public void testSetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testWelcome() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdduser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserlist() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserInfo() throws ServicesException {
		 
	 
	 
	}

	@Test
	public void testGetUserInfo2() {
		    TUser user =new TUser();
		    
		    user.setUsername("zms");
		     
		    log.debug("插入成功:"+ userMapper.insert(user));
		    
		    
		    
	}

	@Test
	public void testGetUserInfo3() {
		fail("Not yet implemented");
	}

}
