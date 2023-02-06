package com.pawin.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface PostDAO {
    //test용
	public List<Map<String, Object>> selectPostListTest();
	
	public int insertPost(
			@Param("title") String title,
			@Param("content") String content, 
			@Param("animals") String animals, 
			@Param("status") String status,
			@Param("area") String area, 
			@Param("userId") int userId);
	

}
