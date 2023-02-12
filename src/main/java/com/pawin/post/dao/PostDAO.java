package com.pawin.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;
@Repository
public interface PostDAO {
    //test용
	public List<Map<String, Object>> selectPostListTest();
	
	public void insertPost(Post post);
	
	public List<Post> selectPostList();
	
	public List<Keyword> selectKeywordListByTitleStatusAnimalsArea(
			@Param("searchTitle") String searchTitle,
			@Param("searchStatus") String searchStatus, 
			@Param("searchAnimals") String searchAnimals,
			@Param("searchArea") String searchArea);
	
	

}
