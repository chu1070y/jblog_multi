package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertPost(PostVo vo) {
		
		return 1 == sqlSession.insert("post.insertPost", vo);
	}
	
	public Boolean deletePost(PostVo vo) {
		
		return 1 == sqlSession.delete("post.deletePost", vo);
	}
	
	public List<PostVo> getPost(PostVo vo){
		
		return sqlSession.selectList("post.getPostList", vo);
	}

	public PostVo checkPostNo(PostVo postVo) {

		return sqlSession.selectOne("post.checkPostNo", postVo);
	}

}
