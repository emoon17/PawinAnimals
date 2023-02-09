package com.pawin.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.dao.PostDAO;
import com.pawin.post.model.Post;
import com.pawin.user.bo.UserBO;
import com.pawin.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	 @Autowired
	private PostImageBO postImageBO;
	 
	 
	
	
	public void addPost(Post post, List<MultipartFile> files, int userId) {
		
		// 1. 글 등록
		post.setUserId(userId);
		postDAO.insertPost(post);
		
		
		//2 . 이미지 업로들을 내 컴퓨터에 업로드
		postImageBO.addPost(files, userId, post.getStatus(), post.getId());
		
		
		
	}
	
}
