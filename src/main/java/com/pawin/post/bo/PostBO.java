package com.pawin.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.dao.PostDAO;
import com.pawin.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	 @Autowired
	private PostImageBO postImageBO;
	
	
	public void addPost(Post post, List<MultipartFile> files, String loginId) {
		
		// 1. 글 등록
		postDAO.insertPost(post);
		
		//2 . 이미지 업로들을 내 컴퓨터에 업로드
		postImageBO.addPost(files, post.getUserId(), post.getStatus(), post.getId());
		
		//3. 이미지 패스들을 글 등록했던 아이디를 가져와서 이미지패스에 넣기 (반복문 돌려서)
		
	}
	
}
