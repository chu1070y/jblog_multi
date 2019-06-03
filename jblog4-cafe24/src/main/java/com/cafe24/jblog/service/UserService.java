package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Transactional
	public Boolean insertUser(UserVo userVo) {
		
		Boolean user = userDao.insertUser(userVo);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle("임시 타이틀");
		blogVo.setLogo("assets/images/noimage.jpg");
		
		Boolean blog = blogDao.insertBlog(blogVo);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(blogVo.getId());
		categoryVo.setTitle("미분류");
		categoryVo.setDescription("카테고리를 지정하지 않은 경우");
		
		Boolean category = categoryDao.insertCategory(categoryVo); 
		
		return user && blog && category;
	}

	public Boolean checkid(String id) {
		
		return userDao.checkid(id);
	}
	
	public UserVo login(UserVo vo) {
		
		return userDao.login(vo);
	}
	
}
