package com.pawin.likeadopt.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeadoptDAO {

	public int selectLikeadoptCountByPostOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId,
			@Param("type") String type);
	
	public void deleteLikeadoptByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId,
			@Param("type") String type);
	
	public void insertLikeadoptByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("type") String type);
}

