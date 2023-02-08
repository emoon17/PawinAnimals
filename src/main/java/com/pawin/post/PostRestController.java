package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.bo.PostBO;
import com.pawin.post.bo.PostImageBO;
import com.pawin.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;

	@Autowired
	private PostImageBO postImageBO;
	
	@PostMapping("/post_create")
	public Map<String, Object> create(
			@ModelAttribute Post post, // name 태그 값과 일치하는 필드에 값이 들어간다.
			@RequestPart("files") List<MultipartFile> files, 
			HttpSession session) {

		
		  // 세션으로 필요한 정보 가져오기 userId, loginId, postId 
		Integer userId = (Integer)session.getAttribute("userId"); 
		String loginId = (String)session.getAttribute("loginId"); 
		
		  
	     // db insert
		 postBO.addPost(post, files, loginId);
		 
		  
		 
		// code 구분
		Map<String, Object> result = new HashMap<>();
		
		  if (post != null) { 
		      result.put("code", 1); 
		      session.setAttribute("title", post.getTitle());
		      session.setAttribute("content", post.getContent());
		      session.setAttribute("status", post.getStatus());
		      session.setAttribute("animals", post.getAnimals());
		      session.setAttribute("postId", post.getId());
		      session.setAttribute("area", post.getArea());
          } 
		  else {
			  result.put("errorMessage","관리자에게 문의하여 주세요."); 
		  }
		 
		
		// 응답하기
		return result;
	}

}
