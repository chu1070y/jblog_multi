package com.cafe24.jblog.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class UserDaoTests {
	
	@Autowired
	private UserDao dao;
	
	@Test
	public void insertUser() {
		
		UserVo vo = new UserVo();
		vo.setId("chu1070y");
		vo.setName("추연훈");
		vo.setPassword("123");
		
		assertTrue(dao.insertUser(vo));
	}
	
	@Test
	public void checkUser() {
		String id = "chu1070y";
		
		System.out.println(dao.checkid(id));
	}
	
	@Test
	public void login() {
		UserVo vo = new UserVo();
		vo.setId("chu1070y");
		vo.setPassword("123");
		
		System.out.println(dao.login(vo));
	}
	
	
}
