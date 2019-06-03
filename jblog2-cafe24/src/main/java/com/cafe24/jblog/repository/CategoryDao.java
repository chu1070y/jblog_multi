package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> getCategoryList(String id){
		
		return sqlSession.selectList("category.getCategoryList", id);
	}
	
	
	public Boolean insertCategory(CategoryVo vo) {
		
		int count = sqlSession.insert("category.insertCategory", vo);
		
		return 1 == count;
	}
	
	public Boolean deleteCategory(CategoryVo vo) {
		
		int count = sqlSession.delete("category.deleteCategory", vo);
		
		return 1 == count;
	}


	public CategoryVo checkCategoryNo(PostVo postVo) {

		return sqlSession.selectOne("category.checkCategoryNo", postVo);
	}
	
}
