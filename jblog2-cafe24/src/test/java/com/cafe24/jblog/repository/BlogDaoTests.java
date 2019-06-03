package com.cafe24.jblog.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.BlogVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class BlogDaoTests {

	@Autowired
	private BlogDao dao;
	
	@Test
	public void insertBlog() {
		
		BlogVo vo = new BlogVo();
		vo.setId("chu1070y");
		vo.setLogo("/assets/images/spring-logo.jpg");
		vo.setTitle("테스트중");
		
		assertTrue(dao.updateBlog(vo));
	}
	
	@Test
	public void getBlogInfo() {
		
		String id = "chu1070y";
		
		System.out.println(dao.getBlogInfo(id));
	}
	
	@Test
	public void updateBlogInfo() {
		
		BlogVo vo = new BlogVo();
		vo.setId("chu1070y");
		vo.setLogo("/assets/images/spring-logo.jpg");
		vo.setTitle("ttttttest");
		
		System.out.println(dao.updateBlog(vo));
	}
	
	
}
