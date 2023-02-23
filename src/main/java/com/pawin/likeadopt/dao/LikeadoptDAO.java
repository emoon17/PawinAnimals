package com.pawin.likeadopt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.likeadopt.model.Likeadopt;

@Repository
public interface LikeadoptDAO {

	public int selectLikeadoptCountByPostOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId,
			@Param("type") String type
			);
	
	public void deleteLikeadoptByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId,
			@Param("type") String type);
	
	public void deleteLikeAdopt(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void insertLikeadoptByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("type") String type);
	
	public List<Likeadopt> selectLikeAdoptList(
			@Param("postId") int postId);
}

