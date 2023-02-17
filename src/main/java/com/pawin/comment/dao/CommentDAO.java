package com.pawin.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public void insertCommentByUserIdPostIdComment(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content);

	public List<Comment> selectCommentListByPostId(int postId);
	
	public void deleteCommentListByPostId(
			@Param("userId") int userId, 
			@Param("postId") int postId,
			@Param("content") String content);
}

