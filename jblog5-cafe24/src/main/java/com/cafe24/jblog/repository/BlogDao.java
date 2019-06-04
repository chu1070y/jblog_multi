package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertBlog(BlogVo vo) {
		int count = sqlSession.insert("blog.insertBlog", vo);
		
		return 1 == count;
	}
	
	public Boolean updateBlog(BlogVo vo) {
		int count = sqlSession.update("blog.updateBlog", vo);
		
		return 1 == count;
	}

	public BlogVo getBlogInfo(String id) {
		
		return sqlSession.selectOne("blog.getBlogInfo", id);
	}
	
}
