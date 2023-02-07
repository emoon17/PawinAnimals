package com.pawin.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageDAO {

	public int insertImagePost(@Param("imagePath") String imagePath, @Param("userId") int userId);
}
