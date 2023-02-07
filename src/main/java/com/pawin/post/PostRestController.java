package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pawin.post.bo.PostBO;
import com.pawin.post.bo.PostImageBO;

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
			@RequestParam("writeTitle") String title,
			@RequestParam("writeArea") String content,
			@RequestPart("files") List<MultipartFile> files, 
			@RequestParam("animals") String animals,
			@RequestParam("status") String status,
		    @RequestParam("area") String area,
			HttpSession session) {

		
		  // 세션으로 필요한 정보 가져오기 userId, loginId, postId 
		Integer userId = (Integer)session.getAttribute("userId"); 
		String loginId = (String)session.getAttribute("loginId"); 
		
		  
	     // db insert
		  int rowCount = postBO.addPost(title, content, animals, status, area, userId, loginId);
		  int rowImageCount = postImageBO.addPost(files, userId, loginId);
		  
		 
		// code 구분
		Map<String, Object> result = new HashMap<>();
		
		  if (rowCount > 0 && rowImageCount > 0) { 
		      result.put("code", 1); 
			  session.setAttribute("title",title); 
			  session.setAttribute("animals", animals);
			  session.setAttribute("status", status); 
			  session.setAttribute("area", area);

          } 
		  else {
			  result.put("errorMessage","관리자에게 문의하여 주세요."); 
		  }
		 
		
		// 응답하기
		return result;
	}

}
