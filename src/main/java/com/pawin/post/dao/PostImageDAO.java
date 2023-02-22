package com.pawin.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pawin.post.model.ImagePath;

@Repository
public interface PostImageDAO {

	public void insertImagePost(
			@Param("imagePath") String imagePath,
			@Param("userId") int userId, 
			@Param("postId") int postId);
	
	public List<ImagePath> selectImagePathListByPostId(int postId);
	
	public ImagePath selectIamgePathByPostId(int postId);
}
