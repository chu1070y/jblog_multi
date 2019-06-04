package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	public BlogVo getBlogInfo(String id) {
		return blogDao.getBlogInfo(id);
	}

	public Boolean updateBlogInfo(BlogVo vo) {
		
		String url = fileUploadService.restore(vo.getMultipartFile());
		vo.setLogo(url);
		
		if(url == "") {
			vo.setLogo("assets/images/noimage.jpg");
		}
		
		return blogDao.updateBlog(vo);
	}
	
	public List<CategoryVo> getCategoryList(String id){
		return categoryDao.getCategoryList(id);
	}
	
	public Boolean insertCategory(CategoryVo vo) {
		return categoryDao.insertCategory(vo);
	}
	
	@Transactional
	public Boolean deleteCategory(CategoryVo vo) {
		
		PostVo postVo = new PostVo();
		postVo.setCategoryNo(vo.getNo());
		postVo.setId(vo.getBlogId());
		
		postDao.deletePost(postVo);
		
		return categoryDao.deleteCategory(vo);
	}
	
	public Boolean insertPost(PostVo vo) {
		
		return postDao.insertPost(vo);
	}
	
	public List<PostVo> getPost(PostVo postVo){
		
		return postDao.getPost(postVo);
	}

	public CategoryVo checkCategoryNo(PostVo postVo) {
		
		return categoryDao.checkCategoryNo(postVo);
	}

	public PostVo checkPostNo(PostVo postVo) {

		return postDao.checkPostNo(postVo);
	}

}
