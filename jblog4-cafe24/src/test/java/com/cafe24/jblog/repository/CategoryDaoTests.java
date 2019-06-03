package com.cafe24.jblog.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.jblog.vo.CategoryVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class CategoryDaoTests {

	@Autowired
	private CategoryDao dao;
	
	@Test
	public void getCategoryList() {
		List<CategoryVo> list = dao.getCategoryList("chu1070y");
		
		System.out.println(list);
	}
	
	@Test
	public void insertCategory() {
		CategoryVo vo = new CategoryVo();
		vo.setTitle("카테고리1");
		vo.setDescription("나는 누구? 여긴 어디?");
		vo.setBlogId("chu1070y");
		
		assertNotNull(dao.insertCategory(vo));
	}
	
	@Test
	public void deleteCategory() {
		
		CategoryVo vo = new CategoryVo();
		vo.setNo(7L);
		
		assertNotNull(dao.deleteCategory(vo));
	}
}
