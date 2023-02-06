package com.pawin.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageDAO {

	public void insertPost(
			@Param("imagePath") String imagePath,
			@Param("userId") int userId, 
			@Param("postId") int postId);
}
