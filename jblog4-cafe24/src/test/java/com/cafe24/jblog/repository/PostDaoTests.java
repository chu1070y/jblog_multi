package com.cafe24.jblog.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.PostVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class PostDaoTests {

	@Autowired
	private PostDao dao;
	
	@Test
	public void insertPost() {
		
		PostVo vo = new PostVo();
		vo.setTitle("카테고릐");
		vo.setContent("11212312");
		vo.setCategoryNo(1L);
		vo.setId("chu1070y");
		
		assertNotNull(dao.insertPost(vo));
	}
	
	@Test
	public void deletePost() {
		PostVo vo = new PostVo();
		vo.setCategoryNo(10L);
		vo.setId("chu1070y");
		
		assertNotNull(dao.deletePost(vo));
	}
	
}
