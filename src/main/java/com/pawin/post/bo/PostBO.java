package com.pawin.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.dao.PostDAO;
import com.pawin.post.dao.PostImageDAO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	 @Autowired
	private PostImageDAO postImageDAO;
	
	
	public int addPost(String title, String content, String animals, 
			String status, String area, int userId, String loginId) {
		
		postImageDAO.insertImagePost(loginId, userId);
			
		
		return postDAO.insertPost(title, content, animals, status, area, userId);
	}
	
	
}
