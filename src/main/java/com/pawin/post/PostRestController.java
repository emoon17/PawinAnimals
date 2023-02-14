package com.pawin.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pawin.post.bo.PostBO;
import com.pawin.post.bo.PostImageBO;
import com.pawin.post.model.Keyword;
import com.pawin.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;

	@Autowired
	private PostImageBO postImageBO;
	
	
	/**
	 *  글쓰기 API
	 * @param post
	 * @param files
	 * @param session
	 * @return
	 */
	@PostMapping("/post_create")
	public Map<String, Object> create(
			@ModelAttribute Post post, // name 태그 값과 일치하는 필드에 값이 들어간다.
			@RequestPart("files") List<MultipartFile> files,
			HttpSession session) {

		int userId = (int)session.getAttribute("userId");
		  
	     // db insert
		 postBO.addPost(post, files, userId);
		 
		  
		 
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
	
	@GetMapping("/search_list")
	public Map<String, Object> searchList(
			@RequestParam(value="searchTitle", required=false) String searchTitle,
			@RequestParam("searchStatus") String searchStatus,
			@RequestParam("searchAnimals") String searchAnimals,
			@RequestParam("searchArea") String searchArea) throws JsonProcessingException{
		
		
		//select
		List<Keyword> keywordList = postBO.getKeywordListByTitleStatusAnimalsArea(searchTitle, searchStatus, searchAnimals, searchArea);
		
		
		//map code 구분
		Map<String, Object> result = new HashMap<>();
		if (keywordList != null) {
			result.put("keywordList", keywordList);
		} else {
			result.put("errorMessage", "검색중 오류가 발생했습니다.");
		}
		
		// 응답
		return result;
		
	}

}
