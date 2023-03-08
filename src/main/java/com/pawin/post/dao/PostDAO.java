package com.pawin.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;
import com.pawin.post.model.PostView;
@Repository
public interface PostDAO {
    
	
	//test용
	public List<Map<String, Object>> selectPostListTest();
	
	
	
	public void insertPost(Post post);
	
	public void updatePost(
			@Param("postId") int postId,
			@Param("title") String title,
			@Param("content") String content,
			@Param("status") String status,
			@Param("animals") String animals,
			@Param("area") String area);
	
	public List<Post> selectPostList();
	
	public List<Post> selectAdoptPostList();
	
	public List<Post> selectStatusPostList();

	public List<Post> selectFamilyPostListDesc();
	
	public List<Post> selectAdoptPostListByPostId(int postId);
	
	public List<Post> selectPostListByPostId(int postId);
	
	public List<Post> selectPostListByPostIdUserId(int postId, int userId);
	
	public List<Keyword> selectKeywordListByTitleStatusAnimalsArea(
			@Param("searchTitle") String searchTitle,
			@Param("searchStatus") String searchStatus, 
			@Param("searchAnimals") String searchAnimals,
			@Param("searchArea") String searchArea);
	
	public List<Keyword> selectKeywordAdoptListByTitleStatusAnimals(
			@Param("searchTitle") String searchTitle,
			@Param("searchAnimals") String searchAnimals,
			@Param("searchStatus") String searchStatus);
	
	public List<PostView> selectPostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public int deletePostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	

}
