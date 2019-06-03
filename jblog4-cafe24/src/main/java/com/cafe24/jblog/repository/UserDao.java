package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertUser(UserVo vo) {
		
		int count = sqlSession.insert("user.insertUser", vo);
		
		return 1 == count;
	}

	public Boolean checkid(String id) {
		
		Boolean check = sqlSession.selectOne("user.checkUser", id) != null;
		
		return check;
	}

	public UserVo login(UserVo vo) {
		
		return sqlSession.selectOne("user.login", vo);
	}
	

}
